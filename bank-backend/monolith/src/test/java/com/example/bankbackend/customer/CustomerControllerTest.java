package com.example.bankbackend.customer;

import com.example.bankbackend.customer.dto.CustomerCreateDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class CustomerControllerTest {


    private MockMvc mockMvc;
    private CustomerFacade customerFacade;
    private ObjectMapper objectMapper;
    private CustomerRoleRepository roleRepository;


    @Autowired
    public CustomerControllerTest(MockMvc mockMvc, CustomerFacade customerFacade, ObjectMapper objectMapper, CustomerRoleRepository roleRepository) {
        this.mockMvc = mockMvc;
        this.customerFacade = customerFacade;
        this.objectMapper = objectMapper;
        this.roleRepository = roleRepository;
    }

    @BeforeEach
    public void setupRoles() {
        if (roleRepository.findByRole("ROLE_USER").isEmpty()) {
            roleRepository.save(new CustomerRole("ROLE_USER"));
            roleRepository.save(new CustomerRole("ROLE_ADMIN"));
        }
    }

    @Test
    @DisplayName("getShouldReturnCustomer")
    void get() throws Exception {
        CustomerCreateDto customerCreateDto = new CustomerCreateDto(
                "Pieter",
                "Bark",
                "testPassword",
                "testPassword",
                "Pieter@gmail.com"
        );
        customerFacade.create(customerCreateDto);

        Assertions.assertNotNull(customerFacade.getById(1));

        mockMvc.perform(MockMvcRequestBuilders.get("/customers/{id}", 1))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.firstName", is("Pieter")))
                .andExpect(jsonPath("$.lastName", is("Bark")))
                .andExpect(jsonPath("$.email", is("Pieter@gmail.com")))
                .andExpect(status().isOk()).andReturn();
    }

    @Test
    @DisplayName("getShouldReturnCustomerNotFoundException")
    void getCustomerNotFoundException() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/customers/{id}", 1))
                .andExpect(result -> Assertions.assertEquals("There is no customer with id : 1", result.getResolvedException().getMessage()))
                .andExpect(status().is4xxClientError()).andReturn();
    }

    @Test
    @DisplayName("createShouldCreateCustomer")
    void create() throws Exception {
        CustomerSnapshot customerSnapshot = CustomerSnapshot.builder()
                .id(1)
                .firstName("Pieter")
                .lastName("Bark")
                .funds(new BigDecimal("1000.00"))
                .password("testPassword")
                .email("Pieter@gmail.com")
                .build();

        CustomerCreateDto customerCreateDto = new CustomerCreateDto(
                "Pieter",
                "Bark",
                "testPassword",
                "testPassword",
                "Pieter@gmail.com"
        );
        this.mockMvc.perform(post("/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(customerCreateDto)))
                .andExpect(status().is2xxSuccessful()).andReturn();


        CustomerSnapshot toFindSnapshot = customerFacade.getById(1).getSnapshot();

        Assertions.assertNotNull(toFindSnapshot);
        Assertions.assertEquals(customerSnapshot.getFirstName(), toFindSnapshot.getFirstName());
        Assertions.assertEquals(customerSnapshot.getFunds(), toFindSnapshot.getFunds());
        Assertions.assertEquals(customerSnapshot.getLastName(), toFindSnapshot.getLastName());
        Assertions.assertEquals(customerSnapshot.getEmail(), toFindSnapshot.getEmail());


    }
    @Test
    @DisplayName("createShouldReturnCustomerEmailAlreadyExist")
    void createCustomerEmailAlreadyExistException() throws Exception {
        CustomerCreateDto customerCreateDtoFirst = new CustomerCreateDto(
                "Pieter",
                "Bark",
                "testPassword",
                "testPassword",
                "Pieter@gmail.com"
        );
        customerFacade.create(customerCreateDtoFirst);

        Assertions.assertNotNull(customerFacade.getById(1));

        CustomerCreateDto customerCreateDtoSecond = new CustomerCreateDto(
                "Pieter",
                "Bark",
                "testPassword",
                "testPassword",
                "Pieter@gmail.com"
        );
        this.mockMvc.perform(post("/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(customerCreateDtoSecond)))
                .andExpect(result -> Assertions.assertEquals("Customer with that email already exists: Pieter@gmail.com", result.getResolvedException().getMessage()))
                .andExpect(status().is4xxClientError()).andReturn();

    }
    @Test
    @DisplayName("createShouldReturnCustomerValidationPassword")
    void createCustomerValidationPasswordException() throws Exception {
        CustomerCreateDto customerCreateDtoSecond = new CustomerCreateDto(
                "Pieter",
                "Bark",
                "correctPassword",
                "wrongPassword",
                "Pieter@gmail.com"
        );
        this.mockMvc.perform(post("/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(customerCreateDtoSecond)))
                .andExpect(result -> Assertions.assertEquals("Passwords are not the same", result.getResolvedException().getMessage()))
                .andExpect(status().is4xxClientError()).andReturn();
    }
}

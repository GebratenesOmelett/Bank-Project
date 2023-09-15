package com.example.bankbackend.customer;

import com.example.bankbackend.customer.dto.CustomerCreateDto;
import com.example.bankbackend.customer.dto.CustomerDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private CustomerFacade customerFacade;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private CustomerRoleRepository roleRepository;




    @BeforeEach
    public void setupRoles(){
        if(roleRepository.findByRole("ROLE_USER").isEmpty()){
            roleRepository.save(new CustomerRole("ROLE_USER"));
            roleRepository.save(new CustomerRole("ROLE_ADMIN"));
        }
    }
//    @Test
//    @DisplayName("getShouldReturnCustomer")
//    void get() {
//        System.out.println("gaga");
//    }

    @Test
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
                .andExpect(status().isOk()).andReturn();


        CustomerSnapshot toFindSnapshot = customerFacade.get(1).getSnapshot();

        Assertions.assertNotNull(toFindSnapshot);
        Assertions.assertEquals(customerSnapshot.getFirstName(), toFindSnapshot.getFirstName());
        Assertions.assertEquals(customerSnapshot.getFunds(), toFindSnapshot.getFunds());
        Assertions.assertEquals(customerSnapshot.getLastName(), toFindSnapshot.getLastName());
        Assertions.assertEquals(customerSnapshot.getEmail(), toFindSnapshot.getEmail());


    }
}

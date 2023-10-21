package com.example.bankbackend.transfer;

import com.example.bankbackend.customer.CustomerFacade;
import com.example.bankbackend.customer.CustomerRoleFacade;
import com.example.bankbackend.customer.dto.CustomerCreateDto;
import com.example.bankbackend.customer.dto.CustomerRoleCreateDto;
import com.example.bankbackend.customer.exceptions.CustomerNotFoundException;
import com.example.bankbackend.transfer.dto.TransferCreateDto;
import com.example.bankbackend.transfer.exceptions.TransferNotFoundException;
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
class TransferControllerTest {

    private final MockMvc mockMvc;
    private final CustomerFacade customerFacade;
    private final ObjectMapper objectMapper;
    private final CustomerRoleFacade customerRoleFacade;
    private final TransferFacade transferFacade;

    @Autowired
    public TransferControllerTest(MockMvc mockMvc,
                                  CustomerFacade customerFacade,
                                  ObjectMapper objectMapper,
                                  CustomerRoleFacade customerRoleFacade,
                                  TransferFacade transferFacade) {
        this.mockMvc = mockMvc;
        this.customerFacade = customerFacade;
        this.objectMapper = objectMapper;
        this.customerRoleFacade = customerRoleFacade;
        this.transferFacade = transferFacade;
    }

    @BeforeEach
    public void setupRoles() {
        if (customerRoleFacade.findRole("ROLE_USER") == null) {
            customerRoleFacade.save(new CustomerRoleCreateDto("ROLE_USER"));
            customerRoleFacade.save(new CustomerRoleCreateDto("ROLE_ADMIN"));
        }
    }


    @Test
    @DisplayName("createShouldCreateCustomer")
    void create() throws Exception {
        CustomerCreateDto customerCreateDtoAddressee = new CustomerCreateDto(
                "Pieter",
                "Bark",
                "testPassword",
                "testPassword",
                "Pieter@gmail.com"
        );
        CustomerCreateDto customerCreateDtoReceiver = new CustomerCreateDto(
                "Molik",
                "Bark",
                "testPassword",
                "testPassword",
                "Molik@gmail.com"
        );

        customerFacade.create(customerCreateDtoAddressee);
        customerFacade.create(customerCreateDtoReceiver);

        Assertions.assertNotNull(customerFacade.getByEmail("Pieter@gmail.com"));
        Assertions.assertNotNull(customerFacade.getByEmail("Molik@gmail.com"));


        TransferCreateDto transferCreateDto = new TransferCreateDto(
                "payment",
                new BigDecimal(100),
                1,
                2);


        this.mockMvc.perform(post("/api/transfers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(transferCreateDto)))
                .andExpect(jsonPath("$.title", is("payment")))
                .andExpect(jsonPath("$.funds", is(100)))
                .andExpect(jsonPath("$.receiverId", is(2)))
                .andExpect(status().is2xxSuccessful()).andReturn();
    }

    @Test
    @DisplayName("createShouldReturnCustomerNotFoundException")
    void createCustomerNotFoundException() throws Exception {
        CustomerCreateDto customerCreateDtoAddressee = new CustomerCreateDto(
                "Pieter",
                "Bark",
                "testPassword",
                "testPassword",
                "Pieter@gmail.com"
        );

        customerFacade.create(customerCreateDtoAddressee);
        Assertions.assertNotNull(customerFacade.getByEmail("Pieter@gmail.com"));
        Assertions.assertThrows(CustomerNotFoundException.class, () -> customerFacade.getByEmail("Molik@gmail.com"));

        TransferCreateDto transferCreateDto = new TransferCreateDto(
                "payment",
                new BigDecimal(100),
                1,
                2);

        this.mockMvc.perform(post("/api/transfers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(transferCreateDto)))
                .andExpect(result -> Assertions.assertEquals("There is no customer with : 2", result.getResolvedException().getMessage()))
                .andExpect(status().is4xxClientError()).andReturn();
    }

    @Test
    @DisplayName("createShouldReturnCustomerNotEnoughFundsException")
    void createCustomerNotEnoughFoundsException() throws Exception {
        CustomerCreateDto customerCreateDtoAddressee = new CustomerCreateDto(
                "Pieter",
                "Bark",
                "testPassword",
                "testPassword",
                "Pieter@gmail.com"
        );
        CustomerCreateDto customerCreateDtoReceiver = new CustomerCreateDto(
                "Molik",
                "Bark",
                "testPassword",
                "testPassword",
                "Molik@gmail.com"
        );

        customerFacade.create(customerCreateDtoAddressee);
        customerFacade.create(customerCreateDtoReceiver);

        Assertions.assertNotNull(customerFacade.getByEmail("Pieter@gmail.com"));
        Assertions.assertNotNull(customerFacade.getByEmail("Molik@gmail.com"));

        TransferCreateDto transferCreateDto = new TransferCreateDto(
                "payment",
                new BigDecimal(1100),
                1,
                2);

        this.mockMvc.perform(post("/api/transfers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(transferCreateDto)))
                .andExpect(result -> Assertions.assertEquals("Not enough funds", result.getResolvedException().getMessage()))
                .andExpect(status().is4xxClientError()).andReturn();
    }

    @Test
    @DisplayName("createShouldReturnIdCanNotBeTheSameException")
    void createIdCanNotBeTheSameException() throws Exception {
        CustomerCreateDto customerCreateDtoAddressee = new CustomerCreateDto(
                "Pieter",
                "Bark",
                "testPassword",
                "testPassword",
                "Pieter@gmail.com"
        );

        customerFacade.create(customerCreateDtoAddressee);
        Assertions.assertNotNull(customerFacade.getByEmail("Pieter@gmail.com"));
        Assertions.assertThrows(CustomerNotFoundException.class, () -> customerFacade.getByEmail("Molik@gmail.com"));

        TransferCreateDto transferCreateDto = new TransferCreateDto(
                "payment",
                new BigDecimal(100),
                1,
                1);

        this.mockMvc.perform(post("/api/transfers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(transferCreateDto)))
                .andExpect(result -> Assertions.assertEquals("Id can not be the same", result.getResolvedException().getMessage()))
                .andExpect(status().is4xxClientError()).andReturn();
    }

    @Test
    @DisplayName("getByIdShouldReturnTransfer")
    void getById() throws Exception {
        CustomerCreateDto customerCreateDtoAddressee = new CustomerCreateDto(
                "Pieter",
                "Bark",
                "testPassword",
                "testPassword",
                "Pieter@gmail.com"
        );
        CustomerCreateDto customerCreateDtoReceiver = new CustomerCreateDto(
                "Molik",
                "Bark",
                "testPassword",
                "testPassword",
                "Molik@gmail.com"
        );

        customerFacade.create(customerCreateDtoAddressee);
        customerFacade.create(customerCreateDtoReceiver);

        TransferCreateDto transferCreateDto = new TransferCreateDto(
                "payment",
                new BigDecimal(100),
                1,
                2);

        this.mockMvc.perform(post("/api/transfers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(transferCreateDto)))
                .andExpect(status().is2xxSuccessful()).andReturn();

        Assertions.assertNotNull(customerFacade.getByEmail("Pieter@gmail.com"));
        Assertions.assertNotNull(customerFacade.getByEmail("Molik@gmail.com"));
        Assertions.assertNotNull(transferFacade.getDtoById(1));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/transfers/id/{id}", 1))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.title", is("payment")))
                .andExpect(jsonPath("$.funds", is(100.0)))
                .andExpect(jsonPath("$.receiverId", is(2)))
                .andExpect(status().isOk()).andReturn();
    }

    @Test
    @DisplayName("getByIdShouldReturnTransferNotFoundException")
    void getByIdTransferNotFoundException() throws Exception {

        Assertions.assertThrows(TransferNotFoundException.class, () -> transferFacade.getDtoById(1));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/transfers/id/{id}", 1))
                .andExpect(result -> Assertions.assertEquals("There is no transfer with : 1", result.getResolvedException().getMessage()))
                .andExpect(status().is4xxClientError()).andReturn();
    }

    @Test
    @DisplayName("getByIdShouldReturnTransfer")
    void getByEmail() throws Exception {
        CustomerCreateDto customerCreateDtoAddressee = new CustomerCreateDto(
                "Pieter",
                "Bark",
                "testPassword",
                "testPassword",
                "Pieter@gmail.com"
        );
        CustomerCreateDto customerCreateDtoReceiver = new CustomerCreateDto(
                "Molik",
                "Bark",
                "testPassword",
                "testPassword",
                "Molik@gmail.com"
        );

        customerFacade.create(customerCreateDtoAddressee);
        customerFacade.create(customerCreateDtoReceiver);

        TransferCreateDto transferCreateDto = new TransferCreateDto(
                "payment",
                new BigDecimal(100),
                1,
                2);
        TransferCreateDto transferCreateDtoSecond = new TransferCreateDto(
                "tax",
                new BigDecimal(200),
                1,
                2);

        this.mockMvc.perform(post("/api/transfers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(transferCreateDto)))
                .andExpect(status().is2xxSuccessful()).andReturn();

        this.mockMvc.perform(post("/api/transfers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(transferCreateDtoSecond)))
                .andExpect(status().is2xxSuccessful()).andReturn();

        Assertions.assertNotNull(customerFacade.getByEmail("Pieter@gmail.com"));
        Assertions.assertNotNull(customerFacade.getByEmail("Molik@gmail.com"));
        Assertions.assertNotNull(transferFacade.getDtoById(1));
        Assertions.assertNotNull(transferFacade.getDtoById(2));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/transfers/email/{email}", "Pieter@gmail.com"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].title", is("tax")))
                .andExpect(jsonPath("$[0].funds", is(200.0)))
                .andExpect(jsonPath("$[0].receiverId", is(2)))
                .andExpect(jsonPath("$[1].title", is("payment")))
                .andExpect(jsonPath("$[1].funds", is(100.0)))
                .andExpect(jsonPath("$[1].receiverId", is(2)))
                .andExpect(status().isOk()).andReturn();
    }
    @Test
    @DisplayName("getByIdShouldReturnCustomerNotFoundException")
    void getByEmailCustomerNotFoundException() throws Exception {
        Assertions.assertThrows(CustomerNotFoundException.class, () -> customerFacade.getByEmail("Pieter@gmail.com"));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/transfers/email/{email}", "Pieter@gmail.com"))
                .andExpect(result -> Assertions.assertEquals("There is no customer with : Pieter@gmail.com", result.getResolvedException().getMessage()))
                .andExpect(status().is4xxClientError()).andReturn();
    }
}

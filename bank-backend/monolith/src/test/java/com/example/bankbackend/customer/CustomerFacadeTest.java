package com.example.bankbackend.customer;

import com.example.bankbackend.transfer.dto.TransferCreateDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;

@SpringBootTest
class CustomerFacadeTest {



    @Test
    void create() {

    }

    @Test
    void updateFunds() {
        TransferCreateDto toCreate = new TransferCreateDto("transfer",
                                        new BigDecimal(100), 1, 2);

    }

    @Test
    void toSimpleCustomerEntity() {
    }

    @Test
    void doesCustomerExist() {
    }
}
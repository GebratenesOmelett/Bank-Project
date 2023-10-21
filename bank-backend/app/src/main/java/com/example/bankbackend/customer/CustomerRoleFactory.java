package com.example.bankbackend.customer;

import com.example.bankbackend.customer.dto.CustomerRoleCreateDto;

class CustomerRoleFactory {
    CustomerRole from(CustomerRoleCreateDto customerRoleCreateDto){
        return CustomerRole.restore(new CustomerRoleSnapshot(
                customerRoleCreateDto.getRole()
        ));
    }
}

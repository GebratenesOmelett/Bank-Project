package com.example.bankbackend.customer;

import com.example.bankbackend.customer.dto.CustomerDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customers")
class CustomerController {

    CustomerFacade customerFacade;
    public CustomerController(CustomerFacade customerFacade) {
        this.customerFacade = customerFacade;
    }

    @GetMapping("/{id}")
    ResponseEntity<CustomerDto> get(@PathVariable int id){

        return customerFacade.get(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @PostMapping()
    void create(@RequestBody CustomerDto toCreate){
        customerFacade.create(toCreate);
    }
}

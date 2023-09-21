package com.example.bankbackend.customer;

import com.example.bankbackend.customer.dto.CustomerCreateDto;
import com.example.bankbackend.customer.dto.CustomerDto;
import com.example.bankbackend.transfer.exceptions.TransferValidationException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
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

        return new ResponseEntity<>(customerFacade.getDto(id), HttpStatus.OK);
    }
    @PostMapping()
    ResponseEntity<CustomerDto> create(@RequestBody @Valid CustomerCreateDto toCreate, BindingResult errors){
        if(errors.hasErrors()){
            throw new TransferValidationException(errors);
        }
        return new ResponseEntity<>(customerFacade.create(toCreate), HttpStatus.CREATED);
    }
}

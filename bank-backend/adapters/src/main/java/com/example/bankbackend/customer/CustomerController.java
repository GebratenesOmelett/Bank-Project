package com.example.bankbackend.customer;

import com.example.bankbackend.customer.dto.CustomerCreateDto;
import com.example.bankbackend.customer.dto.CustomerDto;
import com.example.bankbackend.customer.dto.CustomerLoginDto;
import com.example.bankbackend.customer.dto.CustomerLoginResponseDto;
import com.example.bankbackend.transfer.exceptions.TransferValidationException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customers")
@CrossOrigin
class CustomerController {


    CustomerFacade customerFacade;
    public CustomerController(CustomerFacade customerFacade) {
        this.customerFacade = customerFacade;
    }

    @GetMapping("/{id}")

    ResponseEntity<CustomerDto> get(@PathVariable int id){

        return new ResponseEntity<>(customerFacade.getDtoById(id), HttpStatus.OK);
    }
    @PostMapping()
    ResponseEntity<CustomerDto> create(@RequestBody @Valid CustomerCreateDto toCreate, BindingResult errors){
        if(errors.hasErrors()){
            throw new TransferValidationException(errors);
        }
        return new ResponseEntity<>(customerFacade.create(toCreate), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    ResponseEntity<CustomerLoginResponseDto> loginCustomer(@RequestBody CustomerLoginDto customerloginDto){
        return new ResponseEntity<>(customerFacade.login(customerloginDto), HttpStatus.OK);
    }
}

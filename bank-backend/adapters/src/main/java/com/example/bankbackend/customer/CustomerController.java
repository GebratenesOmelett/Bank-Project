package com.example.bankbackend.customer;


import com.example.bankbackend.customer.dto.*;
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
    CustomerRoleFacade customerRoleFacade;

    public CustomerController(CustomerFacade customerFacade, CustomerRoleFacade customerRoleFacade) {
        this.customerFacade = customerFacade;
        this.customerRoleFacade = customerRoleFacade;
    }

    @GetMapping("/{email}")
    ResponseEntity<CustomerDto> getByEmail(@PathVariable String email){
        return new ResponseEntity<>(customerFacade.getDtoByEmail(email), HttpStatus.OK);
    }
    @PostMapping()
    ResponseEntity<CustomerAuthDto> create(@RequestBody @Valid CustomerCreateDto toCreate, BindingResult errors){
        if(errors.hasErrors()){
            throw new TransferValidationException(errors);
        }
        return new ResponseEntity<>(customerFacade.create(toCreate), HttpStatus.CREATED);
    }
    @PostMapping("/login")
    ResponseEntity<CustomerAuthDto> login(@RequestBody CustomerLoginDto customerloginDto){
        return new ResponseEntity<>(customerFacade.login(customerloginDto), HttpStatus.OK);
    }

}

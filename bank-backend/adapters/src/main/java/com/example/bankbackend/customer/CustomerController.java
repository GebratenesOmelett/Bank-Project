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

    public CustomerController(CustomerFacade customerFacade) {
        this.customerFacade = customerFacade;
    }

    @GetMapping("/id/{id}")
    ResponseEntity<CustomerDto> getById(@PathVariable int id){
        return new ResponseEntity<>(customerFacade.getDtoById(id), HttpStatus.OK);
    }
    @GetMapping("/email/{email}")
    ResponseEntity<CustomerDto> getByEmail(@PathVariable String email){
        return new ResponseEntity<>(customerFacade.getDtoByEmail(email), HttpStatus.OK);
    }
    @GetMapping("/exist/{email}")
    ResponseEntity<Boolean> CustomerExists(@PathVariable String email){
        return new ResponseEntity<>(customerFacade.customerExists(email), HttpStatus.OK);
    }

    @PostMapping()
    ResponseEntity<CustomerDto> create(@RequestBody @Valid CustomerCreateDto toCreate, BindingResult errors){
        if(errors.hasErrors()){
            throw new TransferValidationException(errors);
        }
        return new ResponseEntity<>(customerFacade.create(toCreate), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    ResponseEntity<CustomerLoginResponseDto> loginCustomerMessage(@RequestBody CustomerLoginDto customerloginDto){
        CustomerLoginResponseDto customerLoginResponseDto = customerFacade.loginMessage(customerloginDto);
        return new ResponseEntity<>(customerLoginResponseDto, HttpStatus.OK);
    }
}

package com.example.bankbackend.transfer;


import com.example.bankbackend.transfer.dto.TransferCreateDto;
import com.example.bankbackend.transfer.dto.TransferDto;
import com.example.bankbackend.transfer.exceptions.TransferValidationException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/transfers")
class TransferController {
    TransferFacade transferFacade;

    public TransferController(TransferFacade transferFacade) {
        this.transferFacade = transferFacade;
    }

    @GetMapping("/{id}")
    ResponseEntity<TransferDto> get(@PathVariable int id) {
        return new ResponseEntity<>(transferFacade.get(id), HttpStatus.OK);
    }

    @PostMapping()
    ResponseEntity<TransferDto> create(@RequestBody @Valid TransferCreateDto toCreate, BindingResult errors) {
        if (errors.hasErrors()){
            throw new TransferValidationException(errors);
        }
        return new ResponseEntity<>(transferFacade.create(toCreate), HttpStatus.CREATED);
    }
}

package com.example.bankbackend.transfer;


import com.example.bankbackend.transfer.dto.TransferCreateDto;
import com.example.bankbackend.transfer.dto.TransferDto;
import com.example.bankbackend.transfer.exceptions.TransferValidationException;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transfers")
class TransferController {
    TransferFacade transferFacade;

    public TransferController(TransferFacade transferFacade) {
        this.transferFacade = transferFacade;
    }

    @GetMapping("/{id}")
    TransferDto get(@PathVariable int id) {
        return transferFacade.get(id);
    }

    @PostMapping()
    void create(@RequestBody @Valid TransferCreateDto toCreate, BindingResult errors) {
        if (errors.hasErrors()){
            throw new TransferValidationException(errors);
        }
        transferFacade.create(toCreate);
    }
}

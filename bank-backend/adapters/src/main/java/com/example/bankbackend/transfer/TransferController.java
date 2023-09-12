package com.example.bankbackend.transfer;


import com.example.bankbackend.transfer.dto.TransferCreateDto;
import com.example.bankbackend.transfer.dto.TransferDto;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
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
    void create(@RequestBody @Valid TransferCreateDto toCreate) {
        System.out.println(toCreate);
        transferFacade.create(toCreate);
    }
}

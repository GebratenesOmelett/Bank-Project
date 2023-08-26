package com.example.bankbackend.transfer;


import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/transfer")
class TransferController {
    TransferFacade transferFacade;

    public TransferController(TransferFacade transferFacade) {
        this.transferFacade = transferFacade;
    }

    @GetMapping("/{id}")
    ResponseEntity<TransferDto> get(@PathVariable int id) {
        return transferFacade.get(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping()
    void create(@RequestBody TransferDto toCreate) {

        transferFacade.create(toCreate);
    }
}

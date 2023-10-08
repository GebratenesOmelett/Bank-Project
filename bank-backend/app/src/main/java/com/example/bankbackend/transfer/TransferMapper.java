package com.example.bankbackend.transfer;

import com.example.bankbackend.transfer.dto.SimpleTransferQueryEntitySnapshot;
import com.example.bankbackend.transfer.dto.TransferDto;
import org.springframework.stereotype.Service;

@Service
public class TransferMapper {
    public TransferDto toTransferDto(SimpleTransferQueryEntitySnapshot transferQueryEntitySnapshot) {
        return TransferDto.create(transferQueryEntitySnapshot.getTitle(),
                transferQueryEntitySnapshot.getFunds(),
                transferQueryEntitySnapshot.getReceiverId(),
                transferQueryEntitySnapshot.getTransferDate(),
                transferQueryEntitySnapshot.getTransferTime());
    }
    public TransferDto toTransferDto(TransferSnapshot transferSnapshot) {
        return TransferDto.create(transferSnapshot.getTitle(),
                transferSnapshot.getFunds(),
                transferSnapshot.getReceiverId(),
                transferSnapshot.getTransferDate(),
                transferSnapshot.getTransferTime());
    }

}

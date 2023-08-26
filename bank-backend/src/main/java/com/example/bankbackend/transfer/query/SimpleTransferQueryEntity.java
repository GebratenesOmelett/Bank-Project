package com.example.bankbackend.transfer.query;

import jakarta.persistence.*;

@Entity
@Table(name = "transfers")
public class SimpleTransferQueryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;

    public SimpleTransferQueryEntity() {
    }
    public SimpleTransferQueryEntity(int id, String title) {
        this.id = id;
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }
}

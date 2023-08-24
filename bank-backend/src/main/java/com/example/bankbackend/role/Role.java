package com.example.bankbackend.role;

import com.example.bankbackend.customer.query.SimpleCustomerQueryDto;
import jakarta.persistence.*;
import org.springframework.lang.NonNull;

import java.util.List;

@Entity
@Table(name = "roles")
class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String role;
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE,
                    CascadeType.DETACH, CascadeType.REFRESH})
    @JoinTable(
            name = "customer_roles",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "customer_id"))
    private List<SimpleCustomerQueryDto> customerList;

    protected Role() {
    }
    public Role(long id, String role, List<SimpleCustomerQueryDto> customerList) {
        this.id = id;
        this.role = role;
        this.customerList = customerList;
    }
}

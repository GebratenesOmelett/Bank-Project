package com.example.bankbackend.customer;

import com.example.bankbackend.transfer.dto.SimpleTransferQueryEntitySnapshot;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import java.math.BigDecimal;
import java.util.Collection;
import java.util.Set;

@Builder
@ToString
@Setter
@Getter
class CustomerSnapshot implements UserDetails {

    private int id;
    private String firstName;
    private String lastName;
    private BigDecimal funds;
    private String password;
    private String email;
    private Set<CustomerRoleSnapshot> roleSet;
    private Set<SimpleTransferQueryEntitySnapshot> transferSet;
    private boolean enabled;

    public CustomerSnapshot() {
    }

    public CustomerSnapshot(int id,
                            String firstName,
                            String lastName,
                            BigDecimal funds,
                            String password,
                            String email,
                            Set<CustomerRoleSnapshot> roleSet,
                            Set<SimpleTransferQueryEntitySnapshot> transferSet,
                            boolean enabled) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.funds = funds;
        this.password = password;
        this.email = email;
        this.roleSet = roleSet;
        this.transferSet = transferSet;
        this.enabled = enabled;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roleSet;
    }
    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    @Override
    public boolean isEnabled() {
        return true;
    }

    void addRole(CustomerRoleSnapshot role){
        roleSet.add(role);
    }
    void addTransfer(SimpleTransferQueryEntitySnapshot transfer){
        transferSet.add(transfer);
    }

}

package com.example.bankbackend.customer;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomerUserDetailService implements UserDetailsService {

    private final CustomerFacade customerFacade;
    public CustomerUserDetailService(CustomerFacade customerFacade) {
        this.customerFacade = customerFacade;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        CustomerSnapshot customer = customerFacade.getByEmail(username).getSnapshot();
        return User.withUsername(customer.getEmail())
                .password(customer.getPassword())
                .authorities(customer.getRoleSet().stream()
                        .map(role -> new SimpleGrantedAuthority(role.getRole()))
                        .toList())
                .build();
    }
}

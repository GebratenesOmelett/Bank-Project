package com.example.bankbackend.customer;

import com.example.bankbackend.customer.CustomerRole;
import com.example.bankbackend.customer.CustomerRoleRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component("roleWarmup")
class Warmup implements ApplicationListener<ContextRefreshedEvent> {

    private final CustomerRoleRepository roleRepository;
    public Warmup(final CustomerRoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }
    @Override
    public void onApplicationEvent(final ContextRefreshedEvent event) {
        if(roleRepository.findByRole("ROLE_USER").isEmpty()){
            roleRepository.save(new CustomerRole("ROLE_USER"));
            roleRepository.save(new CustomerRole("ROLE_ADMIN"));
        }
    }
}

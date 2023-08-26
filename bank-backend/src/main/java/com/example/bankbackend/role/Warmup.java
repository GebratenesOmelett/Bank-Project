package com.example.bankbackend.role;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component("roleWarmup")
class Warmup implements ApplicationListener<ContextRefreshedEvent> {

    private final RoleRepository roleRepository;
    public Warmup(final RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }
    @Override
    public void onApplicationEvent(final ContextRefreshedEvent event) {
        if(roleRepository.findByRole("ROLE_USER").isEmpty()){
            roleRepository.save(new Role("ROLE_USER"));
            roleRepository.save(new Role("ROLE_ADMIN"));
        }
    }
}

package com.example.bankfrontend.customer;

import com.example.bankfrontend.customer.dto.CustomerDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
class CustomerController {
    @GetMapping("/")
    public String homeSite() {
        return "home/Home-Page";
    }

    @GetMapping("/register")
    public String register(Model model){
        model.addAttribute("customer", new CustomerDto());
        return "registration/Registration-Page";
    }

}

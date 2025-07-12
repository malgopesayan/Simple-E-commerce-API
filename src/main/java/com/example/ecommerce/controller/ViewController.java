package com.example.ecommerce.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {
    @GetMapping("/") public String index() { return "index"; }
    @GetMapping("/login") public String loginPage() { return "login"; }
    @GetMapping("/register") public String registerPage() { return "register"; }
    @GetMapping("/products") public String productsPage() { return "products"; }
    @GetMapping("/cart") public String cartPage() { return "cart"; }
}

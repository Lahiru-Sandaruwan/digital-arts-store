package com.digital.art.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {
    @GetMapping(value = "/")
    public String homePage() {
        return "index.html";
    }

    @GetMapping(value = "/home")
    public String home2Page() {
        return "index.html";
    }

    @GetMapping(value = "/login")
    public String login() {
        return "login.html";
    }

    @GetMapping(value = "/signUp")
    public String signUp() {
        return "sign-up.html";
    }

    @GetMapping(value = "/addItem")
    public String addItem() {
        return "add-item.html";
    }

}

package com.umar.security.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/app")
    public String getName(){
        return "Hi Umar";
    }
}

package com.example;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TripController {

    @GetMapping("/tripbuddy")
    public String showTripForm() {
        return "tripbuddy.html";
    }
}


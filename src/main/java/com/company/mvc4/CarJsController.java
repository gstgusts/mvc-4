package com.company.mvc4;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/pages")
public class CarJsController {
    @GetMapping("/cars")
    public String getCars(Model model) {
        return "cars_js";
    }
}

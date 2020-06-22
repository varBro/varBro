package com.varbro.varbro.controller.production;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProductionController {

    @GetMapping("/production")
    public String productionIndex() {
        return "production/index";
    }
}

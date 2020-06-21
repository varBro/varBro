package com.varbro.varbro.controller.production;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BeerController {

    @GetMapping("/production/beers")
    public String beers() {
        return "production/beers";
    }
}

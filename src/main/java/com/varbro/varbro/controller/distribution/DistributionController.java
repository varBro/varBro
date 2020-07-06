package com.varbro.varbro.controller.distribution;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DistributionController {
    @GetMapping("/distribution")
    public String financeIndex() {
        return "distribution/index";
    }
}

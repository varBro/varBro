package com.varbro.varbro.controller.finance;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InvoiceController {
    @GetMapping("/finance/invoice/add")
    public static String add(){
        return "finance/invoice/add";
    }
}

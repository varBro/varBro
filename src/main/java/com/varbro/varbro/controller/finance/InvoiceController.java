package com.varbro.varbro.controller.finance;

import com.varbro.varbro.model.finance.Invoice;
import com.varbro.varbro.service.finance.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class InvoiceController {
    @Autowired
    private InvoiceService invoiceService;

    @GetMapping("/finance/invoice")
    public String index() {
        return "finance/invoice/index";
    }

    @GetMapping("/finance/invoice/add")
    public String add(Model model) {
        model.addAttribute("invoice", new Invoice());
        return "finance/invoice/add";
    }

    @PostMapping("/finance/invoice/add")
    public String add(@ModelAttribute Invoice invoice) {
        //invoiceService.saveInvoice(invoice);
        return "redirect:/finance/invoice";
    }
}

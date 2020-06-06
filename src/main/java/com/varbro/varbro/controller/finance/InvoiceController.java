package com.varbro.varbro.controller.finance;

import com.varbro.varbro.model.finance.Contractor;
import com.varbro.varbro.model.finance.Invoice;
import com.varbro.varbro.service.finance.ContractorService;
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

    @Autowired
    private ContractorService contractorService;

    @GetMapping("/finance/invoice")
    public String index() {
        return "finance/invoice/index";
    }

    @GetMapping("/finance/invoice/add")
    public String add(Model model) {
        model.addAttribute("invoice", new Invoice());
        model.addAttribute("contractors",contractorService.getContractors());
        return "finance/invoice/add";
    }

    @PostMapping("/finance/invoice/add")
    public String add(@ModelAttribute Invoice invoice) {
        invoiceService.saveInvoice(invoice);
        return "redirect:/finance/invoice";
    }

    @GetMapping("/finance/invoice/add/contractor")
    public String addContractor(Model model) {
        model.addAttribute("contractor", new Contractor());
        return "finance/invoice/add-contractor";
    }

    @PostMapping("/finance/invoice/add/contractor")
    public String add(@ModelAttribute Contractor contractor) {
        contractorService.saveContractor(contractor);
        return "redirect:/finance/invoice/add";
    }
}

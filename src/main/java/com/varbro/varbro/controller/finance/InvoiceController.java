package com.varbro.varbro.controller.finance;

import com.varbro.varbro.model.finance.ContractorFinance;
import com.varbro.varbro.model.finance.Invoice;
import com.varbro.varbro.model.finance.InvoiceProduct;
import com.varbro.varbro.model.finance.ProductFinance;
import com.varbro.varbro.service.finance.ContractorServiceFinance;
import com.varbro.varbro.service.finance.InvoiceService;
import com.varbro.varbro.service.finance.ProductServiceFinance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class InvoiceController {
    @Autowired
    private InvoiceService invoiceService;

    @Autowired
    private ContractorServiceFinance contractorServiceFinance;

    @Autowired
    private ProductServiceFinance productServiceFinance;

    @GetMapping("/finance/invoice")
    public String index(Model model) {
        model.addAttribute("invoices", invoiceService.getInvoices());
        return "finance/invoice/index";
    }

    @GetMapping("/finance/invoice/add")
    public String add(@ModelAttribute Invoice invoice, Model model) {
        model.addAttribute("contractors", contractorServiceFinance.getContractors());
        model.addAttribute("products", productServiceFinance.getProducts());
        return "finance/invoice/add";
    }

    @PostMapping(value = "/finance/invoice/add", params = "addProduct")
    public String addRow(@ModelAttribute Invoice invoice, Model model)
    {
        invoice.addProduct(new InvoiceProduct());
        model.addAttribute("contractors", contractorServiceFinance.getContractors());
        model.addAttribute("products", productServiceFinance.getProducts());
        return "finance/invoice/add";
    }

    @PostMapping("/finance/invoice/add")
    public String add(@ModelAttribute Invoice invoice) {
        invoiceService.saveInvoice(invoice);
        return "redirect:/finance/invoice/" + invoice.getId() + "/show";
    }

    @GetMapping("/finance/invoice/{id}/show")
    public String showInvoice(@PathVariable("id") long id, Model model)
    {
        Invoice invoice = invoiceService.getOne(id);
        model.addAttribute("invoice", invoice);
        return "finance/invoice/show";
    }

    @GetMapping("/finance/invoice/add/contractor")
    public String addContractor(Model model) {
        model.addAttribute("contractor", new ContractorFinance());
        return "finance/invoice/add-contractor";
    }

    @PostMapping("/finance/invoice/add/contractor")
    public String add(@ModelAttribute ContractorFinance contractorFinance) {
        contractorServiceFinance.saveContractor(contractorFinance);
        return "redirect:/finance/invoice/add";
    }

    @GetMapping("/finance/invoice/add/product")
    public String addProduct(Model model) {
        model.addAttribute("product", new ProductFinance());
        return "finance/invoice/add-product";
    }

    @PostMapping("/finance/invoice/add/product")
    public String add(@ModelAttribute ProductFinance productFinance) {
        productServiceFinance.saveProduct(productFinance);
        return "redirect:/finance/invoice/add";
    }
}

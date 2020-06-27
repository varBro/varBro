package com.varbro.varbro.controller.finance;

import com.varbro.varbro.model.finance.Contractor;
import com.varbro.varbro.model.finance.Invoice;
import com.varbro.varbro.model.finance.InvoiceProduct;
import com.varbro.varbro.model.finance.Product;
import com.varbro.varbro.service.finance.ContractorService;
import com.varbro.varbro.service.finance.InvoiceProductService;
import com.varbro.varbro.service.finance.InvoiceService;
import com.varbro.varbro.service.finance.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class InvoiceController {
    @Autowired
    private InvoiceService invoiceService;

    @Autowired
    private ContractorService contractorService;

    @Autowired
    private ProductService productService;

    @Autowired
    private InvoiceProductService invoiceProductService;

    @GetMapping("/finance/invoice")
    public String index() {
        return "finance/invoice/index";
    }

    @GetMapping("/finance/invoice/add")
    public String add(@ModelAttribute Invoice invoice, Model model) {
        model.addAttribute("contractors", contractorService.getContractors());
        model.addAttribute("products", productService.getProducts());
        return "finance/invoice/add";
    }

    @PostMapping(value = "/finance/invoice/add", params = "addProduct")
    public String addRow(@ModelAttribute Invoice invoice, Model model)
    {
        invoice.addProduct(new InvoiceProduct());
        model.addAttribute("contractors", contractorService.getContractors());
        model.addAttribute("products", productService.getProducts());
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
        model.addAttribute("contractor", new Contractor());
        return "finance/invoice/add-contractor";
    }

    @PostMapping("/finance/invoice/add/contractor")
    public String add(@ModelAttribute Contractor contractor) {
        contractorService.saveContractor(contractor);
        return "redirect:/finance/invoice/add";
    }

    @GetMapping("/finance/invoice/add/product")
    public String addProduct(Model model) {
        model.addAttribute("product", new Product());
        return "finance/invoice/add-product";
    }

    @PostMapping("/finance/invoice/add/product")
    public String add(@ModelAttribute Product product) {
        productService.saveProduct(product);
        return "redirect:/finance/invoice/add";
    }
}

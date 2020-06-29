package com.varbro.varbro.controller.production;

import com.varbro.varbro.model.User;
import com.varbro.varbro.model.production.Vat;
import com.varbro.varbro.service.UserService;
import com.varbro.varbro.service.production.VatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class VatController {

    @Autowired
    VatService vatService;

    @Autowired
    UserService userService;

    @GetMapping("/production/vats")
    public String showAll(Model model) {
        model.addAttribute("vats", vatService.getVatsOrderedByCapacity());
        return "production/vats";
    }

    @GetMapping("/production/vat/{id}")
    public String showVat(@PathVariable("id") long id, Model model) {
        Vat vat = vatService.getVatById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid vat Id:" + id));

        model.addAttribute("vat", vat);
        model.addAttribute("users", userService.getProductionWorkers());

        return "production/vat";
    }
}

package com.varbro.varbro.controller.production;

import com.varbro.varbro.model.User;
import com.varbro.varbro.model.production.Vat;
import com.varbro.varbro.service.UserService;
import com.varbro.varbro.service.production.BeerService;
import com.varbro.varbro.service.production.VatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class VatController {

    @Autowired
    VatService vatService;

    @Autowired
    UserService userService;

    @Autowired
    BeerService beerService;

    @GetMapping("/production/vats")
    public String showAll(Model model) {
        model.addAttribute("vats", vatService.getVatsOrderedByCapacity());
        return "production/vat/vats";
    }

    @GetMapping("/production/vat/{id}")
    public String showVat(@PathVariable("id") long id, Model model) {
        Vat vat = vatService.getVatById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid vat Id:" + id));

        model.addAttribute("vat", vat);

        return "production/vat/vat";
    }

    @GetMapping("/production/vat/{id}/assign")
    public String assignVat(@PathVariable("id") long id, Model model) {
        Vat vat = vatService.getVatById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid vat Id:" + id));

        List<User> users = userService.getProductionWorkers();

        for( Vat iter : vatService.getVats()) {
            if( iter.getUser() != null) {
                users.remove(iter.getUser());
            }
        }

        model.addAttribute("vat", vat);
        model.addAttribute("users", users);
        model.addAttribute("beers", beerService.getBeers());

        return "production/vat/assign-vat";
    }

    @RequestMapping(value = "/production/vat/{id}/assign", params = "submit")
    public ModelAndView vatAssigned(@PathVariable("id") long id,
                                    @RequestParam("assignee") String assignee,
                                    @RequestParam("producedBeer") String beer, Model model) {

        Vat vat = vatService.getVatById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid vat Id:" + id));

        vat.setUser(userService.getUserByNameAndSurname(assignee.split(" ")[0], assignee.split(" ")[1]));
        vat.setBeer(beerService.getBeerByName(beer));
        vat.setProcessPhase(Vat.ProcessPhase.NOT_STARTED);

        vatService.saveVat(vat);
        model.addAttribute("vat_assigned", vat);
        return new ModelAndView("redirect:/production/vat/" + vat.getId());
    }
}

package com.varbro.varbro.controller.production;

import com.varbro.varbro.model.User;
import com.varbro.varbro.model.production.Vat;
import com.varbro.varbro.service.UserService;
import com.varbro.varbro.service.production.BeerService;
import com.varbro.varbro.service.production.VatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email = ((UserDetails)principal).getUsername();

        model.addAttribute("vat", vat);
        model.addAttribute("user", userService.getUserByEmail(email));

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

    /*@GetMapping("/production/vat/{id}/update")
    public String updateProcess(@PathVariable("id") long id, Model model) {
        System.out.println("===========jestem w gecie===========");
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email = ((UserDetails)principal).getUsername();

        Vat vat = vatService.getVatById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid vat Id:" + id));

        model.addAttribute("vat", vat);
        model.addAttribute("user", userService.getUserByEmail(email));
        return "production/vat/vat";
    }*/

    @PostMapping("/production/vat/{id}/update")
    public String updateProcess(@PathVariable("id") long id, Model model) {

        System.out.println("===========Jestem w poscie=========");
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email = ((UserDetails)principal).getUsername();

        Vat vat = vatService.getVatById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid vat Id:" + id));

        if(vat.getProcessPhase() == Vat.ProcessPhase.PACKAGING) {
            vat.setProcessPhase(Vat.ProcessPhase.values()[0]);
            vat.setLastUpdated();
            vatService.saveVat(vat);
            return "redirect:/production/vats";
        } else {
            vat.setProcessPhase(vat.getProcessPhase().nextPhase());
        }
        System.out.println("=========faza procesu: " + vat.getProcessPhase().displayName());
        vat.setLastUpdated();
        vatService.saveVat(vat);

        model.addAttribute("vat", vat);
        model.addAttribute("user", userService.getUserByEmail(email));
        return "redirect:/production/vat/" + vat.getId();
    }
}

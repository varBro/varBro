package com.varbro.varbro.controller.production;

import com.varbro.varbro.model.User;
import com.varbro.varbro.model.production.Batch;
import com.varbro.varbro.model.production.Vat;
import com.varbro.varbro.service.UserService;
import com.varbro.varbro.service.production.BatchService;
import com.varbro.varbro.service.production.BeerService;
import com.varbro.varbro.service.production.VatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.util.List;

@Controller
public class VatController {

    @Autowired
    VatService vatService;

    @Autowired
    UserService userService;

    @Autowired
    BeerService beerService;

    @Autowired
    BatchService batchService;

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

    @PostMapping("/production/vat/{id}/update")
    public String updateProcess(@PathVariable("id") long id, Model model) {


        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email = ((UserDetails)principal).getUsername();

        Vat vat = vatService.getVatById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid vat Id:" + id));

        if (vat.getProcessPhase() == Vat.ProcessPhase.NOT_STARTED) {
            vat.setProcessPhase(vat.getProcessPhase().nextPhase());
            vat.setStartTime(LocalDate.now());
            vat.setLastUpdated(LocalDate.now());

        } else if (vat.getProcessPhase() == Vat.ProcessPhase.PACKAGING) {
            vat.setProcessPhase(Vat.ProcessPhase.values()[0]);
            Batch batch = new Batch(vat);
            batchService.saveBatch(batch);
            vat.resetVat();
            vatService.saveVat(vat);


            /*tutej trzeba zrobic dodawanie piwa do magazynu*/

            return "redirect:/production/vats";

        } else {
            vat.setProcessPhase(vat.getProcessPhase().nextPhase());
            vat.setLastUpdated(LocalDate.now());
        }

        vatService.saveVat(vat);

        model.addAttribute("vat", vat);
        model.addAttribute("user", userService.getUserByEmail(email));
        return "redirect:/production/vat/" + vat.getId();
    }
}

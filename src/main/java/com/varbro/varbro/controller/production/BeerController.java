package com.varbro.varbro.controller.production;

import com.varbro.varbro.model.User;
import com.varbro.varbro.model.production.Beer;
import com.varbro.varbro.service.production.BeerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BeerController {

    @Autowired
    BeerService beerService;

    @GetMapping("/production/beers")
    public String showAll(Model model) {
        model.addAttribute("beers", beerService.getBeersOrderedByName());
        return "production/beers";
    }

    @PostMapping("/production/beers")
    public String showAll(@RequestParam(value = "name", required = false) String name, Model model) {
        if (!name.equals("")) {
            model.addAttribute("beers", beerService.getBeersLikeName(name));
        } else
            model.addAttribute("beers", beerService.getBeersOrderedByName());
        model.addAttribute("name", name);
        return "production/beers";
    }

    @GetMapping("/production/beer/{id}")
    public String showBeer(@PathVariable("id") long id, Model model) {
        Beer beer = beerService.getBeerById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid beer Id:" + id));

        model.addAttribute("beer", beer);
        return "production/recipe";
    }

}

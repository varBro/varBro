package com.varbro.varbro.controller.production;

import com.varbro.varbro.model.finance.Expense;
import com.varbro.varbro.model.logistics.Order;
import com.varbro.varbro.model.production.Beer;
import com.varbro.varbro.model.production.BeerIngredient;
import com.varbro.varbro.service.production.BeerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

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

    @GetMapping("/production/add-new-recipe")
    public String addBeer(Model model) {
        model.addAttribute("beer", new Beer());
        return "production/add-new-recipe";
    }

//    @PostMapping("/production/add-new-recipe")
//    public String addBeerSubmit(@Valid @ModelAttribute("beer") Beer beer, BindingResult bindingResult) {
//        if (!bindingResult.hasErrors()) {
//            beerService.saveBeer(beer);
//        }
//        return "production/index";
//    }
}

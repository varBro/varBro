package com.varbro.varbro.controller.production;

import com.varbro.varbro.model.finance.Expense;
import com.varbro.varbro.model.logistics.Order;
import com.varbro.varbro.model.logistics.OrderItem;
import com.varbro.varbro.model.logistics.Product;
import com.varbro.varbro.model.production.Beer;
import com.varbro.varbro.model.production.BeerIngredient;
import com.varbro.varbro.service.logistics.ProductService;
import com.varbro.varbro.service.production.BeerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;

@Controller
public class BeerController {

    @Autowired
    BeerService beerService;

    @Autowired
    ProductService productService;

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
    public String addBeer(@ModelAttribute Beer beer, Model model) {
        List<BeerIngredient> beerIngredients = new ArrayList<>();
        beerIngredients.add(new BeerIngredient());
        beer.setBeerIngredients(beerIngredients);
        model.addAttribute("beer", beer);
        return "production/add-new-recipe";
    }

    @PostMapping(value = "/production/add-new-recipe", params = "addRow")
    public String addRow(@ModelAttribute Beer beer, Model model)
    {
        beer.getBeerIngredients().add(new BeerIngredient());
        model.addAttribute("beer", beer);
        return "production/add-new-recipe";
    }

//    @RequestMapping(value = "/production/add-new-recipe", params = "removeRow")
//    public String removeRow(@ModelAttribute Beer beer, Model model, HttpServletRequest req)
//    {
//        Integer rowId = Integer.valueOf(req.getParameter("removeRow"));
//        beer.getBeerIngredients().remove(rowId.intValue());
//        model.addAttribute("beer", beer);
//        return "production/add-new-recipe";
//    }

//    @RequestMapping(value = "/production/add-new-recipe", params = "save")
//    public String saveBeer(@Valid @ModelAttribute Beer beer, SessionStatus status, BindingResult bindingResult)
//    {
//        if(!bindingResult.hasErrors()) {
//            Set<BeerIngredient> newBeer = new HashSet<>();
//            for (BeerIngredient beerIngredient : beer.getBeerIngredients()) {
//                Product p = productService.getProductByName(beerIngredient.getProduct().getName());
//                if (p != null) {
//                    newBeer.add(new BeerIngredient(p, beerIngredient.getQuantity(), beerIngredient.getIngredientType()));
//                }
//            }
//            beerService.saveBeer(new Beer(beer.getName(),beer.getRecipeDescription(), (BeerIngredient) newBeer));
//            status.setComplete();
//        }
//        return "redirect:/default";
//    }
}

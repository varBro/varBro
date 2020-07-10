package com.varbro.varbro.controller.production;


import com.varbro.varbro.model.distribution.BeerStock;
import com.varbro.varbro.model.logistics.Product;
import com.varbro.varbro.model.logistics.Stock;
import com.varbro.varbro.model.production.Beer;
import com.varbro.varbro.model.production.BeerIngredient;
import com.varbro.varbro.service.distribution.BeerStockService;
import com.varbro.varbro.service.logistics.ProductService;
import com.google.common.collect.*;
import com.varbro.varbro.service.logistics.StockService;
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
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;



@Controller
public class BeerController {

    @Autowired
    BeerService beerService;

    @Autowired
    ProductService productService;

    @Autowired
    StockService productStockService;

    @Autowired
    BeerStockService beerStockService;

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
    public String showBeer(@PathVariable("id") long id, ModelMap map) {
        Beer beer = beerService.getBeerById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid beer Id:" + id));

        SetMultimap<String, HashMap<String, Float>> beerIngredients = HashMultimap.create();
        for( BeerIngredient ingredient : beer.getBeerIngredients()) {
            HashMap<String, Float> ingredients = new HashMap<>();
            ingredients.put(ingredient.getIngredient().getName(), ingredient.getQuantity());
            beerIngredients.put(ingredient.getIngredientType().toString(), ingredients);
        }

        map.addAttribute("ingredients", beerIngredients);
        map.addAttribute("beer", beer);

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

    @PostMapping(value = "/production/add-new-recipe", params = "removeRow")
    public String removeRow(@ModelAttribute Beer beer, Model model, HttpServletRequest req)
    {
        Integer rowId = Integer.valueOf(req.getParameter("removeRow"));
        beer.getBeerIngredients().remove(rowId.intValue());
        model.addAttribute("beer", beer);
        return "production/add-new-recipe";
    }

    @PostMapping(value = "/production/add-new-recipe", params = "save")
    public String saveBeer(@Valid @ModelAttribute Beer beer, SessionStatus status, BindingResult bindingResult)
    {
        if(!bindingResult.hasErrors()) {
            for (BeerIngredient beerIngredient : beer.getBeerIngredients()) {
                Product product = productService.getProductByName(beerIngredient.getProduct().getName());
                if (product == null) {
                    beerIngredient.getProduct().setUnit(Product.Unit.KG);
                    beerIngredient.getProduct().setIngredient(true);
                    productService.saveProduct(beerIngredient.getProduct());
                    productStockService.saveStock(new Stock(productService.getProductByName(beerIngredient.getProduct().getName()), 0));
                }
                else {
                    beerIngredient.setProduct(product);
                }
            }
            beerService.saveBeer(new Beer(beer.getName(),beer.getRecipeDescription(), beer.getBeerIngredients().toArray(new BeerIngredient[beer.getBeerIngredients().size()])));
            beerStockService.saveStock(new BeerStock(beerService.getBeerByName(beer.getName())));
            status.setComplete();
        }
        return "redirect:/default";
    }
}

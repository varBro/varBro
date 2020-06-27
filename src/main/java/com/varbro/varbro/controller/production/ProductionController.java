package com.varbro.varbro.controller.production;

import com.varbro.varbro.model.production.Beer;
import com.varbro.varbro.model.production.Request;
import com.varbro.varbro.service.production.BeerService;
import com.varbro.varbro.service.production.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ProductionController {

    @Autowired
    BeerService beerService;

    @Autowired
    RequestService requestService;

    @GetMapping("/production")
    public String productionIndex() {
        return "production/index";
    }

    @GetMapping("/production/request/add")
    public String showRequestForm(Model model)
    {
        model.addAttribute("beers", beerService.getBeersOrderedByName());
        model.addAttribute("request", new Request());

        return "production/request/add";
    }

    @PostMapping("/production/request/add")
    public String addRequest(@ModelAttribute Request request)
    {
        Beer beer = beerService.getBeerById(request.getBeer().getId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid beer Id:" + request.getBeer().getId()));

        requestService.save(new Request(beer, request.getAmount()));

        return "redirect:/default";
    }
}

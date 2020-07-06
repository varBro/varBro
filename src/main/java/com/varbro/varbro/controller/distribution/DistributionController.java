package com.varbro.varbro.controller.distribution;

import com.varbro.varbro.model.distribution.BeerStock;
import com.varbro.varbro.service.distribution.BeerStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DistributionController {

    @Autowired
    BeerStockService beerStockService;

    @GetMapping("/distribution")
    public String distributionIndex() {
        return "distribution/index";
    }

    @GetMapping("/distribution/stock")
    public String showStock(Model model) {
        model.addAttribute("stocks", beerStockService.getStocks());
        return "distribution/stock/show";
    }

    @GetMapping("/distribution/stock/edit")
    public String editStockForm(Model model) {
        model.addAttribute("beerStocks", beerStockService.getStocks());
        model.addAttribute("stock", new BeerStock());
        model.addAttribute("operation", "SUBSTITUTE");
        return "distribution/stock/edit";
    }

    @PostMapping("/distribution/stock/edit")
    public String editStock(@RequestParam(value = "operation") String operation, @ModelAttribute BeerStock stock) {
        if(operation.equals("ADD")) {
            beerStockService.addToStock(stock.getBeer().getName(), stock.getQuantity());
        }
        else if(operation.equals("SUBSTITUTE")) {
            beerStockService.substituteFromStock(stock.getBeer().getName(), stock.getQuantity());
        }
        return "redirect:/distribution/stock/edit";
    }

}

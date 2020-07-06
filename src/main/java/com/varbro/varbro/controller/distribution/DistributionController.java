package com.varbro.varbro.controller.distribution;

import com.varbro.varbro.service.distribution.BeerStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import sun.text.normalizer.NormalizerBase;

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
        model.addAttribute("stock", beerStockService.getStocks());
        return "distribution/stock";
    }
}

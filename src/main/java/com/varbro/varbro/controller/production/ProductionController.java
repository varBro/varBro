package com.varbro.varbro.controller.production;

import com.varbro.varbro.model.logistics.Stock;
import com.varbro.varbro.model.production.*;
import com.varbro.varbro.service.logistics.StockService;
import com.varbro.varbro.service.production.BatchService;
import com.varbro.varbro.service.production.BeerService;
import com.varbro.varbro.service.production.RequestService;
import com.varbro.varbro.service.production.VatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Controller
public class ProductionController {

    @Autowired
    BeerService beerService;

    @Autowired
    RequestService requestService;

    @Autowired
    StockService stockService;

    @Autowired
    BatchService batchService;

    @Autowired
    VatService vatService;

    @GetMapping("/production")
    public String productionIndex() {
        return "production/index";
    }

//    @GetMapping("/production/request/add")
//    public String showRequestForm(Model model) {
//        model.addAttribute("beers", beerService.getBeersOrderedByName());
//        model.addAttribute("request", new Request());
//
//        return "production/request/add";
//    }
//
//    @PostMapping("/production/request/add")
//    public String addRequest(@ModelAttribute Request request) {
//        Beer beer = beerService.getBeerById(request.getBeer().getId())
//                .orElseThrow(() -> new IllegalArgumentException("Invalid beer Id:" + request.getBeer().getId()));
//        Request requestToSave = requestService.updateRequestAvailability(new Request(beer, request.getAmount()));
//        requestService.save(requestToSave);
//
//        return "redirect:/default";
//    }

    @GetMapping("/production/request/list")
    public String showRequestsList(Model model) {
        model.addAttribute("requests", requestService.getPendingRequestsOrderedByTime());
        model.addAttribute("requests_ordered", requestService.getOrderedRequestsOrderedByTime());
        return "production/request/list";
    }

    @GetMapping("/production/request/{id}")
    public String showRequest(@PathVariable("id") long id, Model model) {

        Request request = requestService.getRequestById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid request Id:" + id));
        Set<BeerIngredient> ingredients = new LinkedHashSet(request.getBeer().getBeerIngredients());
        List<Integer> percentages = new ArrayList<>();
        for (BeerIngredient beerIngredient : ingredients) {
            Stock s = stockService.getStockByProductId(beerIngredient.getIngredient().getId())
                    .orElseThrow(() -> new IllegalArgumentException("Invalid product Id:" + beerIngredient.getIngredient().getId()));
            percentages.add(getPercentage(s.getQuantity(), request.getAmount() / 1000.0 * beerIngredient.getQuantity()));
        }
        double bottlesCount = (double) stockService.getQuantityOfBottles()
                .orElseThrow(() -> new IllegalArgumentException("No bottles found in stock"));
        model.addAttribute("bottle_percentage", getPercentage(bottlesCount, request.getAmount() * 2));
        model.addAttribute("ingredients", ingredients);
        model.addAttribute("percentages", percentages);
        model.addAttribute("request", request);
        return "production/request/show";
    }

    public int getPercentage(double inStock, double needed) {
        double percentage = inStock / needed * 100;
        if (Math.floor(percentage) < 100)
            return (int) Math.floor(percentage);
        else
            return 100;
    }

    @GetMapping("/production/request/{id}/ready")
    public String setRequestToReady(@PathVariable("id") long id) {
        Request request = requestService.getRequestById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid request Id:" + id));
        requestService.requestReady(request);
        requestService.updateRequestsAvailability();

        return "redirect:/production/request/list";
    }

    @GetMapping("/production/manager/stats")
    public String productionStats(Model model) {
        LocalDate today = LocalDate.now();
        List<Batch> batches = batchService.getBatchesByMonthAndYearOrderedByDay(today.getMonthValue(), today.getYear());

        Map<Integer, Integer> dayToBeerAmount = batches.stream().collect(
                Collectors.groupingBy(
                        batch -> batch.getDate().getDayOfMonth(),
                        Collectors.summingInt(Batch::getBeerAmountInLiters)));

        IntStream.rangeClosed(1, today.lengthOfMonth()).forEach(i -> dayToBeerAmount.putIfAbsent(i, 0));

        model.addAttribute("day_to_beer_amount", dayToBeerAmount);

        Stream.of(dayToBeerAmount).forEach(System.out::println);
        return "production/manager/stats";
    }

}

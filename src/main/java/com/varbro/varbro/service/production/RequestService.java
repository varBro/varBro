package com.varbro.varbro.service.production;

import com.varbro.varbro.model.User;
import com.varbro.varbro.model.logistics.Stock;
import com.varbro.varbro.model.production.Beer;
import com.varbro.varbro.model.production.BeerIngredient;
import com.varbro.varbro.model.production.Request;
import com.varbro.varbro.repository.production.BeerRepository;
import com.varbro.varbro.repository.production.RequestRepository;
import com.varbro.varbro.service.logistics.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RequestService {

    @Autowired
    RequestRepository requestRepository;

    @Autowired
    BeerService beerService;

    @Autowired
    StockService stockService;

    public void save(Request request) {
        requestRepository.save(request);
    }

    public void saveAll(List<Request> requests) {
        requestRepository.saveAll(requests);
    }

    public void deleteAll() {
        requestRepository.deleteAll();
    }

    public Iterable<Request> getRequests() {return requestRepository.findAll();}

    public Iterable<Request> getPendingRequests() {return requestRepository.findByStatus(Request.Status.PENDING);}

    public Iterable<Request> getPendingRequestsOrderedByTime() {
        return requestRepository.findByStatusOrderByTime(Request.Status.PENDING);
    }

    public Optional<Request> getRequestById(Long id) {

        return requestRepository.findById(id);
    }

    public void updateRequestsAvailability() {
        Iterable<Request> requests = this.getPendingRequests();
        for (Request request: requests) {
            this.save(this.updateRequestAvailability(request));
        }
    }

    public Request updateRequestAvailability(Request request) {
        Beer beer = beerService.getBeerById(request.getBeer().getId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid beer Id:" + request.getBeer().getId()));
        double multiplier = request.getAmount() / 1000.0;
        request.setEnoughIngredients(true);
        for (BeerIngredient beerIngredient : beer.getBeerIngredients()) {
            double quantity = beerIngredient.getQuantity() * multiplier;
            Stock s = stockService.getStockByProductId(beerIngredient.getIngredient().getId())
                    .orElseThrow(() -> new IllegalArgumentException("Invalid product Id:" + beerIngredient.getIngredient().getId()));
            if (s.getQuantity() < quantity) {
                request.setEnoughIngredients(false);
                break;
            }
        }
        double bottlesCount = (double) stockService.getQuantityOfBottles()
                .orElseThrow(() -> new IllegalArgumentException("No bottles found in stock"));
        if(bottlesCount < request.getAmount() * 2)
            request.setEnoughIngredients(false);
        return request;
    }
}

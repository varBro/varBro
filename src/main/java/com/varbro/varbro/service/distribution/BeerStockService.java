package com.varbro.varbro.service.distribution;


import com.varbro.varbro.model.distribution.BeerStock;
import com.varbro.varbro.model.logistics.Stock;
import com.varbro.varbro.repository.distribution.BeerStockRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class BeerStockService {

    @Autowired
    BeerStockRepository beerStockRepository;

    public Optional<BeerStock> getStockByBeerId(long id) {
        return beerStockRepository.findByBeerId(id);
    }

    public void saveStock(BeerStock beer) {
        beerStockRepository.save(beer);
    }

    public void saveStocks(List<BeerStock> beers) {
        beerStockRepository.saveAll(beers);
    }

    public Iterable<BeerStock> getStocks() {
        return beerStockRepository.findAll();
    }

    public void deleteAll() {
        beerStockRepository.deleteAll();
    }

    public void addToStock(long id, double quantity) {
        BeerStock beerStock = beerStockRepository.findByBeerId(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid beer Id:" + id));
        beerStock.add(quantity);
        beerStockRepository.save(beerStock);
    }

    public void substituteFromStock(long id, double quantity) {
        BeerStock beerStock = beerStockRepository.findByBeerId(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid beer Id:" + id));
        beerStock.substitute(quantity);
        beerStockRepository.save(beerStock);
    }

    public void addToStock(String beerName, double quantity) {
        BeerStock beerStock = beerStockRepository.findByBeerName(beerName)
                .orElseThrow(() -> new IllegalArgumentException("Invalid beer name:" + beerName));
        beerStock.add(quantity);
        beerStockRepository.save(beerStock);
    }

    public void substituteFromStock(String beerName, double quantity) {
        BeerStock beerStock = beerStockRepository.findByBeerName(beerName)
                .orElseThrow(() -> new IllegalArgumentException("Invalid beer name:" + beerName));
        beerStock.substitute(quantity);
        beerStockRepository.save(beerStock);
    }
}
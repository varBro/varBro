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
}
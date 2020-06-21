package com.varbro.varbro.service.production;

import com.varbro.varbro.model.User;
import com.varbro.varbro.model.production.Beer;
import com.varbro.varbro.repository.production.BeerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BeerService {

    @Autowired
    BeerRepository beerRepository;

    public void saveBeer(Beer beer) {

        beerRepository.save(beer);
    }

    public void saveBeers(List<Beer> beers) {

        beerRepository.saveAll(beers);
    }

    public void deleteAll() {

        beerRepository.deleteAll();
    }
}

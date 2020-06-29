package com.varbro.varbro.service.production;

import com.varbro.varbro.model.production.Beer;
import com.varbro.varbro.repository.production.BeerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public Iterable<Beer> getBeersOrderedByName() {
        return beerRepository.findAllByOrderByName();
    }

    public Iterable<Beer> getBeersLikeName(String name) {
        return beerRepository.findByNameContainingIgnoreCase(name);
    }

    public Optional<Beer> getBeerById(Long id) {

        return beerRepository.findById(id);
    }
}

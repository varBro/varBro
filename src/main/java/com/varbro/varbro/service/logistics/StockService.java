package com.varbro.varbro.service.logistics;


import com.varbro.varbro.model.logistics.Stock;
import com.varbro.varbro.repository.logistics.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StockService {

    @Autowired
    StockRepository stockRepository;

    public Optional<Stock> getStockByProductId(long id) { return stockRepository.findByProductId(id); }

    public void saveStock(Stock product) {
        stockRepository.save(product);
    }

    public void saveStocks(List<Stock> products) { stockRepository.saveAll(products); }

    public Iterable<Stock> getStocks() {
        return stockRepository.findAll();
    }

    public void deleteAll() { stockRepository.deleteAll(); }
}

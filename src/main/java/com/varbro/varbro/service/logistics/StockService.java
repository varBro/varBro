package com.varbro.varbro.service.logistics;


import com.varbro.varbro.model.distribution.BeerStock;
import com.varbro.varbro.model.logistics.Order;
import com.varbro.varbro.model.logistics.OrderItem;
import com.varbro.varbro.model.logistics.Product;
import com.varbro.varbro.model.logistics.Stock;
import com.varbro.varbro.model.production.BeerIngredient;
import com.varbro.varbro.model.production.Request;
import com.varbro.varbro.repository.logistics.StockRepository;
import com.varbro.varbro.service.production.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class StockService {

    @Autowired
    StockRepository stockRepository;

    @Autowired
    ProductService productService;

    @Autowired
    RequestService requestService;

    public Optional<Stock> getStockByProductId(long id) { return stockRepository.findByProductId(id); }

    public void saveStock(Stock product) {
        stockRepository.save(product);
    }

    public void saveStocks(List<Stock> products) { stockRepository.saveAll(products); }

    public Iterable<Stock> getStocks() {
        return stockRepository.findAll();
    }

    public Optional<Object> getQuantityOfProductById(long id) {return stockRepository.findQuantityById(id); }

    public Optional<Object> getQuantityOfBottles() {return stockRepository.findBottlesQuantity(); }

    public List<Stock> getIngredientStocks() {return stockRepository.findIngredients(); }

    public List<Stock> getNotIngredientStocks() {return stockRepository.findNotIngredients(); }

    public void deleteAll() { stockRepository.deleteAll(); }

    public void updateStocksAdd(Order order) {
        for (OrderItem orderItem : order.getOrderItems() ) {
            System.out.println(orderItem.getProduct().getId());
            Stock stockToUpdate = this.getStockByProductId(orderItem.getProduct().getId())
                    .orElseThrow(() -> new IllegalArgumentException("Invalid product Id:" + orderItem.getProduct().getId()));
            stockToUpdate.setQuantity(stockToUpdate.getQuantity() + orderItem.getQuantity());
            stockToUpdate.setLastUpdated(LocalDate.now());
            this.saveStock(stockToUpdate);
        }
    }

    public void updateStocksSubstitute(Request request) {
        Set<BeerIngredient> ingredients = new LinkedHashSet(request.getBeer().getBeerIngredients());
        double multiplier = request.getAmount() / 1000.0;
        for (BeerIngredient beerIngredient: ingredients ) {
            Stock stockToUpdate = this.getStockByProductId(beerIngredient.getIngredient().getId())
                    .orElseThrow(() -> new IllegalArgumentException("Invalid product Id:" + beerIngredient.getIngredient().getId()));
            double quantity = beerIngredient.getQuantity() * multiplier;
            stockToUpdate.setQuantity(stockToUpdate.getQuantity() - quantity);
            stockToUpdate.setLastUpdated(LocalDate.now());
            this.saveStock(stockToUpdate);
        }
        Product bottle = productService.getProductByName("Bottle");
        Stock stockToUpdate = this.getStockByProductId(bottle.getId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid product Id:" + bottle.getId()));
        stockToUpdate.setQuantity(stockToUpdate.getQuantity() - request.getAmount() * 2);
        stockToUpdate.setLastUpdated(LocalDate.now());
        this.saveStock(stockToUpdate);
    }

    public void substituteFromStock(long id, double quantity) {
        Stock stock = this.getStockByProductId(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid beer Id:" + id));
        stock.substitute(quantity);
        stock.setLastUpdated(LocalDate.now());
        this.saveStock(stock);
        requestService.updateRequestsAvailability();
    }

    public void addToStock(long id, double quantity) {
        Stock stock = this.getStockByProductId(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid beer Id:" + id));
        stock.add(quantity);
        stock.setLastUpdated(LocalDate.now());
        this.saveStock(stock);
        requestService.updateRequestsAvailability();
    }
}

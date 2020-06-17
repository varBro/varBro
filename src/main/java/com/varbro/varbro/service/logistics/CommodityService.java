package com.varbro.varbro.service.logistics;

import com.varbro.varbro.model.User;
import com.varbro.varbro.model.logistics.Commodity;
import com.varbro.varbro.repository.logistics.CommodityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommodityService {

    @Autowired
    CommodityRepository commodityRepository;

    public void saveCommodity(Commodity commodity) {
        commodityRepository.save(commodity);
    }

    public void saveCommodities(List<Commodity> commodities) { commodityRepository.saveAll(commodities); }

    public Iterable<Commodity> getCommodities() {
        return commodityRepository.findAll();
    }

    public void deleteAll() { commodityRepository.deleteAll(); }
}

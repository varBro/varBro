package com.varbro.varbro.service.logistics;

import com.varbro.varbro.model.Logistics.Commodity;
import com.varbro.varbro.repository.logistics.CommodityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CommodityService {

    @Autowired
    CommodityRepository commodityRepository;

    public void saveCommodity(Commodity commodity) {
        commodityRepository.save(commodity);
    }

    public Iterable<Commodity> getCommodities() {
        return commodityRepository.findAll();
    }
}

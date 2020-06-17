package com.varbro.varbro.controller.logistics;

import com.varbro.varbro.model.logistics.Commodity;
import com.varbro.varbro.service.logistics.CommodityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

public class LogisticsController {

    @Autowired
    CommodityService commodityService;

    @GetMapping("/logistics")
    public String hrIndex() {
        return "logistics/index";
    }

//    @GetMapping("/logistics/stock")


}


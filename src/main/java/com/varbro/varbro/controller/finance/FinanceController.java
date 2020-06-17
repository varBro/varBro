package com.varbro.varbro.controller.finance;

import com.varbro.varbro.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FinanceController {

    @Autowired
    RoleService roleService;

    @GetMapping("/finance")
    public String financeIndex() {
        return "finance/index";
    }

}

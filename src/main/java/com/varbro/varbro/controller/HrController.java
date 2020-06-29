package com.varbro.varbro.controller;

import com.varbro.varbro.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HrController {

    @Autowired
    RoleService roleService;

    @GetMapping("/hr")
    public String hrIndex() {
        return "hr/index";
    }
}

package com.fpt.duantn.ui.controller;

import com.fpt.duantn.services.BillService;
import com.fpt.duantn.services.CustomerService;
import com.fpt.duantn.services.EmployeeService;
import com.fpt.duantn.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins = {"http://localhost:4201","http://localhost:4200"})
@RequestMapping("/DashBoard")
public class DashBoardController {
    @Autowired
    ProductService productService;
    @Autowired
    CustomerService customerService;
    @Autowired
    EmployeeService employeeService;
    @Autowired
    BillService billService;
    @GetMapping()
    public Map<String, Long> get(){
        Map<String,Long>map = new HashMap<>();
        map.put("totalProduct",productService.count());
        map.put("totalCustomer",customerService.count());
        map.put("totalEmployee0", employeeService.count());
        map.put("totalBill", billService.count());
        return map;
    }

}

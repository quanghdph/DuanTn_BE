package com.fpt.duantn.ui.controller;

import com.fpt.duantn.services.CustomerCardService;
import com.fpt.duantn.shrared.dto.CRUD.CustomerDto;
import com.fpt.duantn.ui.model.response.ProductCardRest;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
@CrossOrigin(origins = {"http://localhost:4201","http://localhost:4200"})
@RestController
@RequestMapping("/customer-list")
public class CustomerCardController {

    @Autowired
    CustomerCardService customerCardService;

    @GetMapping()
    public List<ProductCardRest> getCustomer(@RequestParam(value = "page", defaultValue = "0") int page,
                                             @RequestParam(value = "limit", defaultValue = "2") int limit) {
        List<ProductCardRest> returnValue = new ArrayList<>();

        List<CustomerDto> customers = customerCardService.getCustomerCard(page, limit);

        for (CustomerDto customerDto : customers) {
            ProductCardRest customerModel = new ProductCardRest();
            BeanUtils.copyProperties(customerDto, customerModel);
            returnValue.add(customerModel);
        }

        return returnValue;

    }
}

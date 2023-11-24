package com.fpt.duantn.services.impl;

import com.fpt.duantn.io.entity.CustomerEntity;
import com.fpt.duantn.io.repository.CustomerRepository;
import com.fpt.duantn.services.CustomerCardService;
import com.fpt.duantn.shrared.dto.CRUD.CustomerDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerCardServiceimpl implements CustomerCardService {

    @Autowired
    CustomerRepository customerRepository;
    @Override
    public List<CustomerDto> getCustomerCard(int page, int limit) {
        List<CustomerDto> returnValue = new ArrayList<>();

        if(page>0) page = page-1;

        Pageable pageableRequest = PageRequest.of(page, limit);
        Page<CustomerEntity> customerCardPage = customerRepository.getCustomersAndBills(pageableRequest);
        List<CustomerEntity> customerCart = customerCardPage.getContent();

        for (CustomerEntity customerCartEntity : customerCart) {
            CustomerDto customerDto = new CustomerDto();
            BeanUtils.copyProperties(customerCartEntity, customerDto);
            returnValue.add(customerDto);
        }

        return returnValue;
    }

}

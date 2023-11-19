package com.fpt.duantn.services;

import com.fpt.duantn.shrared.dto.CRUD.CustomerDto;

import java.util.List;

public interface CustomerService {

    CustomerDto createCustomer(CustomerDto customer);
    CustomerDto getCustomerById(Long customerId);
    CustomerDto updateCustomer(Long customerId, CustomerDto customer);
    void deleteCustomer(Long customerId);
    List<CustomerDto> getCustomers(int page, int limit, String filter);
    Long count(String filter);
    List<CustomerDto> getCustomerByCustomerName(String customerName, int page, int limit);


}

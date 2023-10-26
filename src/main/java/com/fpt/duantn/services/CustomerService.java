package com.fpt.duantn.services;

import com.fpt.duantn.shrared.dto.CRUD.CustomerDto;

import java.util.List;

public interface CustomerService {

    CustomerDto createCustomer(CustomerDto customer);
    CustomerDto getCustomerByCustomerCode(String customerCode);
    CustomerDto updateCustomer(String customerCode, CustomerDto customer);
    void deleteCustomer(String customerCode);
    List<CustomerDto> getCustomers(int page, int limit);
    List<CustomerDto> getCustomerByCustomerName(String customerName, int page, int limit);


}

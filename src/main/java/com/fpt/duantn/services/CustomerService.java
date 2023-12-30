package com.fpt.duantn.services;

import com.fpt.duantn.io.entity.CustomerEntity;
import com.fpt.duantn.shrared.dto.CRUD.CustomerDto;
import com.fpt.duantn.ui.model.response.CustomerResponse;

import java.util.List;
import java.util.Optional;

public interface CustomerService {

    CustomerDto createCustomer(CustomerDto customer);
    CustomerDto getCustomerById(Long customerId);
    CustomerDto updateCustomer(Long customerId, CustomerDto customer);
    void deleteCustomer(Long customerId);
    List<CustomerDto> getCustomers(int page, int limit, String filter);

    Optional<CustomerEntity> findById(Long aLong);

    Long count();
    Long count(String filter);

    CustomerResponse findByPhoneNumber(String phoneNumber);

    Optional<CustomerEntity> findCByPhoneNumber(String phoneNumber);

    Optional<CustomerEntity> findByEmail(String email);

    List<CustomerDto> getCustomerByCustomerName(String customerName, int page, int limit);
    <S extends CustomerEntity> S save(S entity);

}

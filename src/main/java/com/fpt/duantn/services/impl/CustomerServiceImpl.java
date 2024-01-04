package com.fpt.duantn.services.impl;

import com.fpt.duantn.exceptions.CustomerServiceException;
import com.fpt.duantn.io.entity.CustomerEntity;
import com.fpt.duantn.io.repository.CustomerRepository;
import com.fpt.duantn.services.CustomerService;
import com.fpt.duantn.shrared.Utils;
import com.fpt.duantn.shrared.dto.CRUD.CustomerDto;
import com.fpt.duantn.ui.model.response.CustomerResponse;
import com.fpt.duantn.ui.model.response.ErrorMessages;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    Utils utils;

    @Override
    public CustomerDto createCustomer(CustomerDto customer) {
        // Kiểm tra xem CustomerCode đã tồn tại hay chưa
        if (customerRepository.findByCustomerCode(customer.getCustomerCode()) != null) {
            throw new CustomerServiceException("Customer with the same code already exists");
        }

        // Chuyển đổi CustomerDto thành CustomerEntity
        ModelMapper modelMapper = new ModelMapper();
        CustomerEntity customerEntity = modelMapper.map(customer, CustomerEntity.class);

        // Tạo một mã ngẫu nhiên cho CustomerCode (tùy theo yêu cầu)
        String publicCustomerCode = utils.generateColorCode(8);
        customerEntity.setCustomerCode(publicCustomerCode);

        // Lưu trữ thông tin màu vào cơ sở dữ liệu
        CustomerEntity storedCustomerDetails = customerRepository.save(customerEntity);

        // Chuyển đổi CustomerEntity thành CustomerDto
        CustomerDto returnValue = modelMapper.map(storedCustomerDetails, CustomerDto.class);

        return returnValue;
    }


    @Override
    public Boolean existsByEmail(String email) {
        return customerRepository.existsByEmail(email);
    }

    @Override
    public CustomerDto getCustomerById(Long customerId) {
        CustomerDto returnValue = new CustomerDto();
        CustomerEntity customerEntity = customerRepository.findCustomerEntityById(customerId);

        if (customerEntity == null)
            throw new CustomerServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

        BeanUtils.copyProperties(customerEntity, returnValue);

        return returnValue;
    }

    @Override
    public CustomerDto updateCustomer(Long customerId, CustomerDto customer) {
        CustomerDto returnValue = new CustomerDto();

        CustomerEntity customerEntity = customerRepository.findCustomerEntityById(customerId);

        if (customerEntity == null)
            throw new CustomerServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

        customerEntity.setFirstName(customer.getFirstName());
        customerEntity.setLastName(customer.getLastName());
        customerEntity.setEmail(customer.getEmail());
        customerEntity.setEmailVerificationStatus(customer.isEmailVerificationStatus());
        customerEntity.setEmailVerificationToken(customer.getEmailVerificationToken());
        customerEntity.setEncryptedPassword(customer.getEncryptedPassword());
        customerEntity.setGender(customer.getGender());
        customerEntity.setPhoneNumber(customer.getPhoneNumber());
        customerEntity.setDateOfBirth(customer.getDateOfBirth());
        customerEntity.setCreateDate(customer.getCreateDate());
        customerEntity.setUpdateDate(customer.getUpdateDate());

        CustomerEntity updatedCustomers = customerRepository.save(customerEntity);
        returnValue = new ModelMapper().map(updatedCustomers, CustomerDto.class);

        return returnValue;
    }

    @Override
    public void deleteCustomer(Long customerId) {
        CustomerEntity customerEntity = customerRepository.findCustomerEntityById(customerId);

        if (customerEntity == null)
            throw new CustomerServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

        customerRepository.delete(customerEntity);
    }

    @Override
    public List<CustomerDto> getCustomers(int page, int limit, String filter) {
        List<CustomerDto> returnValue = new ArrayList<>();

        if(page>0) page = page-1;

        Pageable pageableRequest = PageRequest.of(page, limit);

        Page<CustomerEntity> customerPage = customerRepository.filter(filter, pageableRequest);
        List<CustomerEntity> customers = customerPage.getContent();

        for (CustomerEntity customerEntity : customers) {
            CustomerDto customerDto = new CustomerDto();
            BeanUtils.copyProperties(customerEntity, customerDto);
            returnValue.add(customerDto);
        }

        return returnValue;
    }

    @Override
    public Optional<CustomerEntity> findById(Long aLong) {
        return customerRepository.findById(aLong);
    }

    @Override
    public Long count() {
        return this.customerRepository.count();
    }

    @Override
    public Long count(String filter) {
        return  this.customerRepository.count(filter);
    }

    @Override
    public CustomerResponse findByPhoneNumber(String phoneNumber) {
        return customerRepository.findByPhoneNumber(phoneNumber);
    }

    @Override
    public Optional<CustomerEntity> findCByPhoneNumber(String phoneNumber) {
        return customerRepository.findCByPhoneNumber(phoneNumber);
    }

    @Override
    public Optional<CustomerEntity> findByEmail(String email) {
        return customerRepository.findByEmail(email);
    }

    @Override
    public <S extends CustomerEntity> S save(S entity) {
        return customerRepository.save(entity);
    }

    @Override
    public List<CustomerDto> getCustomerByCustomerName(String customerName, int page, int limit) {
        List<CustomerDto> returnValue = new ArrayList<>();

        if(page>0) page = page-1;

        Pageable pageableRequest = PageRequest.of(page, limit);
        Page<CustomerEntity> customerPage = customerRepository.findByLastNameContainingOrderByIdAsc(customerName, pageableRequest);
        List<CustomerEntity> customers = customerPage.getContent();

        for (CustomerEntity customerEntity : customers) {
            CustomerDto customerDto = new CustomerDto();
            BeanUtils.copyProperties(customerEntity, customerDto);
            returnValue.add(customerDto);
        }

        return returnValue;
    }
}

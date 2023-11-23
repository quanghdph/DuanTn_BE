package com.fpt.duantn.services;

import com.fpt.duantn.shrared.dto.CRUD.CustomerDto;

import java.util.List;

public interface CustomerCardService {

    List<CustomerDto> getCustomerCard(int page, int limit);

}

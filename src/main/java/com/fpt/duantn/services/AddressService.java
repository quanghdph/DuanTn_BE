package com.fpt.duantn.services;

import com.fpt.duantn.shrared.dto.CRUD.AddressDto;

import java.util.List;

public interface AddressService {

    AddressDto createAddress(AddressDto address);
    AddressDto getAddressById(Long addressId);
    AddressDto updateAddress(Long addressId, AddressDto address);
    void deleteAddress(Long addressId);
    List<AddressDto> getAddresss(int page, int limit, String filter);
    Long count(String filter);

}

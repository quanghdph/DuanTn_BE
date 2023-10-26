package com.fpt.duantn.services;

import com.fpt.duantn.shrared.dto.CRUD.AddressDto;

import java.util.List;

public interface AddressService {

    AddressDto createAddress(AddressDto address);
    AddressDto getAddressByAddressCode(String addressCode);
    AddressDto updateAddress(String addressCode, AddressDto address);
    void deleteAddress(String addressCode);
    List<AddressDto> getAddresss(int page, int limit);
    List<AddressDto> getAddressByAddressName(String addressName, int page, int limit);

}

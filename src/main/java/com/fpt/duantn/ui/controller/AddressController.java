package com.fpt.duantn.ui.controller;

import com.fpt.duantn.services.AddressService;
import com.fpt.duantn.shrared.dto.CRUD.AddressDto;
import com.fpt.duantn.ui.model.request.AddressDetailsRequestModel;
import com.fpt.duantn.ui.model.response.AddressRest;
import com.fpt.duantn.ui.model.response.OperationStatusModel;
import com.fpt.duantn.ui.model.response.RequestOperationStatus;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/address")
public class AddressController {

    @Autowired
    AddressService addressService;

    @GetMapping(path = "/{id}")
    public AddressRest getAddress(@PathVariable String id) {
        AddressRest returnValue = new AddressRest();

        AddressDto addressDto = addressService.getAddressByAddressCode(id);
        ModelMapper modelMapper = new ModelMapper();
        returnValue = modelMapper.map(addressDto, AddressRest.class);

        return returnValue;
    }

    @PostMapping()
    public AddressRest createAddress(@RequestBody AddressDetailsRequestModel addressDetails) throws Exception {
        AddressRest returnValue = new AddressRest();

        ModelMapper modelMapper = new ModelMapper();
        AddressDto addressDto = modelMapper.map(addressDetails, AddressDto.class);

        addressDto.setCustomer(addressDetails.getCustomer());

        AddressDto createdUser = addressService.createAddress(addressDto);
        returnValue = modelMapper.map(createdUser, AddressRest.class);

        return returnValue;
    }

    @GetMapping()
    public List<AddressRest> getAddresss(@RequestParam(value = "page", defaultValue = "0") int page,
                                       @RequestParam(value = "limit", defaultValue = "2") int limit) {
        List<AddressRest> returnValue = new ArrayList<>();

        List<AddressDto> addresss = addressService.getAddresss(page, limit);

        for (AddressDto addressDto : addresss) {
            AddressRest addressModel = new AddressRest();
            BeanUtils.copyProperties(addressDto, addressModel);
            returnValue.add(addressModel);
        }

        return returnValue;
    }


    @PutMapping(path = "/{id}")
    public AddressRest updateAddress(@PathVariable String id, @RequestBody AddressDetailsRequestModel addressDetails) {
        AddressRest returnValue = new AddressRest();

        AddressDto addressDto = new AddressDto();
        addressDto = new ModelMapper().map(addressDetails, AddressDto.class);

        addressDto.setCustomer(addressDetails.getCustomer());

        AddressDto updateAddress = addressService.updateAddress(id, addressDto);
        returnValue = new ModelMapper().map(updateAddress, AddressRest.class);

        return returnValue;
    }

    @DeleteMapping(path = "/{id}")
    public OperationStatusModel deleteAddress(@PathVariable String id) {
        OperationStatusModel returnValue = new OperationStatusModel();
        returnValue.setOperationName(RequestOperationName.DELETE.name());

        addressService.deleteAddress(id);

        returnValue.setOperationResult(RequestOperationStatus.SUCCESS.name());
        return returnValue;
    }



    @GetMapping("/search")
    public List<AddressRest> searchAddresss(@RequestParam(value = "addressName") String addressName,
                                          @RequestParam(value = "page", defaultValue = "0") int page,
                                          @RequestParam(value = "limit", defaultValue = "2") int limit) {
        List<AddressRest> returnValue = new ArrayList<>();

        List<AddressDto> addresss = addressService.getAddressByAddressName(addressName, page, limit);

        for (AddressDto addressDto : addresss) {
            AddressRest addressModel = new AddressRest();
            BeanUtils.copyProperties(addressDto, addressModel);
            returnValue.add(addressModel);
        }

        return returnValue;
    }

}

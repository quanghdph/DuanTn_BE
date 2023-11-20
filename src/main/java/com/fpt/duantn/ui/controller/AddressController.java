package com.fpt.duantn.ui.controller;

import com.fpt.duantn.services.AddressService;
import com.fpt.duantn.shrared.dto.CRUD.AddressDto;
import com.fpt.duantn.ui.model.request.AddressRequest;
import com.fpt.duantn.ui.model.response.AddressRest;
import com.fpt.duantn.ui.model.response.OperationStatusModel;
import com.fpt.duantn.ui.model.response.PaginationRest;
import com.fpt.duantn.ui.model.response.RequestOperationStatus;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/address")
public class AddressController {

    @Autowired
    AddressService addressService;

    @GetMapping(path = "/{id}")
    public AddressRest getAddress(@PathVariable Long id) {
        AddressRest returnValue = new AddressRest();

        AddressDto addressDto = addressService.getAddressById(id);
        ModelMapper modelMapper = new ModelMapper();
        returnValue = modelMapper.map(addressDto, AddressRest.class);

        return returnValue;
    }

    @PostMapping()
    public AddressRest createAddress(@RequestBody AddressRequest addressDetails) throws Exception {
        AddressRest returnValue = new AddressRest();

        ModelMapper modelMapper = new ModelMapper();
        AddressDto addressDto = modelMapper.map(addressDetails, AddressDto.class);

        addressDto.setCustomer(addressDetails.getCustomer());

        AddressDto createdUser = addressService.createAddress(addressDto);
        returnValue = modelMapper.map(createdUser, AddressRest.class);

        return returnValue;
    }




    @PutMapping(path = "/{id}")
    public AddressRest updateAddress(@PathVariable Long id, @RequestBody AddressRequest addressDetails) {
        AddressRest returnValue = new AddressRest();

        AddressDto addressDto = new AddressDto();
        addressDto = new ModelMapper().map(addressDetails, AddressDto.class);

        addressDto.setCustomer(addressDetails.getCustomer());
        try {

        AddressDto updateAddress = addressService.updateAddress(id, addressDto);
        returnValue = new ModelMapper().map(updateAddress, AddressRest.class);
        }catch (Exception e){
            System.out.println(e);
        }
        return returnValue;
    }

    @DeleteMapping(path = "/{id}")
    public OperationStatusModel deleteAddress(@PathVariable Long id) {
        OperationStatusModel returnValue = new OperationStatusModel();
        returnValue.setOperationName(RequestOperationName.DELETE.name());

        try {
            addressService.deleteAddress(id);
            returnValue.setOperationResult(RequestOperationStatus.SUCCESS.name());
            returnValue.setOperationMessage("Xoa Thanh Cong.");
        }catch (DataIntegrityViolationException exception){
            returnValue.setOperationResult(RequestOperationStatus.ERROR.name());
            returnValue.setOperationMessage("Lỗi khi xóa Dia Chi: Dia Chi có tham chiếu đến khoá ngoại.");
        }catch (Exception e){
            returnValue.setOperationResult(RequestOperationStatus.ERROR.name());
            returnValue.setOperationMessage("Lỗi khi xóa Dia Chi: " + e.getMessage());
        }
        return returnValue;
    }

    @GetMapping()
    public PaginationRest getAddresss(@RequestParam(value = "page", defaultValue = "0") int page,
                                      @RequestParam(value = "limit", defaultValue = "5") int limit,
                                      @RequestParam(value = "filter", defaultValue = "") String filter) {
        List<AddressRest> returnValue = new ArrayList<>();

        List<AddressDto> addresss = addressService.getAddresss(page, limit, filter);

        for (AddressDto addressDto : addresss) {
            AddressRest addressModel = new AddressRest();
            BeanUtils.copyProperties(addressDto, addressModel);
            returnValue.add(addressModel);
        }
        PaginationRest paginationRest = new PaginationRest();
        paginationRest.setListAddresses(returnValue);
        paginationRest.setTotal(addressService.count(filter));

        return paginationRest;
    }
}

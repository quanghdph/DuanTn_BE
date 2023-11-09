package com.fpt.duantn.ui.controller;

import com.fpt.duantn.services.BrandService;
import com.fpt.duantn.shrared.dto.CRUD.BrandDto;
import com.fpt.duantn.ui.model.request.BrandDetailsRequestModel;
import com.fpt.duantn.ui.model.response.BrandRest;
import com.fpt.duantn.ui.model.response.OperationStatusModel;
import com.fpt.duantn.ui.model.response.RequestOperationStatus;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/brand")
public class BrandController {

    @Autowired
    BrandService brandService;

    @GetMapping(path = "/{id}")
    public BrandRest getBrand(@PathVariable String id) {
        BrandRest returnValue = new BrandRest();

        BrandDto brandDto = brandService.getBrandByBrandCode(id);
        ModelMapper modelMapper = new ModelMapper();
        returnValue = modelMapper.map(brandDto, BrandRest.class);

        return returnValue;
    }

    @PostMapping()
    public BrandRest createBrand(@RequestBody BrandDetailsRequestModel brandDetails) throws Exception {
        BrandRest returnValue = new BrandRest();

        ModelMapper modelMapper = new ModelMapper();
        BrandDto brandDto = modelMapper.map(brandDetails, BrandDto.class);

        BrandDto createdUser = brandService.createBrand(brandDto);
        returnValue = modelMapper.map(createdUser, BrandRest.class);

        return returnValue;
    }

    @GetMapping()
    public List<BrandRest> getBrands(@RequestParam(value = "page", defaultValue = "0") int page,
                                           @RequestParam(value = "limit", defaultValue = "2") int limit) {
        List<BrandRest> returnValue = new ArrayList<>();

        List<BrandDto> brands = brandService.getBrands(page, limit);

        for (BrandDto brandDto : brands) {
            BrandRest brandModel = new BrandRest();
            BeanUtils.copyProperties(brandDto, brandModel);
            returnValue.add(brandModel);
        }

        return returnValue;
    }


    @PutMapping(path = "/{id}")
    public BrandRest updateBrand(@PathVariable String id, @RequestBody BrandDetailsRequestModel brandDetails) {
        BrandRest returnValue = new BrandRest();

        BrandDto brandDto = new BrandDto();
        brandDto = new ModelMapper().map(brandDetails, BrandDto.class);

        BrandDto updateBrand = brandService.updateBrand(id, brandDto);
        returnValue = new ModelMapper().map(updateBrand, BrandRest.class);

        return returnValue;
    }

    @DeleteMapping(path = "/{id}")
    public OperationStatusModel deleteBrand(@PathVariable String id) {
        OperationStatusModel returnValue = new OperationStatusModel();
        returnValue.setOperationName(RequestOperationName.DELETE.name());

        brandService.deleteBrand(id);

        returnValue.setOperationResult(RequestOperationStatus.SUCCESS.name());
        return returnValue;
    }



    @GetMapping("/search")
    public List<BrandRest> searchBrands(@RequestParam(value = "brandName") String brandName,
                                              @RequestParam(value = "page", defaultValue = "0") int page,
                                              @RequestParam(value = "limit", defaultValue = "2") int limit) {
        List<BrandRest> returnValue = new ArrayList<>();

        List<BrandDto> brands = brandService.getBrandByBrandName(brandName, page, limit);

        for (BrandDto brandDto : brands) {
            BrandRest brandModel = new BrandRest();
            BeanUtils.copyProperties(brandDto, brandModel);
            returnValue.add(brandModel);
        }

        return returnValue;
    }

}

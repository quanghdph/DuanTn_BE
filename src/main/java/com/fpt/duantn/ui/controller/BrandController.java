package com.fpt.duantn.ui.controller;

import com.fpt.duantn.services.BrandService;
import com.fpt.duantn.shrared.dto.CRUD.BrandDto;
import com.fpt.duantn.ui.model.request.BrandRequest;
import com.fpt.duantn.ui.model.response.BrandRest;
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

@CrossOrigin(origins = {"http://localhost:4201","http://localhost:4200"})
@RestController
@RequestMapping("/brand")
public class BrandController {

    @Autowired
    BrandService brandService;

    @GetMapping(path = "/{id}")
    public BrandRest getBrand(@PathVariable Long id) {
        BrandRest returnValue = new BrandRest();

        BrandDto brandDto = brandService.getBrandById(id);
        ModelMapper modelMapper = new ModelMapper();
        returnValue = modelMapper.map(brandDto, BrandRest.class);

        return returnValue;
    }

    @PostMapping()
    public BrandRest createBrand(@RequestBody BrandRequest brandDetails) throws Exception {
        BrandRest returnValue = new BrandRest();

        ModelMapper modelMapper = new ModelMapper();
        BrandDto brandDto = modelMapper.map(brandDetails, BrandDto.class);

        BrandDto createdUser = brandService.createBrand(brandDto);
        returnValue = modelMapper.map(createdUser, BrandRest.class);

        return returnValue;
    }



    @PutMapping(path = "/{id}")
    public BrandRest updateBrand(@PathVariable Long id, @RequestBody BrandRequest brandDetails) {
        BrandRest returnValue = new BrandRest();

        BrandDto brandDto = new BrandDto();
        brandDto = new ModelMapper().map(brandDetails, BrandDto.class);

        BrandDto updateBrand = brandService.updateBrand(id, brandDto);
        returnValue = new ModelMapper().map(updateBrand, BrandRest.class);

        return returnValue;
    }

    @DeleteMapping(path = "/{id}")
    public OperationStatusModel deleteBrand(@PathVariable Long id) {
        OperationStatusModel returnValue = new OperationStatusModel();
        returnValue.setOperationName(RequestOperationName.DELETE.name());

        try {
            brandService.deleteBrand(id);
            returnValue.setOperationResult(RequestOperationStatus.SUCCESS.name());
            returnValue.setOperationMessage("Xoa Thanh Cong.");
        }catch (DataIntegrityViolationException exception){
            returnValue.setOperationResult(RequestOperationStatus.ERROR.name());
            returnValue.setOperationMessage("Lỗi khi xóa Thuong Hieu: Thuong Hieu có tham chiếu đến khoá ngoại.");
        }catch (Exception e){
            returnValue.setOperationResult(RequestOperationStatus.ERROR.name());
            returnValue.setOperationMessage("Lỗi khi xóa Thuong Hieu: " + e.getMessage());
        }return returnValue;
    }



    @GetMapping("/search")
    public List<BrandRest> searchBrands(@RequestParam(value = "brandName") String brandName,
                                              @RequestParam(value = "page", defaultValue = "0") int page,
                                              @RequestParam(value = "limit", defaultValue = "5") int limit) {
        List<BrandRest> returnValue = new ArrayList<>();

        List<BrandDto> brands = brandService.getBrandByBrandName(brandName, page, limit);

        for (BrandDto brandDto : brands) {
            BrandRest brandModel = new BrandRest();
            BeanUtils.copyProperties(brandDto, brandModel);
            returnValue.add(brandModel);
        }

        return returnValue;
    }


    @GetMapping()
    public PaginationRest getBrands(@RequestParam(value = "page", defaultValue = "0") int page,
                                    @RequestParam(value = "limit", defaultValue = "5") int limit,
                                    @RequestParam(value = "filter", defaultValue = "") String filter) {
        List<BrandRest> returnValue = new ArrayList<>();

        List<BrandDto> brands = brandService.getBrands(page, limit, filter);

        for (BrandDto brandDto : brands) {
            BrandRest brandModel = new BrandRest();
            BeanUtils.copyProperties(brandDto, brandModel);
            returnValue.add(brandModel);
        }
        PaginationRest paginationRest = new PaginationRest();
        paginationRest.setListBrand(returnValue);
        paginationRest.setTotal(brandService.count(filter));

        return paginationRest;
    }

}

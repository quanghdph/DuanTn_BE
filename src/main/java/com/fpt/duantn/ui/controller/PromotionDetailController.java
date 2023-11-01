package com.fpt.duantn.ui.controller;

import com.fpt.duantn.services.PromotionDetailService;
import com.fpt.duantn.shrared.dto.CRUD.PromotionDetailDto;
import com.fpt.duantn.ui.model.request.PromotionDetailDetailsRequestModel;
import com.fpt.duantn.ui.model.response.PromotionDetailRest;
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
@RequestMapping("/promotionDetail")
public class PromotionDetailController {

    @Autowired
    PromotionDetailService promotionDetailService;

    @GetMapping(path = "/{id}")
    public PromotionDetailRest getPromotionDetail(@PathVariable Long id) {
        PromotionDetailRest returnValue = new PromotionDetailRest();

        PromotionDetailDto promotionDetailDto = promotionDetailService.getPromotionDetailByPromotionDetailCode(id);
        ModelMapper modelMapper = new ModelMapper();
        returnValue = modelMapper.map(promotionDetailDto, PromotionDetailRest.class);

        return returnValue;
    }

    @PostMapping()
    public PromotionDetailRest createPromotionDetail(@RequestBody PromotionDetailDetailsRequestModel promotionDetailDetails) throws Exception {
        PromotionDetailRest returnValue = new PromotionDetailRest();

        ModelMapper modelMapper = new ModelMapper();
        PromotionDetailDto promotionDetailDto = modelMapper.map(promotionDetailDetails, PromotionDetailDto.class);

        promotionDetailDto.setPromotion(promotionDetailDetails.getPromotion());
        promotionDetailDto.setProductDetail(promotionDetailDetails.getProductDetail());

        PromotionDetailDto createdUser = promotionDetailService.createPromotionDetail(promotionDetailDto);
        returnValue = modelMapper.map(createdUser, PromotionDetailRest.class);

        return returnValue;
    }

    @GetMapping()
    public List<PromotionDetailRest> getPromotionDetails(@RequestParam(value = "page", defaultValue = "0") int page,
                                                     @RequestParam(value = "limit", defaultValue = "2") int limit) {
        List<PromotionDetailRest> returnValue = new ArrayList<>();

        List<PromotionDetailDto> promotionDetails = promotionDetailService.getPromotionDetails(page, limit);

        for (PromotionDetailDto promotionDetailDto : promotionDetails) {
            PromotionDetailRest promotionDetailModel = new PromotionDetailRest();
            BeanUtils.copyProperties(promotionDetailDto, promotionDetailModel);
            returnValue.add(promotionDetailModel);
        }

        return returnValue;
    }


    @PutMapping(path = "/{id}")
    public PromotionDetailRest updatePromotionDetail(@PathVariable Long id, @RequestBody PromotionDetailDetailsRequestModel promotionDetailDetails) {
        PromotionDetailRest returnValue = new PromotionDetailRest();

        PromotionDetailDto promotionDetailDto = new PromotionDetailDto();
        promotionDetailDto = new ModelMapper().map(promotionDetailDetails, PromotionDetailDto.class);

        promotionDetailDto.setProductDetail(promotionDetailDetails.getProductDetail());
        promotionDetailDto.setPromotion(promotionDetailDetails.getPromotion());

        PromotionDetailDto updatePromotionDetail = promotionDetailService.updatePromotionDetail(id, promotionDetailDto);
        returnValue = new ModelMapper().map(updatePromotionDetail, PromotionDetailRest.class);

        return returnValue;
    }

    @DeleteMapping(path = "/{id}")
    public OperationStatusModel deletePromotionDetail(@PathVariable Long id) {
        OperationStatusModel returnValue = new OperationStatusModel();
        returnValue.setOperationName(RequestOperationName.DELETE.name());

        promotionDetailService.deletePromotionDetail(id);

        returnValue.setOperationResult(RequestOperationStatus.SUCCESS.name());
        return returnValue;
    }



//    @GetMapping("/search")
//    public List<PromotionDetailRest> searchPromotionDetails(@RequestParam(value = "promotionDetailName") String promotionDetailName,
//                                              @RequestParam(value = "page", defaultValue = "0") int page,
//                                              @RequestParam(value = "limit", defaultValue = "2") int limit) {
//        List<PromotionDetailRest> returnValue = new ArrayList<>();
//
//        List<PromotionDetailDto> promotionDetails = promotionDetailService.getPromotionDetailByPromotionDetailName(promotionDetailName, page, limit);
//
//        for (PromotionDetailDto promotionDetailDto : promotionDetails) {
//            PromotionDetailRest promotionDetailModel = new PromotionDetailRest();
//            BeanUtils.copyProperties(promotionDetailDto, promotionDetailModel);
//            returnValue.add(promotionDetailModel);
//        }
//
//        return returnValue;
//    }

}

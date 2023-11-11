package com.fpt.duantn.ui.controller;

import com.fpt.duantn.services.PromotionService;
import com.fpt.duantn.shrared.dto.CRUD.PromotionDto;
import com.fpt.duantn.ui.model.request.PromotionDetailsRequestModel;
import com.fpt.duantn.ui.model.response.PaginationRest;
import com.fpt.duantn.ui.model.response.PromotionRest;
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
@RequestMapping("/promotion")
public class PromotionController {

    @Autowired
    PromotionService promotionService;

    @GetMapping(path = "/{id}")
    public PromotionRest getPromotion(@PathVariable String id) {
        PromotionRest returnValue = new PromotionRest();

        PromotionDto promotionDto = promotionService.getPromotionByPromotionCode(id);
        ModelMapper modelMapper = new ModelMapper();
        returnValue = modelMapper.map(promotionDto, PromotionRest.class);

        return returnValue;
    }

    @PostMapping()
    public PromotionRest createPromotion(@RequestBody PromotionDetailsRequestModel promotionDetails) throws Exception {
        PromotionRest returnValue = new PromotionRest();

        ModelMapper modelMapper = new ModelMapper();
        PromotionDto promotionDto = modelMapper.map(promotionDetails, PromotionDto.class);

        PromotionDto createdUser = promotionService.createPromotion(promotionDto);
        returnValue = modelMapper.map(createdUser, PromotionRest.class);

        return returnValue;
    }

    @GetMapping()
    public PaginationRest getPromotions(@RequestParam(value = "page", defaultValue = "0") int page,
                                        @RequestParam(value = "limit", defaultValue = "5") int limit,
                                        @RequestParam(value = "filter", defaultValue = "") String filter) {
        List<PromotionRest> returnValue = new ArrayList<>();

        List<PromotionDto> promotions = promotionService.getPromotions(page, limit, filter);

        for (PromotionDto promotionDto : promotions) {
            PromotionRest promotionModel = new PromotionRest();
            BeanUtils.copyProperties(promotionDto, promotionModel);
            returnValue.add(promotionModel);
        }
        PaginationRest paginationRest = new PaginationRest();
        paginationRest.setListPromotion(returnValue);
        paginationRest.setTotal(promotionService.count(filter));

        return paginationRest;
    }


    @PutMapping(path = "/{id}")
    public PromotionRest updatePromotion(@PathVariable String id, @RequestBody PromotionDetailsRequestModel promotionDetails) {
        PromotionRest returnValue = new PromotionRest();

        PromotionDto promotionDto = new PromotionDto();
        promotionDto = new ModelMapper().map(promotionDetails, PromotionDto.class);

        PromotionDto updatePromotion = promotionService.updatePromotion(id, promotionDto);
        returnValue = new ModelMapper().map(updatePromotion, PromotionRest.class);

        return returnValue;
    }

    @DeleteMapping(path = "/{id}")
    public OperationStatusModel deletePromotion(@PathVariable String id) {
        OperationStatusModel returnValue = new OperationStatusModel();
        returnValue.setOperationName(RequestOperationName.DELETE.name());

        promotionService.deletePromotion(id);

        returnValue.setOperationResult(RequestOperationStatus.SUCCESS.name());
        return returnValue;
    }



    @GetMapping("/search")
    public List<PromotionRest> searchPromotions(@RequestParam(value = "promotionName") String promotionName,
                                              @RequestParam(value = "page", defaultValue = "0") int page,
                                              @RequestParam(value = "limit", defaultValue = "2") int limit) {
        List<PromotionRest> returnValue = new ArrayList<>();

        List<PromotionDto> promotions = promotionService.getPromotionByPromotionName(promotionName, page, limit);

        for (PromotionDto promotionDto : promotions) {
            PromotionRest promotionModel = new PromotionRest();
            BeanUtils.copyProperties(promotionDto, promotionModel);
            returnValue.add(promotionModel);
        }

        return returnValue;
    }

}

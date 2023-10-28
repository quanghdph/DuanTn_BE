package com.fpt.duantn.services;

import com.fpt.duantn.shrared.dto.CRUD.PromotionDetailDto;

import java.util.List;

public interface PromotionDetailService {

    PromotionDetailDto createPromotionDetail(PromotionDetailDto promotionDetail);
    PromotionDetailDto getPromotionDetailByPromotionDetailCode(Long promotionDetailCode);
    PromotionDetailDto updatePromotionDetail(Long promotionDetailCode, PromotionDetailDto promotionDetail);
    void deletePromotionDetail(Long promotionDetailCode);
    List<PromotionDetailDto> getPromotionDetails(int page, int limit);
//    List<PromotionDetailDto> getPromotionDetailByPromotionDetailName(String promotionDetailName, int page, int limit);


}

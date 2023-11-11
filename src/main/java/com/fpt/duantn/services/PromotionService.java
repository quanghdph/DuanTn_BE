package com.fpt.duantn.services;

import com.fpt.duantn.shrared.dto.CRUD.PromotionDto;

import java.util.List;

public interface PromotionService {

    PromotionDto createPromotion(PromotionDto promotion);
    PromotionDto getPromotionByPromotionCode(String promotionCode);
    PromotionDto updatePromotion(String promotionCode, PromotionDto promotion);
    void deletePromotion(String promotionCode);
    List<PromotionDto> getPromotions(int page, int limit, String filter);
    Long count(String filter);
    List<PromotionDto> getPromotionByPromotionName(String promotionName, int page, int limit);


}

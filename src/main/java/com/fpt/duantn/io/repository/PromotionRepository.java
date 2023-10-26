package com.fpt.duantn.io.repository;

import com.fpt.duantn.io.entity.PromotionEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PromotionRepository extends JpaRepository<PromotionEntity, Long> {

    PromotionEntity findByPromotionCode(String promotionCode);

    Page<PromotionEntity> findByAmountContainingOrderByIdAsc(String promotionAmount, Pageable pageable);

}

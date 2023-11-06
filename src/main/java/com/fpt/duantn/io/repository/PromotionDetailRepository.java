package com.fpt.duantn.io.repository;

import com.fpt.duantn.io.entity.PromotionDetailEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PromotionDetailRepository extends JpaRepository<PromotionDetailEntity, Long> {

    PromotionDetailEntity findPromotionDetailEntityById(Long promotionDetailId);



//    Page<PromotionDetailEntity> findByPromotionDetailNameContainingOrderByIdAsc(String PromotionDetailName, Pageable pageable);

}

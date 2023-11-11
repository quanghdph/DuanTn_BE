package com.fpt.duantn.io.repository;

import com.fpt.duantn.io.entity.ProductEntity;
import com.fpt.duantn.io.entity.PromotionEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PromotionRepository extends JpaRepository<PromotionEntity, Long> {

    PromotionEntity findByPromotionCode(String promotionCode);

    Page<PromotionEntity> findByAmountContainingOrderByIdAsc(String promotionAmount, Pageable pageable);

    @Query(value = "SELECT p.id, p.promotion_code," +
            "p.amount,p.discount_level,p.description,p.create_date,p.update_time, " +
            "p.status " +
            "FROM promotions p " +
            "where 1=1 and (:filter is null or :filter = '' or (p.discount_level like %:filter% or p.status like %:filter% or p.promotion_code like %:filter%))",
            nativeQuery = true)
    Page<PromotionEntity> filter(@Param("filter") String filter, Pageable pageable);

    @Query(value = "SELECT count(1) " +
            "FROM promotions p " +
            "where 1=1 and (:filter is null or :filter = '' or (p.discount_level like %:filter% or p.status like %:filter% or p.promotion_code like %:filter%))",
            nativeQuery = true)
    Long count(@Param("filter") String filter);
}

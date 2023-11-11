package com.fpt.duantn.io.repository;

import com.fpt.duantn.io.entity.ProductEntity;
import com.fpt.duantn.io.entity.PromotionDetailEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PromotionDetailRepository extends JpaRepository<PromotionDetailEntity, Long> {

    PromotionDetailEntity findPromotionDetailEntityById(Long promotionDetailId);



//    Page<PromotionDetailEntity> findByPromotionDetailNameContainingOrderByIdAsc(String PromotionDetailName, Pageable pageable);


    @Query(value = "SELECT p.id, p.promotion_id," +
            "p.product_detail_id,p.amount,p.end_date,p.create_date,p.update_date, " +
            "p.status " +
            "FROM promotion_detail p " +
            "where 1=1 and (:filter is null or :filter = '' or (p.create_date like %:filter% or p.status like %:filter% or p.end_date like %:filter%))",
            nativeQuery = true)
    Page<PromotionDetailEntity> filter(@Param("filter") String filter, Pageable pageable);

    @Query(value = "SELECT count(1) " +
            "FROM promotion_detail p " +
            "where 1=1 and (:filter is null or :filter = '' or (p.create_date like %:filter% or p.status like %:filter% or p.end_date like %:filter%))",
            nativeQuery = true)
    Long count(@Param("filter") String filter);
}

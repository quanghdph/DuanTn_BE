package com.fpt.duantn.io.repository;


import com.fpt.duantn.io.entity.CategoryEntity;
import com.fpt.duantn.io.entity.ProductDetailEntity;
import com.fpt.duantn.io.entity.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductDetailRepository extends JpaRepository<ProductDetailEntity, Long> {

    ProductDetailEntity findProductDetailEntityById(Long ProductDetailId);

//    Page<ProductDetailEntity> findByProductDetailContainingOrderByIdAsc(String ProductDetailName,Pageable pageable);

    @Query(value = "SELECT pd.id, pd.product_id," +
            "pd.color_id,pd.size_id,pd.material_id,pd.pattern_id,pd.collar_id,pd.waistband_id,pd.default_price,pd.amount,pd.create_date,pd.update_date, " +
            "pd.status,pd.product_detail_code,pd.price " +
            "FROM product_detail pd " +
            "where 1=1 and (:filter is null or :filter = '' or (pd.price like %:filter% or pd.status like %:filter% or pd.product_detail_code like %:filter% or pd.amount like %:filter%))",
            nativeQuery = true)
    Page<ProductDetailEntity> filter(@Param("filter") String filter, Pageable pageable);

    @Query(value = "SELECT count(1) " +
            "FROM product_detail pd " +
            "where 1=1 and (:filter is null or :filter = '' or (pd.price like %:filter% or pd.status like %:filter% or pd.product_detail_code like %:filter% or pd.amount like %:filter%))",
            nativeQuery = true)
    Long count(@Param("filter") String filter);
}

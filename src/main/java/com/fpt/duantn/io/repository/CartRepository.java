package com.fpt.duantn.io.repository;

import com.fpt.duantn.io.entity.CartEntity;
import com.fpt.duantn.io.entity.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CartRepository extends JpaRepository<CartEntity, Long> {

    CartEntity findByCartCode(String cartCode);

    Page<CartEntity> findByCartCodeContainingOrderByIdAsc(String cartName, Pageable pageable);

    @Query(value = "SELECT p.id, p.product_name," +
            "p.category_id,p.brand_id,p.main_image,p.description,p.create_date,p.update_date, " +
            "p.status,p.product_code,p.quantity,p.price " +
            "FROM products p " +
            "where 1=1 and (:filter is null or :filter = '' or (p.product_name like %:filter% or p.status like %:filter% or p.product_code like %:filter%))",
            nativeQuery = true)
    Page<ProductEntity> filter(@Param("filter") String filter, Pageable pageable);

    @Query(value = "SELECT count(1) " +
            "FROM products p " +
            "where 1=1 and (:filter is null or :filter = '' or (p.product_name like %:filter% or p.status like %:filter% or p.product_code like %:filter%))",
            nativeQuery = true)
    Long count(@Param("filter") String filter);
}

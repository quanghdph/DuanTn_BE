package com.fpt.duantn.io.repository;


import com.fpt.duantn.io.entity.ProductEntity;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    @Query(value = "SELECT p.id, p.description, p.product_code, p.product_name, p.status, p.main_image, " +
            "pd.id AS product_detail_id, pd.price, p.material_id " +
            "c.id AS color_id, c.color_code " +
            "FROM products p " +
            "JOIN product_detail pd ON p.id = pd.product_id " +
            "JOIN colors c ON pd.color_id = c.id", nativeQuery = true)

    Page<ProductEntity> getProductsAndColors(Pageable pageable);

//    @Query(value = "SELECT p.product_name, p.main_image, pd.price, c.color_code " +
//            "FROM products p " +
//            "JOIN product_detail pd ON p.id = pd.product_id " +
//            "JOIN colors c ON pd.color_id = c.id", nativeQuery = true)
//    Page<ProductEntity> getProductsAndColorsByproductName(String productName, Pageable pageable);
//

    ProductEntity findByProductCode(String productCode);

    ProductEntity findProductEntityById(Long productId);

    Page<ProductEntity> findByProductNameContainingOrderByIdAsc(String productName, Pageable pageable);

    @Query(value = "SELECT p.id, p.product_name," +
            "p.category_id,p.brand_id,p.main_image,p.description,p.create_date,p.update_date, " +
            "p.status,p.product_code,p.material_id " +
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

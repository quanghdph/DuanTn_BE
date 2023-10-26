package com.fpt.duantn.io.repository;


import com.fpt.duantn.io.entity.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    @Query(value = "SELECT p.id, p.describe, p.product_code, p.product_name, p.status, p.main_image, " +
            "pd.id AS product_detail_id, pd.price, " +
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



}

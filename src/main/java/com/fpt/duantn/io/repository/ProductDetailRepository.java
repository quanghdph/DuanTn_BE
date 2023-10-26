package com.fpt.duantn.io.repository;


import com.fpt.duantn.io.entity.CategoryEntity;
import com.fpt.duantn.io.entity.ProductDetailEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductDetailRepository extends JpaRepository<ProductDetailEntity, Long> {

    ProductDetailEntity findByProductProductName(String productDetailCode);

//    Page<ProductDetailEntity> findByProductContainingOrderByIdAsc(String productDetailName, Pageable pageable);

}

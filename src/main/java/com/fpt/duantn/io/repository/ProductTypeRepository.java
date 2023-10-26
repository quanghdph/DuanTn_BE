package com.fpt.duantn.io.repository;

import com.fpt.duantn.io.entity.ProductTypeEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductTypeRepository extends JpaRepository<ProductTypeEntity, Long> {

    ProductTypeEntity findByProductTypeCode(String productTypeCode);

    Page<ProductTypeEntity> findByProductTypeNameContainingOrderByIdAsc(String productTypeName, Pageable pageable);

}

package com.fpt.duantn.io.repository;

import com.fpt.duantn.io.entity.CategoryEntity;
import com.fpt.duantn.io.entity.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {

    CategoryEntity findByCategoryCode(String categoryCode);

    Page<CategoryEntity> findByCategoryNameContainingOrderByIdAsc(String categoryName, Pageable pageable);

    @Query(value = "SELECT ca.id, ca.category_code, " +
            "ca.category_name,ca.create_date,ca.update_date,ca.status,ca.product_type_id " +
            "FROM categories ca " +
            "where 1=1 and (:filter is null or :filter = '' or (ca.category_name like %:filter% or ca.status like %:filter% or ca.category_code like %:filter%))",
            nativeQuery = true)
    Page<CategoryEntity> filter(@Param("filter") String filter, Pageable pageable);

    @Query(value = "SELECT count(1) " +
            "FROM categories ca " +
            "where 1=1 and (:filter is null or :filter = '' or (ca.category_name like %:filter% or ca.status like %:filter% or ca.category_code like %:filter%))",
            nativeQuery = true)
    Long count(@Param("filter") String filter);
}

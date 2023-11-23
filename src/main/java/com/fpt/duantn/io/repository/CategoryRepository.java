package com.fpt.duantn.io.repository;

import com.fpt.duantn.io.entity.CategoryEntity;
import com.fpt.duantn.io.entity.ProductDetailEntity;
import com.fpt.duantn.io.entity.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {

    @Query(value = "SELECT c.id, c.category_name, c.status, c.category_code, " +
            "p.id AS product_id, p.product_name " +
            "FROM categories c " +
            "JOIN products p ON c.id = p.category_id " +
            "JOIN brands b ON p.brand_id = b.id", nativeQuery = true)

    Page<CategoryEntity> getCategoryAndBrands(Pageable pageable);


    CategoryEntity findByCategoryCode(String categoryCode);

    CategoryEntity findCategoryEntityById(Long categoryId);
    @Query(value = "SELECT c.id, c.category_name, c.status, c.category_code, " +
            "pt.id AS product_type_id, p.price " +
            "FROM categories c " +
            "JOIN product_type pt ON c.id = pt.brand_id " +
            "JOIN categories c ON p.category_id = c.id", nativeQuery = true)

    Page<CategoryEntity> getCategoryAndProductType(Pageable pageable);

    Page<CategoryEntity> findByCategoryNameContainingOrderByIdAsc(String categoryName, Pageable pageable);

    @Query(value = "SELECT ca.id, ca.category_code, " +
            "ca.category_name,ca.create_date,ca.update_date,ca.status " +
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

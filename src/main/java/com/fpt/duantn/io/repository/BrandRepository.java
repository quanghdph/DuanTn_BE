package com.fpt.duantn.io.repository;

import com.fpt.duantn.io.entity.BrandEntity;
import com.fpt.duantn.io.entity.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface BrandRepository extends JpaRepository<BrandEntity, Long> {

    BrandEntity findByBrandCode(String brandCode);

    BrandEntity findBrandEntityById(Long brandId);


    Page<BrandEntity> findByBrandNameContainingOrderByIdAsc(String brandName, Pageable pageable);

    @Query(value = "SELECT b.id, b.brand_code," +
            "b.create_date,b.update_date, " +
            "b.status,b.brand_name " +
            "FROM brands b " +
            "where 1=1 and (:filter is null or :filter = '' or (b.brand_name like %:filter% or b.status like %:filter% or b.brand_code like %:filter%))",
            nativeQuery = true)
    Page<BrandEntity> filter(@Param("filter") String filter, Pageable pageable);

    @Query(value = "SELECT count(1) " +
            "FROM brands b " +
            "where 1=1 and (:filter is null or :filter = '' or (b.brand_name like %:filter% or b.status like %:filter% or b.brand_code like %:filter%))",
            nativeQuery = true)
    Long count(@Param("filter") String filter);
}

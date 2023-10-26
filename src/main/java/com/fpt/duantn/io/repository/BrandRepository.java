package com.fpt.duantn.io.repository;

import com.fpt.duantn.io.entity.BrandEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandRepository extends JpaRepository<BrandEntity, Long> {

    BrandEntity findByBrandCode(String brandCode);

    Page<BrandEntity> findByBrandNameContainingOrderByIdAsc(String brandName, Pageable pageable);

}

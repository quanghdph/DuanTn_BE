package com.fpt.duantn.io.repository;

import com.fpt.duantn.io.entity.CategoryEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {

    CategoryEntity findByCategoryCode(String categoryCode);

    Page<CategoryEntity> findByCategoryNameContainingOrderByIdAsc(String categoryName, Pageable pageable);

}

package com.fpt.duantn.io.repository;

import com.fpt.duantn.io.entity.ColorEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ColorRepository extends JpaRepository<ColorEntity, Long> {

    ColorEntity findByColorCode(String colorCode);
//    ColorEntity findByColorNameContainingOrderByIdAsc(String colorName);

    Page<ColorEntity> findByColorNameContainingOrderByIdAsc(String colorName, Pageable pageable);


}

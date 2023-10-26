package com.fpt.duantn.io.repository;

import com.fpt.duantn.io.entity.MaterialEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MaterialRepository extends JpaRepository<MaterialEntity, Long> {

    MaterialEntity findByMaterialCode(String materialCode);

    Page<MaterialEntity> findByMaterialNameContainingOrderByIdAsc(String materialName, Pageable pageable);

}

package com.fpt.duantn.io.repository;

import com.fpt.duantn.io.entity.MaterialEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MaterialRepository extends JpaRepository<MaterialEntity, Long> {

    MaterialEntity findByMaterialCode(String materialCode);

    MaterialEntity findMaterialEntityById(Long materialId);


    Page<MaterialEntity> findByMaterialNameContainingOrderByIdAsc(String materialName, Pageable pageable);

}

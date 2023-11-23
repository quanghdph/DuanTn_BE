package com.fpt.duantn.io.repository;

import com.fpt.duantn.io.entity.ColorEntity;
import com.fpt.duantn.io.entity.MaterialEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface MaterialRepository extends JpaRepository<MaterialEntity, Long> {

    @Query(value = "SELECT m.id, m.material_name, m.material_code " +
            "FROM materials m ", nativeQuery = true)

    Page<MaterialEntity> getMaterials(Pageable pageable);


    MaterialEntity findByMaterialCode(String materialCode);

    MaterialEntity findMaterialEntityById(Long materialId);


    @Query(value = "SELECT m.id, m.material_name," +
            "m.material_code " +
            "FROM materials m " +
            "where 1=1 and (:filter is null or :filter = '' or (m.material_name like %:filter% or m.material_code like %:filter%))",
            nativeQuery = true)
    Page<MaterialEntity> filter(@Param("filter") String filter, Pageable pageable);

    @Query(value = "SELECT count(1) " +
            "FROM materials m " +
            "where 1=1 and (:filter is null or :filter = '' or (m.material_name like %:filter% or m.material_code like %:filter%))",
            nativeQuery = true)
    Long count(@Param("filter") String filter);
}

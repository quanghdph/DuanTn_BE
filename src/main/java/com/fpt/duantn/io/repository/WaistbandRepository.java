package com.fpt.duantn.io.repository;

import com.fpt.duantn.io.entity.ColorEntity;
import com.fpt.duantn.io.entity.MaterialEntity;
import com.fpt.duantn.io.entity.ProductEntity;
import com.fpt.duantn.io.entity.WaistbandEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface WaistbandRepository extends JpaRepository<WaistbandEntity, Long> {

    @Query(value = "SELECT w.id, w.waistband_name, w.waistband_code " +
            "FROM waistbands w ", nativeQuery = true)

    Page<WaistbandEntity> getWaistbands(Pageable pageable);
    Page<WaistbandEntity> findByWaistBandContainingOrderByIdAsc(String waistBandName, Pageable pageable);


    WaistbandEntity findByWaistbandCode(String waistbandCode);

    WaistbandEntity findWaistbandEntityById(Long waistbandId);

    @Query(value = "SELECT w.id, w.waistband_name," +
            "w.waistband_code " +
            "FROM waistbands w " +
            "where 1=1 and (:filter is null or :filter = '' or (w.waistband_name like %:filter% or w.waistband_code like %:filter%))",
            nativeQuery = true)
    Page<WaistbandEntity> filter(@Param("filter") String filter, Pageable pageable);

    @Query(value = "SELECT count(1) " +
            "FROM waistbands w " +
            "where 1=1 and (:filter is null or :filter = '' or (w.waistband_name like %:filter% or w.waistband_code like %:filter%))",
            nativeQuery = true)
    Long count(@Param("filter") String filter);

}

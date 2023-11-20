package com.fpt.duantn.io.repository;

import com.fpt.duantn.io.entity.MaterialEntity;
import com.fpt.duantn.io.entity.SizeEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface SizeRepository extends JpaRepository<SizeEntity, Long> {

    SizeEntity findSizeEntityById(Long sizeId);

    SizeEntity findBySizeCode(String sizeCode);

    @Query(value = "SELECT s.id, s.size_name," +
            "s.size_code " +
            "FROM size s " +
            "where 1=1 and (:filter is null or :filter = '' or (s.size_name like %:filter% or s.size_code like %:filter%))",
            nativeQuery = true)
    Page<SizeEntity> filter(@Param("filter") String filter, Pageable pageable);

    @Query(value = "SELECT count(1) " +
            "FROM size s " +
            "where 1=1 and (:filter is null or :filter = '' or (s.size_name like %:filter% or s.size_code like %:filter%))",
            nativeQuery = true)
    Long count(@Param("filter") String filter);
}

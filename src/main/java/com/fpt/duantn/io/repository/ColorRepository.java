package com.fpt.duantn.io.repository;

import com.fpt.duantn.io.entity.AddressEntity;
import com.fpt.duantn.io.entity.ColorEntity;
import com.fpt.duantn.io.entity.EmployeeEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.awt.*;


@Repository
public interface ColorRepository extends JpaRepository<ColorEntity, Long> {

    @Query(value = "SELECT c.id, c.color_name, c.color_code " +
            "FROM colors c ", nativeQuery = true)

    Page<ColorEntity> getColors(Pageable pageable);


    ColorEntity findByColorCode(String colorCode);
    ColorEntity findColorEntityById(Long colorId);

    @Query(value = "SELECT c.id, c.color_name," +
            "c.color_code " +
            "FROM colors c " +
            "where 1=1 and (:filter is null or :filter = '' or (c.color_name like %:filter% or c.color_code like %:filter%))",
            nativeQuery = true)
    Page<ColorEntity> filter(@Param("filter") String filter, Pageable pageable);

    @Query(value = "SELECT count(1) " +
            "FROM colors c " +
            "where 1=1 and (:filter is null or :filter = '' or (c.color_name like %:filter% or c.color_code like %:filter%))",
            nativeQuery = true)
    Long count(@Param("filter") String filter);

}

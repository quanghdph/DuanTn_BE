package com.fpt.duantn.io.repository;

import com.fpt.duantn.io.entity.AddressEntity;
import com.fpt.duantn.io.entity.ImageEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ImageRepository extends JpaRepository<ImageEntity, Long> {

    ImageEntity findImageById(Long imageId);

    Page<ImageEntity> findByProductContainingOrderByIdAsc(String ImageName, Pageable pageable);

    @Query(value = "SELECT i.id, i.image," +
            "i.product_id,i.type " +
            "FROM images i " +
            "where 1=1 and (:filter is null or :filter = '' or (i.type like %:filter% or i.product_id like %:filter%))",
            nativeQuery = true)
    Page<ImageEntity> filter(@Param("filter") String filter, Pageable pageable);

    @Query(value = "SELECT count(1) " +
            "FROM images i " +
            "where 1=1 and (:filter is null or :filter = '' or (i.type like %:filter% or i.product_id like %:filter%))",
            nativeQuery = true)
    Long count(@Param("filter") String filter);
    @Query("select i.id from ImageEntity i where i.product.id =:id ")
    List<Long> findIdByProductId(Long id);
}

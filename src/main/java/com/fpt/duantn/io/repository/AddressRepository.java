package com.fpt.duantn.io.repository;

import com.fpt.duantn.io.entity.AddressEntity;
import com.fpt.duantn.io.entity.ColorEntity;
import com.fpt.duantn.io.entity.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface AddressRepository extends JpaRepository<AddressEntity, Long> {

    AddressEntity findByAddressCode(String addressCode);

    AddressEntity findAddressEntityById(Long addressId);


    Page<ColorEntity> findByCityContainingOrderByIdAsc(String addressName, Pageable pageable);

    @Query(value = "SELECT a.id, a.custome_id," +
            "a.city,a.district,a.ward,a.address_code,a.address_detail,a.update_date, " +
            "a.create_date " +
            "FROM addresses a " +
            "where 1=1 and (:filter is null or :filter = '' or (a.city like %:filter% or a.address_code like %:filter%))",
            nativeQuery = true)
    Page<AddressEntity> filter(@Param("filter") String filter, Pageable pageable);

    @Query(value = "SELECT count(1) " +
            "FROM addresses a " +
            "where 1=1 and (:filter is null or :filter = '' or (a.city like %:filter% or a.address_code like %:filter%))",
            nativeQuery = true)
    Long count(@Param("filter") String filter);
}

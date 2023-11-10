package com.fpt.duantn.io.repository;

import com.fpt.duantn.io.entity.CartEntity;
import com.fpt.duantn.io.entity.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CartRepository extends JpaRepository<CartEntity, Long> {

    CartEntity findByCartCode(String cartCode);

    Page<CartEntity> findByCartCodeContainingOrderByIdAsc(String cartName, Pageable pageable);

    @Query(value = "SELECT c.id, c.cart_code," +
            "c.employee_id,c.create_date,c.update_date, " +
            "c.status " +
            "FROM cart c " +
            "where 1=1 and (:filter is null or :filter = '' or (c.employee_id like %:filter% or c.status like %:filter% or c.cart_code like %:filter%))",
            nativeQuery = true)
    Page<CartEntity> filter(@Param("filter") String filter, Pageable pageable);

    @Query(value = "SELECT count(1) " +
            "FROM cart c " +
            "where 1=1 and (:filter is null or :filter = '' or (c.employee_id like %:filter% or c.status like %:filter% or c.cart_code like %:filter%))",
            nativeQuery = true)
    Long count(@Param("filter") String filter);
}

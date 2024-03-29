package com.fpt.duantn.io.repository;

import com.fpt.duantn.io.entity.CartDetailEntity;
import com.fpt.duantn.io.entity.CustomerEntity;
import com.fpt.duantn.io.entity.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;


@Repository
public interface CartDetailRepository extends JpaRepository<CartDetailEntity, Long> {

//    CartDetailEntity findCartDetailEntityById(Long cartDetailId);

//    Page<CartDetailEntity> findByCartDetailNameContainingOrderByIdAsc(String cartDetailName, Pageable pageable);


//    @Query(value = "SELECT cd.id, cd.product_detail_id," +
//            "cd.cart_id,cd.create_date,cd.update_date, " +
//            "cd.status " +
//            "FROM cart_detail cd " +
//            "where 1=1 and (:filter is null or :filter = '' or (cd.cart_id like %:filter% or cd.status like %:filter% or cd.product_detail_id like %:filter%))",
//            nativeQuery = true)
//    Page<CartDetailEntity> filter(@Param("filter") String filter, Pageable pageable);
//
//    @Query(value = "SELECT count(1) " +
//            "FROM cart_detail cd " +
//            "where 1=1 and (:filter is null or :filter = '' or (cd.cart_id like %:filter% or cd.status like %:filter% or cd.product_detail_id like %:filter%))",
//            nativeQuery = true)
//    Long count(@Param("filter") String filter);

    List<CartDetailEntity> findByCustomer(CustomerEntity customerEntity);
    List<CartDetailEntity>findByCustomerIdAndStatus(Long customerId, Integer status);
    boolean existsByCustomerIdAndProductDetailIdAndStatus(Long customerId,Long productDetailId,Integer status);
    Page<CartDetailEntity> findByCustomerIdAndStatus(Long customerId, Integer status, Pageable pageable);
    @Transactional
    @Modifying
    int deleteByCustomer(CustomerEntity customer);

    @Transactional
    @Modifying
    int deleteByProductDetailIdAndCustomerId(Long productDetailID ,Long customerId);
}

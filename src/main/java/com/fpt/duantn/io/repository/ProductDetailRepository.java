package com.fpt.duantn.io.repository;


import com.fpt.duantn.io.entity.ProductDetailEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductDetailRepository extends JpaRepository<ProductDetailEntity, Long> {

    ProductDetailEntity findProductDetailEntityById(Long productDetailId);

    @Query(value = "SELECT pd.id, pd.price, pd.quantity, " +
            "pd.status, " +
            "p.id AS product_id, p.price " +
            "FROM product_detail pd " +
            "JOIN products p ON pd.id = p.brand_id " +
            "JOIN categories c ON p.category_id = c.id", nativeQuery = true)

    Page<ProductDetailEntity> getProductsDetailAndColors(Pageable pageable);

//    Page<ProductDetailEntity> findByProductContainingOrderByIdAsc(String productDetailCode,Pageable pageable);

    @Query(value = "SELECT pd.id, pd.product_id, " +
            "pd.color_id,pd.size_id,pd.quantity,pd.create_date,pd.update_date, " +
            "pd.status,pd.price " +
            "FROM product_detail pd " +
            "where 1=1 and (:filter is null or :filter = '' or (pd.price like %:filter% or pd.status like %:filter% or pd.quantity like %:filter%))",
            nativeQuery = true)
    Page<ProductDetailEntity> filter(@Param("filter") String filter, Pageable pageable);

    @Query(value = "SELECT pd.id, pd.product_id, " +
            "pd.color_id,pd.size_id,pd.quantity,pd.create_date,pd.update_date, " +
            "pd.status,pd.price " +
            "FROM product_detail pd " +
            "where pd.product_id = :idProduct and (:filter is null or :filter = '' or (pd.price like %:filter% or pd.status like %:filter% or pd.quantity like %:filter%))",
            nativeQuery = true)
    Page<ProductDetailEntity> filter(@Param("idProduct") Long idProduct,@Param("filter") String filter, Pageable pageable);

    @Query(value = "SELECT count(1) " +
            "FROM product_detail pd " +
            "where 1=1 and (:filter is null or :filter = '' or (pd.price like %:filter% or pd.status like %:filter% or pd.quantity like %:filter%))",
            nativeQuery = true)
    Long count(@Param("filter") String filter);

    @Query("SELECT pd FROM ProductDetailEntity pd " +
            "WHERE pd.product.id = :productId AND pd.color.id = :colorId AND pd.size.id = :sizeId")
    ProductDetailEntity findByProductIdAndColorIdAndSizeId(@Param("productId") Long productId,
                                               @Param("colorId") Long colorId,
                                               @Param("sizeId") Long sizeId);

}

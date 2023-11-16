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

    @Query(value = "SELECT pd.id, pd.default_price, pd.price, pd.amount, " +
            "pd.status, " +
            "p.id AS product_id, p.price " +
            "FROM product_detail pd " +
            "JOIN products p ON pd.id = p.brand_id " +
            "JOIN categories c ON p.category_id = c.id", nativeQuery = true)

    Page<ProductDetailEntity> getProductsDetailAndColors(Pageable pageable);

//    Page<ProductDetailEntity> findByProductContainingOrderByIdAsc(String productDetailCode,Pageable pageable);

    @Query(value = "SELECT pd.id, pd.product_id, " +
            "pd.color_id,pd.size_id,pd.default_price,pd.amount,pd.create_date,pd.update_date, " +
            "pd.status,pd.price " +
            "FROM product_detail pd " +
            "where 1=1 and (:filter is null or :filter = '' or (pd.price like %:filter% or pd.status like %:filter% or pd.amount like %:filter% or pd.amount like %:filter%))",
            nativeQuery = true)
    Page<ProductDetailEntity> filter(@Param("filter") String filter, Pageable pageable);

    @Query(value = "SELECT count(1) " +
            "FROM product_detail pd " +
            "where 1=1 and (:filter is null or :filter = '' or (pd.price like %:filter% or pd.status like %:filter% or pd.amount like %:filter% or pd.amount like %:filter%))",
            nativeQuery = true)
    Long count(@Param("filter") String filter);

    @Query("SELECT pd.product.id AS productId, pd.color.id AS colorId, pd.size.id AS sizeId, pd.defaultPrice " +
            "FROM ProductDetailEntity pd " +
            "WHERE pd.product.id = :productId OR pd.color.id = :colorId OR pd.size.id = :sizeId")
    ProductDetailEntity findByColorIdAndSizeId(@Param("productId") Long productId,
                                               @Param("colorId") Long colorId,
                                               @Param("sizeId") Long sizeId);

}

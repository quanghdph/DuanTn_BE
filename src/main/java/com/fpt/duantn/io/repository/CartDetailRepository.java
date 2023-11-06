package com.fpt.duantn.io.repository;

import com.fpt.duantn.io.entity.CartDetailEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartDetailRepository extends JpaRepository<CartDetailEntity, Long> {

    CartDetailEntity findCartDetailEntityById(Long cartDetailId);



//    Page<CartDetailEntity> findByCartDetailNameContainingOrderByIdAsc(String cartDetailName, Pageable pageable);

}

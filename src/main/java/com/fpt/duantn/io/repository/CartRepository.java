package com.fpt.duantn.io.repository;

import com.fpt.duantn.io.entity.CartEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<CartEntity, Long> {

    CartEntity findByCartCode(String cartCode);

    Page<CartEntity> findByCartCodeContainingOrderByIdAsc(String cartName, Pageable pageable);

}

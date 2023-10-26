package com.fpt.duantn.io.repository;

import com.fpt.duantn.io.entity.SleeveEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SleeveRepository extends JpaRepository<SleeveEntity, Long> {

    SleeveEntity findBySleeveCode(String sleveCode);

    Page<SleeveEntity> findBySleeveNameContainingOrderByIdAsc(String sleveName, Pageable pageable);


}

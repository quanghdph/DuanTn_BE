package com.fpt.duantn.io.repository;

import com.fpt.duantn.io.entity.CollarEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CollarRepository extends JpaRepository<CollarEntity, Long> {

    CollarEntity findByCollarCode(String collarCode);

    Page<CollarEntity> findByCollarNameContainingOrderByIdAsc(String collarName, Pageable pageable);

}

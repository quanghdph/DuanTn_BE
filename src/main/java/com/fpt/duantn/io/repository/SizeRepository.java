package com.fpt.duantn.io.repository;

import com.fpt.duantn.io.entity.SizeEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SizeRepository extends JpaRepository<SizeEntity, Long> {

    SizeEntity findBySizeCode(String sizeCode);

    Page<SizeEntity> findBySizeNameContainingOrderByIdAsc(String sizeName, Pageable pageable);
}

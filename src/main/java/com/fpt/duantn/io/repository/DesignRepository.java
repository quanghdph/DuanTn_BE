package com.fpt.duantn.io.repository;

import com.fpt.duantn.io.entity.DesignEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DesignRepository extends JpaRepository<DesignEntity, Long> {

    DesignEntity findByDesignCode(String designCode);

    Page<DesignEntity> findByDesignNameContainingOrderByIdAsc(String designName, Pageable pageable);

}

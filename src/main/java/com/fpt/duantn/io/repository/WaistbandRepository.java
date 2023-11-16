package com.fpt.duantn.io.repository;

import com.fpt.duantn.io.entity.WaistbandEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface WaistbandRepository extends JpaRepository<WaistbandEntity, Long> {

    WaistbandEntity findByWaistbandCode(String waistbandCode);

    Page<WaistbandEntity> findByWaistbandNameContainingOrderByIdAsc(String waistbandName, Pageable pageable);

}

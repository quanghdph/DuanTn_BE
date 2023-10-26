package com.fpt.duantn.io.repository;

import com.fpt.duantn.io.entity.PatternEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatternRepository extends JpaRepository<PatternEntity, Long> {

    PatternEntity findByPatternCode(String patternCode);

    Page<PatternEntity> findByPatternNameContainingOrderByIdAsc(String patternName, Pageable pageable);
    
}

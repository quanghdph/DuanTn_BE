package com.fpt.duantn.io.repository;

import com.fpt.duantn.io.entity.ImageEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ImageRepository extends JpaRepository<ImageEntity, Long> {

    ImageEntity findImageById(Long ImageId);

    Page<ImageEntity> findByProductContainingOrderByIdAsc(String ImageName, Pageable pageable);

}

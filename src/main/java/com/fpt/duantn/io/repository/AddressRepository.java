package com.fpt.duantn.io.repository;

import com.fpt.duantn.io.entity.AddressEntity;
import com.fpt.duantn.io.entity.ColorEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AddressRepository extends JpaRepository<AddressEntity, Long> {

    AddressEntity findByAddressCode(String addressCode);

    Page<ColorEntity> findByAddressDetailContainingOrderByIdAsc(String addressName, Pageable pageable);


}

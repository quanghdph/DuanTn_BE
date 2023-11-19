package com.fpt.duantn.services;

import com.fpt.duantn.shrared.dto.CRUD.SizeDto;

import java.util.List;

public interface SizeService {
    SizeDto createSize(SizeDto size);
    SizeDto getSizeById(Long sizeId);
    SizeDto updateSize(Long sizeId, SizeDto size);
    void deleteSize(Long sizeId);
    List<SizeDto> getSizes(int page, int limit, String filter);
    Long count(String filter);


}

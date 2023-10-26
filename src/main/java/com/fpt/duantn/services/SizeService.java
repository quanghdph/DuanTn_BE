package com.fpt.duantn.services;

import com.fpt.duantn.shrared.dto.CRUD.SizeDto;

import java.util.List;

public interface SizeService {
    SizeDto createSize(SizeDto size);
    SizeDto getSizeBySizeCode(String sizeCode);
    SizeDto updateSize(String sizeCode, SizeDto size);
    void deleteSize(String sizeCode);
    List<SizeDto> getSizes(int page, int limit);
    List<SizeDto> getSizeBySizeName(String sizeName, int page, int limit);

}

package com.fpt.duantn.services;

import com.fpt.duantn.shrared.dto.CRUD.ProductDto;
import com.fpt.duantn.shrared.dto.CRUD.WaistbandDto;

import java.util.List;

public interface WaistbandService {

    WaistbandDto createWaistband(WaistbandDto waistband);
    WaistbandDto getWaistbandById(Long waistbandId);
    WaistbandDto updateWaistband(Long waistbandId, WaistbandDto waistband);
    void deleteWaistband(Long waistbandId);
    List<WaistbandDto> getWaistbands(int page, int limit, String filter);
    Long count(String filter);
    List<WaistbandDto> getWaistbandByWaistbandName(String waistbandName, int page, int limit);
}

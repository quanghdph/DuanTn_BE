package com.fpt.duantn.services;

import com.fpt.duantn.shrared.dto.CRUD.WaistbandDto;

import java.util.List;

public interface WaistbandService {

    WaistbandDto createWaistband(WaistbandDto waistband);
    WaistbandDto getWaistbandByWaistbandCode(String waistbandCode);
    WaistbandDto updateWaistband(String waistbandCode, WaistbandDto waistband);
    void deleteWaistband(String waistbandCode);
    List<WaistbandDto> getWaistbands(int page, int limit);
    List<WaistbandDto> getWaistbandByWaistbandName(String waistbandName, int page, int limit);


}

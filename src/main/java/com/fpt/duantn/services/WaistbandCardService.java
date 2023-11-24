package com.fpt.duantn.services;

import com.fpt.duantn.shrared.dto.CRUD.WaistbandDto;

import java.util.List;

public interface WaistbandCardService {

    List<WaistbandDto> getWaistbandCard(int page, int limit);

}

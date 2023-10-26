package com.fpt.duantn.services;

import com.fpt.duantn.shrared.dto.CRUD.CollarDto;

import java.util.List;

public interface CollarService {

    CollarDto createCollar(CollarDto collar);
    CollarDto getCollarByCollarCode(String collarCode);
    CollarDto updateCollar(String collarCode, CollarDto collar);
    void deleteCollar(String collarCode);
    List<CollarDto> getCollars(int page, int limit);
    List<CollarDto> getCollarByCollarName(String collarName, int page, int limit);

    
}

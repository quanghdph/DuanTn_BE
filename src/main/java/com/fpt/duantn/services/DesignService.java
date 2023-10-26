package com.fpt.duantn.services;

import com.fpt.duantn.shrared.dto.CRUD.DesignDto;

import java.util.List;

public interface DesignService {

    DesignDto createDesign(DesignDto design);
    DesignDto getDesignByDesignCode(String designCode);
    DesignDto updateDesign(String designCode, DesignDto design);
    void deleteDesign(String designCode);
    List<DesignDto> getDesigns(int page, int limit);
    List<DesignDto> getDesignByDesignName(String designName, int page, int limit);
    
}

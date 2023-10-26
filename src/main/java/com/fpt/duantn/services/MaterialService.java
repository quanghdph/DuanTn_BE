package com.fpt.duantn.services;

import com.fpt.duantn.shrared.dto.CRUD.MaterialDto;

import java.util.List;

public interface MaterialService {
    MaterialDto createMaterial(MaterialDto material);
    MaterialDto getMaterialByMaterialCode(String materialCode);
    MaterialDto updateMaterial(String materialCode, MaterialDto material);
    void deleteMaterial(String materialCode);
    List<MaterialDto> getMaterials(int page, int limit);
    List<MaterialDto> getMaterialByMaterialName(String materialName, int page, int limit);

}

package com.fpt.duantn.services;

import com.fpt.duantn.shrared.dto.CRUD.MaterialDto;

import java.util.List;

public interface MaterialService {
    MaterialDto createMaterial(MaterialDto material);
    MaterialDto getMaterialById(Long materialId);
    MaterialDto updateMaterial(Long materialId, MaterialDto material);
    void deleteMaterial(Long materialId);
    List<MaterialDto> getMaterials(int page, int limit);
    List<MaterialDto> getMaterialByMaterialName(String materialName, int page, int limit);

}

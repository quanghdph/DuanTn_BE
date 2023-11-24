package com.fpt.duantn.services;

import com.fpt.duantn.shrared.dto.CRUD.MaterialDto;

import java.util.List;

public interface MaterialCardService {

    List<MaterialDto> getMaterialCard(int page, int limit);

}

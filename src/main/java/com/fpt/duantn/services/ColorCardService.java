package com.fpt.duantn.services;

import com.fpt.duantn.shrared.dto.CRUD.ColorDto;

import java.util.List;

public interface ColorCardService {

    List<ColorDto> getColorCard(int page, int limit);

}

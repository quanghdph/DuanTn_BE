package com.fpt.duantn.services;

import com.fpt.duantn.shrared.dto.CRUD.ColorDto;

import java.util.List;

public interface ColorService {
    ColorDto createColor(ColorDto color);
    ColorDto getColorById(Long colorId);
    ColorDto updateColor(Long colorId, ColorDto color);
    void deleteColor(Long colorId);
    List<ColorDto> getColors(int page, int limit, String filter);
    Long count(String filter);

}

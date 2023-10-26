package com.fpt.duantn.services;

import com.fpt.duantn.shrared.dto.CRUD.ColorDto;

import java.util.List;

public interface ColorService {
    ColorDto createColor(ColorDto color);
    ColorDto getColorByColorCode(String colorCode);
    ColorDto updateColor(String colorCode, ColorDto color);
    void deleteColor(String colorCode);
    List<ColorDto> getColors(int page, int limit);
    List<ColorDto> getColorByColorName(String colorName, int page, int limit);

}

package com.fpt.duantn.services;

import com.fpt.duantn.shrared.dto.CRUD.SleeveDto;

import java.util.List;

public interface SleeveService {
    SleeveDto createSleeve(SleeveDto sleeve);
    SleeveDto getSleeveBySleeveCode(String sleeveCode);
    SleeveDto updateSleeve(String sleeveCode, SleeveDto sleeve);
    void deleteSleeve(String sleeveCode);
    List<SleeveDto> getSleeves(int page, int limit);
    List<SleeveDto> getSleeveBySleeveName(String sleeveName, int page, int limit);

}

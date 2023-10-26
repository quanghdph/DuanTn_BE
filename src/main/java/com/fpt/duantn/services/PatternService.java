package com.fpt.duantn.services;

import com.fpt.duantn.shrared.dto.CRUD.PatternDto;

import java.util.List;

public interface PatternService {

    PatternDto createPattern(PatternDto pattern);
    PatternDto getPatternByPatternCode(String patternCode);
    PatternDto updatePattern(String patternCode, PatternDto pattern);
    void deletePattern(String patternCode);
    List<PatternDto> getPatterns(int page, int limit);
    List<PatternDto> getPatternByPatternName(String patternName, int page, int limit);

}

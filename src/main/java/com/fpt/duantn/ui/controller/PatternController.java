package com.fpt.duantn.ui.controller;

import com.fpt.duantn.services.PatternService;
import com.fpt.duantn.shrared.dto.CRUD.PatternDto;
import com.fpt.duantn.ui.model.request.PatternDetailsRequestModel;
import com.fpt.duantn.ui.model.response.PatternRest;
import com.fpt.duantn.ui.model.response.OperationStatusModel;
import com.fpt.duantn.ui.model.response.RequestOperationStatus;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/pattern")
public class PatternController {

    @Autowired
    PatternService patternService;

    @GetMapping(path = "/{id}")
    public PatternRest getPattern(@PathVariable String id) {
        PatternRest returnValue = new PatternRest();

        PatternDto patternDto = patternService.getPatternByPatternCode(id);
        ModelMapper modelMapper = new ModelMapper();
        returnValue = modelMapper.map(patternDto, PatternRest.class);

        return returnValue;
    }


    @PostMapping()
    public PatternRest createPattern(@RequestBody PatternDetailsRequestModel patternDetails) throws Exception {
        PatternRest returnValue = new PatternRest();

        ModelMapper modelMapper = new ModelMapper();
        PatternDto patternDto = modelMapper.map(patternDetails, PatternDto.class);

        PatternDto createdUser = patternService.createPattern(patternDto);
        returnValue = modelMapper.map(createdUser, PatternRest.class);

        return returnValue;
    }


    @GetMapping()
    public List<PatternRest> getPatterns(@RequestParam(value = "page", defaultValue = "0") int page,
                                     @RequestParam(value = "limit", defaultValue = "2") int limit) {
        List<PatternRest> returnValue = new ArrayList<>();

        List<PatternDto> patterns = patternService.getPatterns(page, limit);

        for (PatternDto patternDto : patterns) {
            PatternRest patternModel = new PatternRest();
            BeanUtils.copyProperties(patternDto, patternModel);
            returnValue.add(patternModel);
        }

        return returnValue;
    }


    @PutMapping(path = "/{id}")
    public PatternRest updatePattern(@PathVariable String id, @RequestBody PatternDetailsRequestModel patternDetails) {
        PatternRest returnValue = new PatternRest();

        PatternDto patternDto = new PatternDto();
        patternDto = new ModelMapper().map(patternDetails, PatternDto.class);

        PatternDto updatePattern = patternService.updatePattern(id, patternDto);
        returnValue = new ModelMapper().map(updatePattern, PatternRest.class);

        return returnValue;
    }

    @DeleteMapping(path = "/{id}")
    public OperationStatusModel deletePattern(@PathVariable String id) {
        OperationStatusModel returnValue = new OperationStatusModel();
        returnValue.setOperationName(RequestOperationName.DELETE.name());

        patternService.deletePattern(id);

        returnValue.setOperationResult(RequestOperationStatus.SUCCESS.name());
        return returnValue;
    }



    @GetMapping("/search")
    public List<PatternRest> searchPatterns(@RequestParam(value = "patternName") String patternName,
                                        @RequestParam(value = "page", defaultValue = "0") int page,
                                        @RequestParam(value = "limit", defaultValue = "2") int limit) {
        List<PatternRest> returnValue = new ArrayList<>();

        List<PatternDto> patterns = patternService.getPatternByPatternName(patternName, page, limit);

        for (PatternDto patternDto : patterns) {
            PatternRest patternModel = new PatternRest();
            BeanUtils.copyProperties(patternDto, patternModel);
            returnValue.add(patternModel);
        }

        return returnValue;
    }
}

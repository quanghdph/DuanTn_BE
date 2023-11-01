package com.fpt.duantn.ui.controller;


import com.fpt.duantn.services.ColorService;
import com.fpt.duantn.shrared.dto.CRUD.ColorDto;
import com.fpt.duantn.ui.model.request.ColorDetailsRequestModel;
import com.fpt.duantn.ui.model.response.ColorRest;
import com.fpt.duantn.ui.model.response.OperationStatusModel;
import com.fpt.duantn.ui.model.response.RequestOperationStatus;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("/color")
public class ColorController {

    @Autowired
    ColorService colorService;



    @GetMapping(path = "/{id}")
    public ColorRest getColor(@PathVariable String id) {
        ColorRest returnValue = new ColorRest();

        ColorDto colorDto = colorService.getColorByColorCode(id);
        ModelMapper modelMapper = new ModelMapper();
        returnValue = modelMapper.map(colorDto, ColorRest.class);

        return returnValue;
    }


    @PostMapping()
    public ColorRest createColor(@RequestBody ColorDetailsRequestModel colorDetails) throws Exception {
        ColorRest returnValue = new ColorRest();

        ModelMapper modelMapper = new ModelMapper();
        ColorDto colorDto = modelMapper.map(colorDetails, ColorDto.class);

        ColorDto createdUser = colorService.createColor(colorDto);
        returnValue = modelMapper.map(createdUser, ColorRest.class);

        return returnValue;
    }


    @GetMapping()
    public List<ColorRest> getColors(@RequestParam(value = "page", defaultValue = "0") int page,
                                             @RequestParam(value = "limit", defaultValue = "2") int limit) {
        List<ColorRest> returnValue = new ArrayList<>();

        List<ColorDto> colors = colorService.getColors(page, limit);

        for (ColorDto colorDto : colors) {
            ColorRest colorModel = new ColorRest();
            BeanUtils.copyProperties(colorDto, colorModel);
            returnValue.add(colorModel);
        }

        return returnValue;
    }


    @PutMapping(path = "/{id}")
    public ColorRest updateColor(@PathVariable String id, @RequestBody ColorDetailsRequestModel colorDetails) {
        ColorRest returnValue = new ColorRest();

        ColorDto colorDto = new ColorDto();
        colorDto = new ModelMapper().map(colorDetails, ColorDto.class);

        ColorDto updateColor = colorService.updateColor(id, colorDto);
        returnValue = new ModelMapper().map(updateColor, ColorRest.class);

        return returnValue;
    }

    @DeleteMapping(path = "/{id}")
    public OperationStatusModel deleteColor(@PathVariable String id) {
        OperationStatusModel returnValue = new OperationStatusModel();
        returnValue.setOperationName(RequestOperationName.DELETE.name());

        colorService.deleteColor(id);

        returnValue.setOperationResult(RequestOperationStatus.SUCCESS.name());
        return returnValue;
    }



    @GetMapping("/search")
    public List<ColorRest> searchColors(@RequestParam(value = "colorName") String colorName,
                                        @RequestParam(value = "page", defaultValue = "0") int page,
                                        @RequestParam(value = "limit", defaultValue = "2") int limit) {
        List<ColorRest> returnValue = new ArrayList<>();

        List<ColorDto> colors = colorService.getColorByColorName(colorName, page, limit);

        for (ColorDto colorDto : colors) {
            ColorRest colorModel = new ColorRest();
            BeanUtils.copyProperties(colorDto, colorModel);
            returnValue.add(colorModel);
        }

        return returnValue;
    }


}

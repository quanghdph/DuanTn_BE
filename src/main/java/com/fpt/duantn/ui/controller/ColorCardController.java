package com.fpt.duantn.ui.controller;

import com.fpt.duantn.services.ColorCardService;
import com.fpt.duantn.shrared.dto.CRUD.ColorDto;
import com.fpt.duantn.ui.model.response.ColorRest;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/color-list")
public class ColorCardController {

    @Autowired
    ColorCardService colorCardService;

    @GetMapping()
    public List<ColorRest> getColor(@RequestParam(value = "page", defaultValue = "0") int page,
                                    @RequestParam(value = "limit", defaultValue = "2") int limit) {
        List<ColorRest> returnValue = new ArrayList<>();

        List<ColorDto> colors = colorCardService.getColorCard(page, limit);

        for (ColorDto colorDto : colors) {
            ColorRest colorModel = new ColorRest();
            BeanUtils.copyProperties(colorDto, colorModel);
            returnValue.add(colorModel);
        }

        return returnValue;

    }
}

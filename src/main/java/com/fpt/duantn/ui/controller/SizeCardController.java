package com.fpt.duantn.ui.controller;

import com.fpt.duantn.services.SizeCardService;
import com.fpt.duantn.shrared.dto.CRUD.SizeDto;
import com.fpt.duantn.ui.model.response.SizeRest;
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
@RequestMapping("/size-list")
public class SizeCardController {

    @Autowired
    SizeCardService sizeCardService;

    @GetMapping()
    public List<SizeRest> getSize(@RequestParam(value = "page", defaultValue = "0") int page,
                                    @RequestParam(value = "limit", defaultValue = "2") int limit) {
        List<SizeRest> returnValue = new ArrayList<>();

        List<SizeDto> sizes = sizeCardService.getSizeCard(page, limit);

        for (SizeDto sizeDto : sizes) {
            SizeRest sizeModel = new SizeRest();
            BeanUtils.copyProperties(sizeDto, sizeModel);
            returnValue.add(sizeModel);
        }

        return returnValue;

    }
}

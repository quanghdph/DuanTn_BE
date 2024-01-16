package com.fpt.duantn.ui.controller;

import com.fpt.duantn.services.MaterialCardService;
import com.fpt.duantn.shrared.dto.CRUD.MaterialDto;
import com.fpt.duantn.ui.model.response.MaterialRest;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
@CrossOrigin(origins = {"http://localhost:4201","http://localhost:4200"})
@RestController
@RequestMapping("/material-list")
public class MaterialCardController {

    @Autowired
    MaterialCardService materialCardService;

    @GetMapping()
    public List<MaterialRest> getMaterial(@RequestParam(value = "page", defaultValue = "0") int page,
                                            @RequestParam(value = "limit", defaultValue = "2") int limit) {
        List<MaterialRest> returnValue = new ArrayList<>();

        List<MaterialDto> materials = materialCardService.getMaterialCard(page, limit);

        for (MaterialDto materialDto : materials) {
            MaterialRest materialModel = new MaterialRest();
            BeanUtils.copyProperties(materialDto, materialModel);
            returnValue.add(materialModel);
        }

        return returnValue;

    }
}

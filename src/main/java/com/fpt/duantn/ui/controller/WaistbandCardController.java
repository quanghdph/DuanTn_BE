package com.fpt.duantn.ui.controller;

import com.fpt.duantn.services.WaistbandCardService;
import com.fpt.duantn.shrared.dto.CRUD.WaistbandDto;
import com.fpt.duantn.ui.model.response.WaistbandRest;
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
@RequestMapping("/waistband-list")
public class WaistbandCardController {
    @Autowired
    WaistbandCardService waistbandCardService;

    @GetMapping()
    public List<WaistbandRest> getWaistband(@RequestParam(value = "page", defaultValue = "0") int page,
                                    @RequestParam(value = "limit", defaultValue = "2") int limit) {
        List<WaistbandRest> returnValue = new ArrayList<>();

        List<WaistbandDto> waistbands = waistbandCardService.getWaistbandCard(page, limit);

        for (WaistbandDto waistbandDto : waistbands) {
            WaistbandRest waistbandModel = new WaistbandRest();
            BeanUtils.copyProperties(waistbandDto, waistbandModel);
            returnValue.add(waistbandModel);
        }

        return returnValue;

    }
}

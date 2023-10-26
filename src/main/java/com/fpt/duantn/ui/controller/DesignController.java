package com.fpt.duantn.ui.controller;

import com.fpt.duantn.services.DesignService;
import com.fpt.duantn.shrared.dto.CRUD.DesignDto;
import com.fpt.duantn.ui.model.request.DesignDetailsRequestModel;
import com.fpt.duantn.ui.model.response.DesignRest;
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
@RequestMapping("/design")
public class DesignController {
    
    @Autowired
    DesignService designService;

    @GetMapping(path = "/{id}")
    public DesignRest getDesign(@PathVariable String id) {
        DesignRest returnValue = new DesignRest();

        DesignDto designDto = designService.getDesignByDesignCode(id);
        ModelMapper modelMapper = new ModelMapper();
        returnValue = modelMapper.map(designDto, DesignRest.class);

        return returnValue;
    }


    @PostMapping()
    public DesignRest createDesign(@RequestBody DesignDetailsRequestModel designDetails) throws Exception {
        DesignRest returnValue = new DesignRest();

        ModelMapper modelMapper = new ModelMapper();
        DesignDto designDto = modelMapper.map(designDetails, DesignDto.class);

        DesignDto createdUser = designService.createDesign(designDto);
        returnValue = modelMapper.map(createdUser, DesignRest.class);

        return returnValue;
    }


    @GetMapping()
    public List<DesignRest> getDesigns(@RequestParam(value = "page", defaultValue = "0") int page,
                                     @RequestParam(value = "limit", defaultValue = "2") int limit) {
        List<DesignRest> returnValue = new ArrayList<>();

        List<DesignDto> designs = designService.getDesigns(page, limit);

        for (DesignDto designDto : designs) {
            DesignRest designModel = new DesignRest();
            BeanUtils.copyProperties(designDto, designModel);
            returnValue.add(designModel);
        }

        return returnValue;
    }


    @PutMapping(path = "/{id}")
    public DesignRest updateDesign(@PathVariable String id, @RequestBody DesignDetailsRequestModel designDetails) {
        DesignRest returnValue = new DesignRest();

        DesignDto designDto = new DesignDto();
        designDto = new ModelMapper().map(designDetails, DesignDto.class);

        DesignDto updateDesign = designService.updateDesign(id, designDto);
        returnValue = new ModelMapper().map(updateDesign, DesignRest.class);

        return returnValue;
    }

    @DeleteMapping(path = "/{id}")
    public OperationStatusModel deleteDesign(@PathVariable String id) {
        OperationStatusModel returnValue = new OperationStatusModel();
        returnValue.setOperationName(RequestOperationName.DELETE.name());

        designService.deleteDesign(id);

        returnValue.setOperationResult(RequestOperationStatus.SUCCESS.name());
        return returnValue;
    }



    @GetMapping("/search")
    public List<DesignRest> searchDesigns(@RequestParam(value = "designName") String designName,
                                        @RequestParam(value = "page", defaultValue = "0") int page,
                                        @RequestParam(value = "limit", defaultValue = "2") int limit) {
        List<DesignRest> returnValue = new ArrayList<>();

        List<DesignDto> designs = designService.getDesignByDesignName(designName, page, limit);

        for (DesignDto designDto : designs) {
            DesignRest designModel = new DesignRest();
            BeanUtils.copyProperties(designDto, designModel);
            returnValue.add(designModel);
        }

        return returnValue;
    }
}

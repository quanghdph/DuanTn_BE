package com.fpt.duantn.ui.controller;

import com.fpt.duantn.services.ImageService;
import com.fpt.duantn.shrared.dto.CRUD.ImageDto;
import com.fpt.duantn.ui.model.request.ImageDetailsRequestModel;
import com.fpt.duantn.ui.model.response.ImageRest;
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
@RequestMapping("/image")
public class ImageController {

    @Autowired
    ImageService imageService;

    @GetMapping(path = "/{id}")
    public ImageRest getImage(@PathVariable Long id) {
        ImageRest returnValue = new ImageRest();

        ImageDto imageDto = imageService.getImageByImageCode(id);
        ModelMapper modelMapper = new ModelMapper();
        returnValue = modelMapper.map(imageDto, ImageRest.class);

        return returnValue;
    }

    @PostMapping()
    public ImageRest createImage(@RequestBody ImageDetailsRequestModel imageDetails) throws Exception {
        ImageRest returnValue = new ImageRest();

        ModelMapper modelMapper = new ModelMapper();
        ImageDto imageDto = modelMapper.map(imageDetails, ImageDto.class);

        imageDto.setProduct(imageDetails.getProduct());

        ImageDto createdUser = imageService.createImage(imageDto);
        returnValue = modelMapper.map(createdUser, ImageRest.class);

        return returnValue;
    }

    @GetMapping()
    public List<ImageRest> getImages(@RequestParam(value = "page", defaultValue = "0") int page,
                                       @RequestParam(value = "limit", defaultValue = "2") int limit) {
        List<ImageRest> returnValue = new ArrayList<>();

        List<ImageDto> images = imageService.getImages(page, limit);

        for (ImageDto imageDto : images) {
            ImageRest imageModel = new ImageRest();
            BeanUtils.copyProperties(imageDto, imageModel);
            returnValue.add(imageModel);
        }

        return returnValue;
    }


    @PutMapping(path = "/{id}")
    public ImageRest updateImage(@PathVariable Long id, @RequestBody ImageDetailsRequestModel imageDetails) {
        ImageRest returnValue = new ImageRest();

        ImageDto imageDto = new ImageDto();
        imageDto = new ModelMapper().map(imageDetails, ImageDto.class);

        imageDto.setProduct(imageDetails.getProduct());

        ImageDto updateImage = imageService.updateImage(id, imageDto);
        returnValue = new ModelMapper().map(updateImage, ImageRest.class);

        return returnValue;
    }

    @DeleteMapping(path = "/{id}")
    public OperationStatusModel deleteImage(@PathVariable Long id) {
        OperationStatusModel returnValue = new OperationStatusModel();
        returnValue.setOperationName(RequestOperationName.DELETE.name());

        imageService.deleteImage(id);

        returnValue.setOperationResult(RequestOperationStatus.SUCCESS.name());
        return returnValue;
    }



    @GetMapping("/search")
    public List<ImageRest> searchImages(@RequestParam(value = "imageName") String imageName,
                                          @RequestParam(value = "page", defaultValue = "0") int page,
                                          @RequestParam(value = "limit", defaultValue = "2") int limit) {
        List<ImageRest> returnValue = new ArrayList<>();

        List<ImageDto> images = imageService.getImageByImageName(imageName, page, limit);

        for (ImageDto imageDto : images) {
            ImageRest imageModel = new ImageRest();
            BeanUtils.copyProperties(imageDto, imageModel);
            returnValue.add(imageModel);
        }

        return returnValue;
    }

}

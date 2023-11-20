package com.fpt.duantn.ui.controller;


import com.fpt.duantn.services.ImageService;
import com.fpt.duantn.shrared.dto.CRUD.ImageDto;
import com.fpt.duantn.ui.model.request.ImageRequest;
import com.fpt.duantn.ui.model.response.ImageRest;
import com.fpt.duantn.ui.model.response.PaginationRest;
import jakarta.servlet.http.HttpServletRequest;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/image")
public class ImageController {

    @Autowired
    ImageService imageService;

    @GetMapping(path = "/{id}")
    public ImageRest getImage(@PathVariable Long id) {
        ImageRest returnValue = new ImageRest();

        ImageDto imageDto = imageService.getImageById(id);
        ModelMapper modelMapper = new ModelMapper();
        returnValue = modelMapper.map(imageDto, ImageRest.class);

        return returnValue;
    }

//    @GetMapping()
//    public List<ImageRest> getImages(@RequestParam(value = "page", defaultValue = "0") int page,
//                                       @RequestParam(value = "limit", defaultValue = "2") int limit) {
//        List<ImageRest> returnValue = new ArrayList<>();
//
//        List<ImageDto> images = imageService.getImages(page, limit);
//
//        for (ImageDto imageDto : images) {
//            ImageRest imageModel = new ImageRest();
//            BeanUtils.copyProperties(imageDto, imageModel);
//            returnValue.add(imageModel);
//        }
//
//        return returnValue;
//    }

    @ResponseBody
    @GetMapping("")
    public PaginationRest getProductDetail(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "limit", defaultValue = "2") int limit,
            @RequestParam(value = "filter", defaultValue = "") String filter,
            HttpServletRequest request, Model model) {

        List<ImageRest> returnValue = new ArrayList<>();

        List<ImageDto> images = imageService.getImages(page, limit);

        for (ImageDto imageDto : images) {
            ImageRest imageModel = new ImageRest();
            BeanUtils.copyProperties(imageDto, imageModel);
            returnValue.add(imageModel);
        }
        PaginationRest paginationRest = new PaginationRest();
        paginationRest.setListImage(returnValue);
        paginationRest.setTotal(imageService.count(filter));

        return paginationRest;
    }

    @PostMapping()
    public ImageRest createImage(@RequestBody ImageRequest imageDetails) throws Exception {
        ImageRest returnValue = new ImageRest();

        ModelMapper modelMapper = new ModelMapper();
        ImageDto imageDto = modelMapper.map(imageDetails, ImageDto.class);

        imageDto.setProduct(imageDetails.getProduct());

        ImageDto createdUser = imageService.createImage(imageDto);
        returnValue = modelMapper.map(createdUser, ImageRest.class);

        return returnValue;
    }


    @PutMapping(path = "/{id}")
    public ImageRest updateImage(@PathVariable Long id, @RequestBody ImageRequest imageDetails) {
        ImageRest returnValue = new ImageRest();

        ImageDto imageDto = new ImageDto();
        imageDto = new ModelMapper().map(imageDetails, ImageDto.class);

        imageDto.setProduct(imageDetails.getProduct());

        ImageDto updateImage = imageService.updateImage(id, imageDto);
        returnValue = new ModelMapper().map(updateImage, ImageRest.class);

        return returnValue;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        if (imageService.existsById(id)){
            imageService.deleteImage(id);
            return ResponseEntity.ok().build();
        }else {
            return ResponseEntity.badRequest().body("Không tồn tại");
        }
    }



    @GetMapping("/search")
    public List<ImageRest> searchImages(@RequestParam(value = "imageName") String imageName,
                                          @RequestParam(value = "page", defaultValue = "0") int page,
                                          @RequestParam(value = "limit", defaultValue = "2") int limit) {
        List<ImageRest> returnValue = new ArrayList<>();

        List<ImageDto> images = imageService.getImageByProductId(imageName, page, limit);

        for (ImageDto imageDto : images) {
            ImageRest imageModel = new ImageRest();
            BeanUtils.copyProperties(imageDto, imageModel);
            returnValue.add(imageModel);
        }

        return returnValue;
    }

}

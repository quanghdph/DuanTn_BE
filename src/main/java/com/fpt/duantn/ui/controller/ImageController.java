package com.fpt.duantn.ui.controller;


import com.fpt.duantn.io.entity.ImageEntity;
import com.fpt.duantn.io.entity.ProductEntity;
import com.fpt.duantn.services.ImageService;
import com.fpt.duantn.services.ProductService;
import com.fpt.duantn.shrared.dto.CRUD.ImageDto;
import com.fpt.duantn.ui.model.request.ImageRequest;
import com.fpt.duantn.ui.model.response.ImageRest;
import com.fpt.duantn.ui.model.response.PaginationRest;
import com.fpt.duantn.util.FileImgUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = {"http://localhost:4201","http://localhost:4200"})
@RestController
@RequestMapping("/image")
public class ImageController {

    @Autowired
    ImageService imageService;

    @Autowired
    ProductService productService;

    @Autowired
    FileImgUtil fileImgUtil;
    @GetMapping(path = "/{id}")
    public ImageRest getImage(@PathVariable Long id) {
        ImageRest returnValue = new ImageRest();

        ImageDto imageDto = imageService.getImageById(id);
        ModelMapper modelMapper = new ModelMapper();
        returnValue = modelMapper.map(imageDto, ImageRest.class);

        return returnValue;
    }

    @ResponseBody
    @GetMapping()
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
    @PreAuthorize("hasAnyRole('ADMIN', 'EMPLOYEE')")
    public List<ImageDto> createImage(@RequestParam Long productId, @RequestPart("images") MultipartFile []multipartFile) throws Exception {
         List<ImageDto> returnValue = new ArrayList<>();
        ProductEntity productEntity = productService.findById(productId).orElse(null);
        if (productEntity == null){

        } else {
            ModelMapper modelMapper = new ModelMapper();
            for (int i = 0; i< multipartFile.length; i++){
                ImageDto imageDto = new ImageDto();
                imageDto.setProduct(productEntity);
                imageDto.setImage(new SerialBlob(multipartFile[i].getBytes()));
                ImageDto createdUser = imageService.createImage(imageDto);
                createdUser.setImage(null);
                returnValue.add(createdUser);
            }
        }
        return returnValue;
    }


    @PutMapping(path = "/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'EMPLOYEE')")
    public ImageRest updateImage(@PathVariable Long id, @RequestBody ImageRequest imageDetails) {
        ImageRest returnValue = new ImageRest();

        ImageDto imageDto = new ImageDto();
        imageDto = new ModelMapper().map(imageDetails, ImageDto.class);

        imageDto.setProduct(imageDetails.getProduct());

        ImageDto updateImage = imageService.updateImage(id, imageDto);
        returnValue = new ModelMapper().map(updateImage, ImageRest.class);

        return returnValue;
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'EMPLOYEE')")
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
                                          @RequestParam(value = "limit", defaultValue = "5") int limit) {
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

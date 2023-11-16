package com.fpt.duantn.services;

import com.fpt.duantn.shrared.dto.CRUD.ImageDto;

import java.util.List;

public interface ImageService {

    ImageDto createImage(ImageDto image);
    ImageDto getImageByImageCode(Long imageCode);
    ImageDto updateImage(Long imageCode, ImageDto image);
    void deleteImage(Long imageId);
    List<ImageDto> getImages(int page, int limit);
    List<ImageDto> getImageByProductId(String imageName, int page, int limit);


}

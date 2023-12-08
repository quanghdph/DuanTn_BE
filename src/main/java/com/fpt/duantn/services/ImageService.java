package com.fpt.duantn.services;

import com.fpt.duantn.io.entity.ImageEntity;
import com.fpt.duantn.shrared.dto.CRUD.AddressDto;
import com.fpt.duantn.shrared.dto.CRUD.ImageDto;

import java.util.List;

public interface ImageService {

    ImageDto createImage(ImageDto image);
    ImageDto getImageById(Long imageId);

    <S extends ImageEntity> List<S> saveAll(Iterable<S> entities);

    ImageDto updateImage(Long imageId, ImageDto image);

    void deleteImage(Long imageId);
    boolean existsById(Long id);

    List<ImageDto> getImages(int page, int limit);
    List<ImageDto> getImageByProductId(String imageName, int page, int limit);
    List<ImageDto> getImages(int page, int limit, String filter);
    Long count(String filter);

}

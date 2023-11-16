package com.fpt.duantn.services.impl;

import com.fpt.duantn.exceptions.ImageServiceException;
import com.fpt.duantn.io.entity.ImageEntity;
import com.fpt.duantn.io.repository.ImageRepository;
import com.fpt.duantn.services.ImageService;
import com.fpt.duantn.shrared.Utils;
import com.fpt.duantn.shrared.dto.CRUD.ImageDto;
import com.fpt.duantn.ui.model.response.ErrorMessages;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ImageServiceImpl implements ImageService {

    @Autowired
    ImageRepository imageRepository;

    @Autowired
    Utils utils;

    @Override
    public ImageDto createImage(ImageDto image) {
        return null;
    }

    @Override
    public ImageDto getImageByImageCode(Long imageCode) {
        ImageDto returnValue = new ImageDto();
        ImageEntity imageEntity = imageRepository.findImageById(imageCode);

        if (imageEntity == null)
            throw new ImageServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

        BeanUtils.copyProperties(imageEntity, returnValue);

        return returnValue;
    }

    @Override
    public ImageDto updateImage(Long imageCode, ImageDto image) {
        return null;
    }

    @Override
    public void deleteImage(Long imageId) {
        ImageEntity imageEntity = imageRepository.findImageById(imageId);

        if (imageEntity == null)
            throw new ImageServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

        imageRepository.delete(imageEntity);
    }

    @Override
    public List<ImageDto> getImages(int page, int limit) {
        List<ImageDto> returnValue = new ArrayList<>();

        if(page>0) page = page-1;

        Pageable pageableRequest = PageRequest.of(page, limit);

        Page<ImageEntity> imagePage = imageRepository.findAll(pageableRequest);
        List<ImageEntity> images = imagePage.getContent();

        for (ImageEntity imageEntity : images) {
            ImageDto imageDto = new ImageDto();
            BeanUtils.copyProperties(imageEntity, imageDto);
            returnValue.add(imageDto);
        }

        return returnValue;
    }

    @Override
    public List<ImageDto> getImageByProductId(String imageName, int page, int limit) {
        List<ImageDto> returnValue = new ArrayList<>();

        if(page>0) page = page-1;

        Pageable pageableRequest = PageRequest.of(page, limit);
        Page<ImageEntity> imagePage = imageRepository.findByProductContainingOrderByIdAsc(imageName, pageableRequest);
        List<ImageEntity> images = imagePage.getContent();

        for (ImageEntity imageEntity : images) {
            ImageDto imageDto = new ImageDto();
            BeanUtils.copyProperties(imageEntity, imageDto);
            returnValue.add(imageDto);
        }

        return returnValue;
    }
}

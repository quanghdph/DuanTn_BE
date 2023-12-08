package com.fpt.duantn.services.impl;

import com.fpt.duantn.exceptions.ImageServiceException;
import com.fpt.duantn.io.entity.AddressEntity;
import com.fpt.duantn.io.entity.ImageEntity;
import com.fpt.duantn.io.repository.ImageRepository;
import com.fpt.duantn.services.ImageService;
import com.fpt.duantn.shrared.Utils;
import com.fpt.duantn.shrared.dto.CRUD.AddressDto;
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
import java.util.UUID;

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
    public ImageDto getImageById(Long imageId) {
        ImageDto returnValue = new ImageDto();
        ImageEntity imageEntity = imageRepository.findImageById(imageId);

        if (imageEntity == null)
            throw new ImageServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

        BeanUtils.copyProperties(imageEntity, returnValue);

        return returnValue;
    }

    @Override
    public <S extends ImageEntity> List<S> saveAll(Iterable<S> entities) {
        return imageRepository.saveAll(entities);
    }

    @Override
    public ImageDto updateImage(Long imageId, ImageDto image) {
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

    @Override
    public List<ImageDto> getImages(int page, int limit, String filter) {
        List<ImageDto> returnValue = new ArrayList<>();

        if(page>0) page = page-1;

        Pageable pageableRequest = PageRequest.of(page, limit);

        Page<ImageEntity> imagePage = imageRepository.filter(filter, pageableRequest);
        List<ImageEntity> images = imagePage.getContent();

        for (ImageEntity imageEntity : images) {
            ImageDto imageDto = new ImageDto();
            BeanUtils.copyProperties(imageEntity, imageDto);
            returnValue.add(imageDto);
        }

        return returnValue;
    }

    @Override
    public Long count(String filter) {

        Long total = imageRepository.count(filter);
        return total;
    }

    @Override
    public boolean existsById(Long id) {
        return imageRepository.existsById(id);
    }

}

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
        // Kiểm tra xem ImageCode đã tồn tại hay chưa
        if (imageRepository.findImageById(image.getId()) != null) {
            throw new ImageServiceException("Image with the same code already exists");
        }

        // Chuyển đổi ImageDto thành ImageEntity
        ModelMapper modelMapper = new ModelMapper();
        ImageEntity imageEntity = modelMapper.map(image, ImageEntity.class);

        // Tạo một mã ngẫu nhiên cho ImageCode (tùy theo yêu cầu)
//        String publicImageCode = utils.generateColorCode(8);
//        imageEntity.setId(Long.parseLong(publicImageCode));

        // Lưu trữ thông tin màu vào cơ sở dữ liệu
        ImageEntity storedImageDetails = imageRepository.save(imageEntity);

        // Chuyển đổi ImageEntity thành ImageDto
        ImageDto returnValue = modelMapper.map(storedImageDetails, ImageDto.class);

        return returnValue;
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
        ImageDto returnValue = new ImageDto();

        ImageEntity imageEntity = imageRepository.findImageById(imageCode);

        if (imageEntity == null)
            throw new ImageServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

        imageEntity.setImageName(image.getImageName());
        imageEntity.setUrl(image.getUrl());
        imageEntity.setProduct(image.getProduct());

        ImageEntity updatedImages = imageRepository.save(imageEntity);
        returnValue = new ModelMapper().map(updatedImages, ImageDto.class);

        return returnValue;
    }

    @Override
    public void deleteImage(Long imageCode) {
        ImageEntity imageEntity = imageRepository.findImageById(imageCode);

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
    public List<ImageDto> getImageByImageName(String imageName, int page, int limit) {
        List<ImageDto> returnValue = new ArrayList<>();

        if(page>0) page = page-1;

        Pageable pageableRequest = PageRequest.of(page, limit);
        Page<ImageEntity> imagePage = imageRepository.findByImageNameContainingOrderByIdAsc(imageName, pageableRequest);
        List<ImageEntity> images = imagePage.getContent();

        for (ImageEntity imageEntity : images) {
            ImageDto imageDto = new ImageDto();
            BeanUtils.copyProperties(imageEntity, imageDto);
            returnValue.add(imageDto);
        }

        return returnValue;
    }
}

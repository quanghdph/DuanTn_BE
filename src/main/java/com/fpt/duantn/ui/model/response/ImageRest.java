package com.fpt.duantn.ui.model.response;

import com.fpt.duantn.io.entity.ProductEntity;
import com.fpt.duantn.io.entity.ProductTypeEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ImageRest {

    private Long id;

    private String url;

    private ProductEntity product;

    private String imageName;

}

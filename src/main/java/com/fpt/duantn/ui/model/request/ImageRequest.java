package com.fpt.duantn.ui.model.request;

import com.fpt.duantn.io.entity.ProductEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ImageRequest {

    private String url;

    private ProductEntity product;

    private String imageName;

}

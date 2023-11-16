package com.fpt.duantn.ui.model.request;

import com.fpt.duantn.io.entity.ProductEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ImageRequest {

    private byte[] image;

    private ProductEntity product;

    private boolean type;

}

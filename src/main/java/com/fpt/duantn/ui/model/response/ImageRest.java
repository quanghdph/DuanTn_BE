package com.fpt.duantn.ui.model.response;

import com.fpt.duantn.io.entity.ProductEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ImageRest {

    private Long id;

    private byte[] image;

    private ProductEntity product;

    private boolean type;

}

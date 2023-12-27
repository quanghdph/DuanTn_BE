package com.fpt.duantn.ui.model.request;

import com.fpt.duantn.io.entity.ProductEntity;
import lombok.Getter;
import lombok.Setter;

import java.sql.Blob;

@Getter
@Setter
public class ImageRequest {

    private Blob image;

    private ProductEntity product;

    private boolean type;

}

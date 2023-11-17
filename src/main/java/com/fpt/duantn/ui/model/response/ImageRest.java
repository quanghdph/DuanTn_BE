package com.fpt.duantn.ui.model.response;

import com.fpt.duantn.io.entity.ProductEntity;
import lombok.Getter;
import lombok.Setter;

import java.sql.Blob;

@Getter
@Setter
public class ImageRest {

    private Long id;

    private Blob image;


    private ProductEntity product;

    private boolean type;

}

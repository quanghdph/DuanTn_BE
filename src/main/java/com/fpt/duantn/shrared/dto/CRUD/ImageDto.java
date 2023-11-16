package com.fpt.duantn.shrared.dto.CRUD;

import com.fpt.duantn.io.entity.ProductEntity;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class ImageDto implements Serializable {

    private static final long serialVersionUID = 6835192601898364280L;

    private long id;

    private byte[] image;

    private ProductEntity product;

    private boolean type;
}

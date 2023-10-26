package com.fpt.duantn.shrared.dto.CRUD;

import com.fpt.duantn.io.entity.ProductEntity;
import com.fpt.duantn.io.entity.ProductTypeEntity;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public class ImageDto implements Serializable {

    private static final long serialVersionUID = 6835192601898364280L;

    private long id;

    private String url;

    private ProductEntity product;

    private String imageName;
}

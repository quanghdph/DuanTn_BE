package com.fpt.duantn.ui.model.request;

import com.fpt.duantn.io.entity.ProductTypeEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class CategoryRequest {

    private String categoryName;
    private Integer status;
    private Date createDate;
    private Date updateDate;
    private ProductTypeEntity productType;

}

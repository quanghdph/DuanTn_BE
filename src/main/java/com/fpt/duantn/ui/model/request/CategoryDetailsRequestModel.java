package com.fpt.duantn.ui.model.request;

import com.fpt.duantn.io.entity.ProductTypeEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class CategoryDetailsRequestModel {

    private String categoryName;
    private String categoryCode;
    private Integer status;
    private Date createDate;
    private Date updateDate;
    private ProductTypeEntity productType;

}

package com.fpt.duantn.ui.model.request;

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

}

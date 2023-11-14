package com.fpt.duantn.ui.model.request;

import com.fpt.duantn.io.entity.EmployeeEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class CartRequest {

    private EmployeeEntity employee;

    private Date createDate;

    private Date updateDate;

    private Integer status;

}

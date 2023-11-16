package com.fpt.duantn.shrared.dto.CRUD;

import com.fpt.duantn.io.entity.CustomerEntity;
import com.fpt.duantn.io.entity.EmployeeEntity;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public class CartDto implements Serializable {

    private static final long serialVersionUID = 6835192601898364280L;

    private long id;

    private String cartCode;

    private EmployeeEntity employee;

    private Date createDate;

    private Date updateDate;

    private Integer status;

    private CustomerEntity customer;

}

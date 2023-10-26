package com.fpt.duantn.shrared.dto.CRUD;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public class AddressDto implements Serializable {

    private static final long serialVersionUID = 6835192601898364280L;

    private Long id;

//    private Customer customer;

    private String city;

    private String district;

    private String ward;

    private String addressCode;

    private String addressDetail;

    private Date createDate;

    private Date updateDate;

}

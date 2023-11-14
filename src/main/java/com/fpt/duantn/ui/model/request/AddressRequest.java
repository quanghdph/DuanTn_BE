package com.fpt.duantn.ui.model.request;

import com.fpt.duantn.io.entity.CustomerEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class AddressRequest {

    private Long id;

    private CustomerEntity customer;

    private String city;

    private String district;

    private String ward;

    private String addressDetail;

    private Date createDate;

    private Date updateDate;

}

package com.fpt.duantn.ui.model.request;

import com.fpt.duantn.io.entity.CustomerEntity;
import com.fpt.duantn.io.entity.EmployeeEntity;
import com.fpt.duantn.io.entity.ProductTypeEntity;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
public class BillDetailsRequestModel {

    private CustomerEntity customer;

    private EmployeeEntity employee;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date paymentDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date deliveryDate;

    private String address;

    private String customerName;

    private String phoneNumber;

    private BigDecimal transportFee;

    private BigDecimal totalAmount;

    private String note;

    private Integer status;

}

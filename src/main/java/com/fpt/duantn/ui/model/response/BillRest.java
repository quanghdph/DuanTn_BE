package com.fpt.duantn.ui.model.response;

import com.fpt.duantn.io.entity.CustomerEntity;
import com.fpt.duantn.io.entity.EmployeeEntity;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
public class BillRest {

    private Long id;

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

    private String phoneNumber;

    private BigDecimal transportFee;

    private String note;

    private Integer status;
    private Integer paymentType;
}

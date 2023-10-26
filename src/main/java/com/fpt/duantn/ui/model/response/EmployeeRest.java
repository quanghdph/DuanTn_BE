package com.fpt.duantn.ui.model.response;

import com.fpt.duantn.io.entity.ProductTypeEntity;
import jakarta.persistence.Lob;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class EmployeeRest {

    private String firstName;

    private String lastName;

    private int gender;

    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;

    private String email;

    private String phoneNumber;

    private String encryptedPassword;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDate;

    @Lob
    private byte[] image;

    private String employeeCode;

    private Integer status;


}

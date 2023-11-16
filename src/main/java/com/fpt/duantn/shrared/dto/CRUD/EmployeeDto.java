package com.fpt.duantn.shrared.dto.CRUD;

import jakarta.persistence.Lob;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public class EmployeeDto implements Serializable {

    private static final long serialVersionUID = 6835192601898364280L;

    private long id;

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

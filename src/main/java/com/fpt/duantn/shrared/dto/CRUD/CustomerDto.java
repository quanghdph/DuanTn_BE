package com.fpt.duantn.shrared.dto.CRUD;

import jakarta.persistence.Column;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public class CustomerDto implements Serializable {

    private static final long serialVersionUID = 6835192601898364280L;

    private Long id;
    private boolean emailVerificationStatus;

    private String firstName;

    private String lastName;

    private String email;

    private String emailVerificationToken;

    private String encryptedPassword;

    private int gender;

    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;

    private String phoneNumber;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDate;

    private String customerCode;
}

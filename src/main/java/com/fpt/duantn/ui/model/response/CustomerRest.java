package com.fpt.duantn.ui.model.response;

import com.fpt.duantn.io.entity.ProductTypeEntity;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class CustomerRest {

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

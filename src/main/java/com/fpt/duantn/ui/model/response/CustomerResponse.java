package com.fpt.duantn.ui.model.response;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data

@NoArgsConstructor
public class CustomerResponse {

    private long id;


    private boolean emailVerificationStatus;


    private String firstName;


    private String lastName;


    private String email;


    private String emailVerificationToken;


    private String encryptedPassword;


    private int gender;


    private Date dateOfBirth;


    private String phoneNumber;


    private Date createDate;


    private Date updateDate;

    private String customerCode;

    public CustomerResponse(long id,  String firstName, String lastName, String email,  int gender, Date dateOfBirth, String phoneNumber, Date createDate, Date updateDate, String customerCode) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.customerCode = customerCode;
    }
}

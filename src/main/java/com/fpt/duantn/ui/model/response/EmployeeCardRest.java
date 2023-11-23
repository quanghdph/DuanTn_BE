package com.fpt.duantn.ui.model.response;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class EmployeeCardRest {

    private Long id;

    private String firstName;

    private String lastName;

    private int gender;

    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;

    private String email;

    private String phoneNumber;

    private Integer status;

}

package com.fpt.duantn.io.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="bill")
public class BillEntity implements Serializable {

    private static final long serialVersionUID = 5313493413859894403L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private CustomerEntity customer;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private EmployeeEntity employee;

    @Column(name = "create_date")
    @Temporal(TemporalType.DATE)
    @CreatedDate
    private Date createDate;

    @Column(name = "update_date")
    @Temporal(TemporalType.DATE)
    @UpdateTimestamp
    private Date updateDate;

    @Column(name = "payment_date")
    @Temporal(TemporalType.DATE)
    private Date paymentDate;

    @Column(name = "delivery_date")
    @Temporal(TemporalType.DATE)
    private Date deliveryDate;

    @Column(name = "address", length = 255, nullable = false)
    private String address;

//    @Pattern(regexp = "^(0|\\+\\d{2})\\d{9}$")
    @Column(name = "phone_number", length = 20)
    private String phoneNumber;

    @Column(name = "transport_fee", precision = 10, scale = 0)
    private BigDecimal transportFee;

    @Column(name = "note", length = 255)
    private String note;

    @Column(name = "payment_type")
    private Integer paymentType;

    @Column(name = "payment_amount")
    private BigDecimal paymentAmount;

    @Column(name = "shipe_fee")
    private BigDecimal shipeFee;


    @Column(name = "status")
    private Integer status;

}

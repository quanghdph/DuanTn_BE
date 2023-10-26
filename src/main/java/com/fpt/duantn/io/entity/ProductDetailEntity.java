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
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="product_detail")
public class ProductDetailEntity implements Serializable {

    private static final long serialVersionUID = 5313493413859894403L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private ProductEntity product;

    @ManyToOne
    @JoinColumn(name = "color_id")
    private ColorEntity color;

    @ManyToOne
    @JoinColumn(name = "size_id")
    private SizeEntity size;

    @ManyToOne
    @JoinColumn(name = "design_id")
    private DesignEntity design;

    @ManyToOne
    @JoinColumn(name = "material_id")
    private MaterialEntity material;

    @ManyToOne
    @JoinColumn(name = "pattern_id")
    private PatternEntity pattern;

    @ManyToOne
    @JoinColumn(name = "collar_id")
    private CollarEntity collar;

    @ManyToOne
    @JoinColumn(name = "sleeve_id")
    private SleeveEntity sleeve;

    @ManyToOne
    @JoinColumn(name = "waistband_id")
    private WaistbandEntity waistband;

    @Column(name = "default_price", precision = 10, scale = 0)
    private BigDecimal defaultPrice;

    @Column(name = "price", precision = 10, scale = 0)
    private BigDecimal price;

    @Column(name = "amount")
    private Integer amount;

    @Column(name = "create_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;

    @Column(name = "update_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDate;

    @Column(name = "status")
    private Integer status;
}

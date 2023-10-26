package com.fpt.duantn.shrared.dto.CRUD;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;


@Getter
@Setter
public class ProductDetailDto implements Serializable {

    private static final long serialVersionUID = 6835192601898364280L;

    private Long id;

    private BigDecimal defaultPrice;

    private BigDecimal price;

    private Integer amount;

    private Integer status;

    private ProductDto productEntity;

    private ColorDto colorEntity;


}

package com.fpt.duantn.ui.model.request;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProductDetailRequest {


    private Long colorId;

    private Long sizeId;

    private BigDecimal price;

    private Integer quantity;

    private Integer status;

}

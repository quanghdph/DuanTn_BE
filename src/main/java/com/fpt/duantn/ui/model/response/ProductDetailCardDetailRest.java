package com.fpt.duantn.ui.model.response;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class ProductDetailCardDetailRest {

    private Long id;

    private BigDecimal defaultPrice;
    private BigDecimal price;
    private Integer quantity;
    private Integer status;
    private String productDetailCode;


    private List<ProductDetailCardDetailsRest> productDetailCardDetailsRest;

}

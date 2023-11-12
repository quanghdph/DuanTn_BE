package com.fpt.duantn.ui.model.response;

import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter

public class ProductDetailCardDetailsRest extends RepresentationModel<ProductDetailCardDetailsRest> {

    private Long id;

    private BigDecimal defaultPrice;

    private BigDecimal price;

    private Integer amount;

    private Integer status;

    private String productDetailCode;

    private String colorCode;

    private String colorName;

}

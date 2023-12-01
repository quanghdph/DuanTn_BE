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

    private BigDecimal price;
    private Integer quantity;
    private Integer status;
    private String colorCode;
    private String colorName;
    private String productDetailCode;

}

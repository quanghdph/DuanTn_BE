package com.fpt.duantn.ui.model.response;



import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProductCardRest {

    private Long id;

    private String productCode;
    private String productName;
    private String mainImage;
    private Integer status;

    private List<ProductDetailCardRest> productDetails;
}

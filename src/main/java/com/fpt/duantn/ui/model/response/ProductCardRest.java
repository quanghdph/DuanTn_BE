package com.fpt.duantn.ui.model.response;



import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProductCardRest {

    private String productCode;
    private String productName;
    private String mainImage;

    private List<ProductDetailCardRest> productDetails;
}

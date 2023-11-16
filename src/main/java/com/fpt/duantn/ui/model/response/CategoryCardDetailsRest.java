package com.fpt.duantn.ui.model.response;

import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

@Getter
@Setter
public class CategoryCardDetailsRest extends RepresentationModel<ProductDetailCardDetailsRest> {

    private Long id;
    private String categoryName;
    private String categoryCode;
    private Integer status;
    private String productTypeCode;
    private String productTypeName;

}

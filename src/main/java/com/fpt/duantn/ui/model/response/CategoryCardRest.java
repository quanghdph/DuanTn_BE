package com.fpt.duantn.ui.model.response;

import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

@Getter
@Setter
public class CategoryCardRest extends RepresentationModel<CategoryCardRest> {
    private Long id;

    private String productTypeCode;
    private String productTypeName;
    private String categoryCode;
    private String categoryName;


}

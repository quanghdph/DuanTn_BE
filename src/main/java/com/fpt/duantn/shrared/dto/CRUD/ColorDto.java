package com.fpt.duantn.shrared.dto.CRUD;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;


@Getter
@Setter
public class ColorDto implements Serializable {

    private static final long serialVersionUID = 6835192601898364280L;

    private Long id;
    private String colorName;
    private String colorCode;

    private List<ProductDetailDto> productDetails;


}

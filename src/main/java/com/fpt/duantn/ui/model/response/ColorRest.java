package com.fpt.duantn.ui.model.response;



import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ColorRest {

    private String colorName;
    private String colorCode;

    private List<ProductDetailCardRest> colors;

}

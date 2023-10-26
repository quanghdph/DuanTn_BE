package com.fpt.duantn.shrared.dto.CRUD;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


@Getter
@Setter
public class MaterialDto implements Serializable {

    private static final long serialVersionUID = 6835192601898364280L;

    private Long id;
    private String materialName;
    private String materialCode;


}

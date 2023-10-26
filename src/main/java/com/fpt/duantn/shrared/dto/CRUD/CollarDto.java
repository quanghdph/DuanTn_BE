package com.fpt.duantn.shrared.dto.CRUD;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class CollarDto implements Serializable {

    private static final long serialVersionUID = 6835192601898364280L;

    private Long id;
    private String collarName;
    private String collarCode;
}

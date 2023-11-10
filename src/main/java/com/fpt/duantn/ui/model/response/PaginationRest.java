package com.fpt.duantn.ui.model.response;

import lombok.Data;

import java.util.List;

@Data
public class PaginationRest {
    private Long total;
    private List<ProductRest> list;
}

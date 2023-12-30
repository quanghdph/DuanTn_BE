package com.fpt.duantn.ui.model.request;

import lombok.*;

import java.util.UUID;
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SellOfProductRequest {
    @NonNull
    private Long id;
    @NonNull
    private Integer quantity;
}

package com.fpt.duantn.ui.model.request;

import com.fpt.duantn.io.entity.BrandEntity;
import com.fpt.duantn.io.entity.CategoryEntity;
import com.fpt.duantn.io.entity.MaterialEntity;
import com.fpt.duantn.io.entity.SizeEntity;
import com.fpt.duantn.io.entity.WaistbandEntity;
import jakarta.persistence.Lob;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductRequest {
    private String code;
    private String productName;

    private CategoryEntity category;

    private BrandEntity brand;

    private MultipartFile mainImage;

    @Lob
    private String description;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDate;

    private Integer status;

    private WaistbandEntity waistband;

    private MaterialEntity material;

    private List<ProductDetailRequest> productDetails;

    MultipartFile[] imgs;

}

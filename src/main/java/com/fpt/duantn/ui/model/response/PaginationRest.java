package com.fpt.duantn.ui.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fpt.duantn.io.entity.ImageEntity;
import lombok.Data;

import java.util.Collections;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PaginationRest {
    private Long total;
    private List<ProductRest> list;
    private List<CategoryRest> listCategories;
    private List<EmployeeRest> listEmployees;
    private List<AddressRest> listAddresses;
    private List<BillDetailRest> listBillDetails;
    private List<BillRest> listBill;
    private List<BrandRest> listBrand;
    private List<CartDetailRest> listCartDetail;
    private List<CustomerRest> listCustomer;
    private List<ProductDetailRest> listProductDetail;
    private List<ImageRest> listImage;
    private List<ColorRest> listColors;
    private List<MaterialRest> listMaterials;
    private List<SizeRest> listSizes;


}

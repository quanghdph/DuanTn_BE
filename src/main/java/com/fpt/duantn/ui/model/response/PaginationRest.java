package com.fpt.duantn.ui.model.response;

import lombok.Data;

import java.util.Collections;
import java.util.List;

@Data
public class PaginationRest {
    private Long total;
    private List<ProductRest> list;
    private List<CategoryRest> listCategories;
    private List<EmployeeRest> listEmployees;
    private List<AddressRest> listAddresses;
    private List<BillDetailRest> listBillDetails;
    private List<BillRest> listBill;
    private List<BrandRest> listBrand;
    private List<CartRest> listCart;
    private List<CartDetailRest> listCartDetail;
    private List<CustomerRest> listCustomer;
    private List<ProductDetailRest> listProductDetail;
    private List<PromotionRest> listPromotion;
    private List<PromotionDetailRest> listPromotionDetail;



}

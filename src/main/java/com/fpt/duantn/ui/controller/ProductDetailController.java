package com.fpt.duantn.ui.controller;

import com.fpt.duantn.io.entity.*;
import com.fpt.duantn.services.ProductDetailService;
import com.fpt.duantn.shrared.dto.CRUD.ProductDetailDto;
import com.fpt.duantn.shrared.dto.CRUD.ProductDto;
import com.fpt.duantn.ui.model.request.ProductDetailRequest;

import com.fpt.duantn.ui.model.response.PaginationRest;
import com.fpt.duantn.ui.model.response.ProductDetailRest;
import com.fpt.duantn.ui.model.response.OperationStatusModel;
import com.fpt.duantn.ui.model.response.RequestOperationStatus;
import com.fpt.duantn.util.FormErrorUtil;
import jakarta.servlet.http.HttpServletRequest;

import com.fpt.duantn.ui.model.response.*;

import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin(origins = {"http://localhost:4201","http://localhost:4200"})
@RestController
@RequestMapping("/product-detail")
public class ProductDetailController {

    @Autowired
    ProductDetailService productDetailService;

    @GetMapping(path = "/{id}")
    public ProductDetailRest getProductDetail(@PathVariable Long id) {
        ProductDetailRest returnValue = new ProductDetailRest();

        ProductDetailDto productDetailDto = productDetailService.getProductDetailById(id);
        ModelMapper modelMapper = new ModelMapper();
        returnValue = modelMapper.map(productDetailDto, ProductDetailRest.class);

        return returnValue;
    }

    @PostMapping()
    public ProductDetailRest createProductDetail(@RequestBody ProductDetailRequest productDetailDetails) throws Exception {
        ProductDetailRest returnValue = new ProductDetailRest();

        ModelMapper modelMapper = new ModelMapper();
        ProductDetailDto productDetailDto = modelMapper.map(productDetailDetails, ProductDetailDto.class);
    return  returnValue;
    }


    @GetMapping()
    public PaginationRest getProductDetail(@RequestParam(value = "page", defaultValue = "0") int page,
                                      @RequestParam(value = "limit", defaultValue = "5") int limit,
                                      @RequestParam(value = "filter", defaultValue = "") String filter,
                                      @RequestParam(value = "idProduct",required = true)Long idProduct) {
        List<ProductDetailRest> returnValue = new ArrayList<>();

        List<ProductDetailDto> productsDetails = productDetailService.getProductsDetail(idProduct,page, limit, filter);

        for (ProductDetailDto productDetailDto : productsDetails) {
            ProductDetailRest productModel = new ProductDetailRest();
            BeanUtils.copyProperties(productDetailDto, productModel);
            returnValue.add(productModel);
        }
        PaginationRest paginationRest = new PaginationRest();
        paginationRest.setListProductDetail(returnValue);
        paginationRest.setTotal(productDetailService.count(filter));

        return paginationRest;
    }
//    @PostMapping()
//    public ProductDetailRest createProductDetail(@RequestBody ProductDetailRequest productDetailDetails) throws Exception {
//        ProductDetailRest returnValue = new ProductDetailRest();
//
//        ModelMapper modelMapper = new ModelMapper();
//
//        ProductDetailEntity productDetailEntity = new ProductDetailEntity();
//        productDetailEntity.setProduct(productDetailDetails.ge);
//        ColorEntity colorEntity = new ColorEntity();
//        colorEntity.setId(item.getColorId());
//        productDetailEntity.setColor(colorEntity);
//        SizeEntity sizeEntity = new SizeEntity();
//        sizeEntity.setId(item.getSizeId());
//        productDetailEntity.setSize(sizeEntity);
//        productDetailEntity.setQuantity(item.getQuantity());
//        productDetailEntity.setPrice(item.getPrice());
//        productDetailEntity.setStatus(item.getStatus());
//
//
//
//
//        ProductDetailDto createdUser = productDetailService.createProductDetail(productDetailDto);
//        returnValue = modelMapper.map(createdUser, ProductDetailRest.class);
//
//        return returnValue;
//    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping( value = "/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @ModelAttribute ProductDetailEntity productDetail , BindingResult bindingResult) {
        if (productDetail.getColor()==null){
            bindingResult.rejectValue("color.id","required","Vui lòng nhập trường này");
        }else {
            if (productDetail.getColor().getId()==null){
                bindingResult.rejectValue("color.id","required","Vui lòng nhập trường này");
            };
        }
        if (productDetail.getSize()==null){
            bindingResult.rejectValue("size.id","required","Vui lòng nhập trường này");
        }else {
            if (productDetail.getSize().getId()==null){
                bindingResult.rejectValue("size.id","required","Vui lòng nhập trường này");
            };
        }

        if (bindingResult.hasErrors()){
            Map errors = FormErrorUtil.changeToMapError(bindingResult);
            return ResponseEntity.badRequest().body(errors);
        }

        if (productDetailService.existsById(id)){
            Optional<ProductDetailEntity> optionalProductDetail = productDetailService.findById(id);
            ProductDetailEntity productDetailDB = optionalProductDetail.get();
            if (!(productDetailDB.getColor().getId().equals(productDetail.getColor().getId())&&productDetailDB.getSize().getId().equals(productDetail.getSize().getId()))){
                if (productDetailService.existsByProductIdAndColorIdAndSizeId(productDetail.getProduct().getId(),productDetail.getColor().getId(),productDetail.getSize().getId())){
                    return ResponseEntity.badRequest().body("Biến thể này đã tồn tại");
                }
            }
            productDetail.setId(id);
            ProductDetailEntity productDetailSaved = productDetailService.save(productDetail);
            return ResponseEntity.ok(productDetailSaved);

        }else {
            return ResponseEntity.badRequest().body("Không tồn tại");
        }


    }

    @DeleteMapping(path = "/{id}")
    public OperationStatusModel deleteProductDetail(@PathVariable Long id) {
        OperationStatusModel returnValue = new OperationStatusModel();
        returnValue.setOperationName(RequestOperationName.DELETE.name());

        try {
            productDetailService.deleteProductDetail(id);
            returnValue.setOperationResult(RequestOperationStatus.SUCCESS.name());
            returnValue.setOperationMessage("Xoa Thanh Cong.");
        }catch (DataIntegrityViolationException exception){
            returnValue.setOperationResult(RequestOperationStatus.ERROR.name());
            returnValue.setOperationMessage("Lỗi khi xóa sản phẩm: Sản phẩm có tham chiếu đến khoá ngoại.");
        }catch (Exception e){
            returnValue.setOperationResult(RequestOperationStatus.ERROR.name());
            returnValue.setOperationMessage("Lỗi khi xóa sản phẩm: " + e.getMessage());
        }return returnValue;
    }



//    @GetMapping("/search")
//    public List<ProductDetailRest> searchProductDetails(@RequestParam(value = "productDetailCode") String productDetailCode,
//                                              @RequestParam(value = "page", defaultValue = "0") int page,
//                                              @RequestParam(value = "limit", defaultValue = "2") int limit) {
//        List<ProductDetailRest> returnValue = new ArrayList<>();
//
//        List<ProductDetailDto> productDetails = productDetailService.getProductByProductDetailCode(productDetailCode, page, limit);
//
//        for (ProductDetailDto productDetailDto : productDetails) {
//            ProductDetailRest productDetailModel = new ProductDetailRest();
//            BeanUtils.copyProperties(productDetailDto, productDetailModel);
//            returnValue.add(productDetailModel);
//        }
//
//        return returnValue;
//    }

//    @GetMapping()
//    public PaginationRest getProductDetails(@RequestParam(value = "page", defaultValue = "0") int page,
//                                            @RequestParam(value = "limit", defaultValue = "5") int limit,
//                                            @RequestParam(value = "filter", defaultValue = "") String filter) {
//        List<ProductDetailRest> returnValue = new ArrayList<>();
//
//        List<ProductDetailDto> productDetails = productDetailService.getProductsDetail(page, limit, filter);
//
//        for (ProductDetailDto productDetailDto : productDetails) {
//            ProductDetailRest productDetailModel = new ProductDetailRest();
//            BeanUtils.copyProperties(productDetailDto, productDetailModel);
//            returnValue.add(productDetailModel);
//        }
//        PaginationRest paginationRest = new PaginationRest();
//        paginationRest.setListProductDetail(returnValue);
//        paginationRest.setTotal(productDetailService.count(filter));
//
//        return paginationRest;
//    }

    @GetMapping("/{productId}/{colorId}/{sizeId}")
    public ResponseEntity<ProductDetailDto> getProductDetailByColorAndSize(
            @PathVariable Long productId, @PathVariable Long colorId, @PathVariable Long sizeId) {
        ProductDetailDto productDetailDto = productDetailService.getProductDetailsByProductAndColorAndSize(productId,colorId, sizeId);
        if (productDetailDto != null) {
            return new ResponseEntity<>(productDetailDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}

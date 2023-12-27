package com.fpt.duantn.ui.controller;

import com.fpt.duantn.io.entity.ImageEntity;
import com.fpt.duantn.io.entity.ProductEntity;
import com.fpt.duantn.services.ImageService;
import com.fpt.duantn.services.ProductService;
import com.fpt.duantn.shrared.dto.CRUD.ProductDto;
import com.fpt.duantn.ui.model.request.ProductRequest;
import com.fpt.duantn.ui.model.response.PaginationRest;
import com.fpt.duantn.ui.model.response.ProductRest;
import com.fpt.duantn.ui.model.response.OperationStatusModel;
import com.fpt.duantn.ui.model.response.RequestOperationStatus;
import com.fpt.duantn.util.FileImgUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = {"http://localhost:4201","http://localhost:4200"})
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;
    @Autowired
    FileImgUtil fileImgUtil;
    @Autowired
    ImageService imageService;

    @GetMapping(path = "/{id}")
    public ProductRest getProduct(@PathVariable Long id) {
        ProductRest returnValue = new ProductRest();

        ProductDto productDto = productService.getProductById(id);
        ModelMapper modelMapper = new ModelMapper();
        returnValue = modelMapper.map(productDto, ProductRest.class);

        return returnValue;
    }
    @Autowired
    PasswordEncoder passwordEncoder;

    @GetMapping(path = "/passWord")
    public String SignPass (){
        return passwordEncoder.encode("1");
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("hasAnyRole('ADMIN', 'EMPLOYEE')")
    public ProductRest createProduct(@RequestBody ProductRequest productDetails, @RequestPart(value = "images",required = false) MultipartFile  []multipartFiles) throws Exception {
        ProductRest returnValue = new ProductRest();

        ModelMapper modelMapper = new ModelMapper();
        ProductDto productDto = modelMapper.map(productDetails, ProductDto.class);

        productDto.setCategory(productDetails.getCategory());
        productDto.setBrand(productDetails.getBrand());
        productDto.setMaterial(productDetails.getMaterial());
        productDto.setWaistband(productDetails.getWaistband());
        if (multipartFiles != null ){
            if (multipartFiles.length>0){
                productDto.setMainImage(new SerialBlob(multipartFiles[0].getBytes()));
            }
        }

        ProductDto productDto1 = productService.createProduct(productDto);
        ProductEntity product = new ProductEntity();
        product.setId(productDto1.getId());
        List<ImageEntity> imagesList= new ArrayList<>();
        boolean imgSelect = true;
      if (multipartFiles != null){
          for (MultipartFile multipartFile : multipartFiles){
              try {
                  Blob blob =fileImgUtil.convertMultipartFileToBlob(multipartFile);
                  if (blob!=null){
                      ImageEntity image = new ImageEntity();
                      image.setProduct(product);
                      image.setImage(blob);
                      if (imgSelect){
                          image.setType(true);
                          imgSelect=false;
                      }else {
                          image.setType(true);
                      }
                      imagesList.add(image);
                  }
              } catch (IOException | SQLException e) {
                  System.out.println("Không đọc ghi được ảnh (kiểm tra lại sản phảm vừa tạo");
              }
          }
      }
        imageService.saveAll(imagesList);
        returnValue = modelMapper.map(productDto1, ProductRest.class);

        return returnValue;
    }

    @PutMapping(path = "/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'EMPLOYEE')")
    public ProductRest updateProduct(@PathVariable Long id, @RequestBody ProductRequest productDetails) {
        ProductRest returnValue = new ProductRest();

        ProductDto productDto = new ProductDto();
        productDto = new ModelMapper().map(productDetails, ProductDto.class);

        productDto.setCategory(productDetails.getCategory());
        productDto.setBrand(productDetails.getBrand());
        productDto.setMaterial(productDetails.getMaterial());
        productDto.setWaistband(productDetails.getWaistband());

        try {
            ProductDto updateProduct = productService.updateProduct(id, productDto);
            returnValue = new ModelMapper().map(updateProduct, ProductRest.class);
        }catch (Exception e){
            System.out.println(e);
        }
        return returnValue;
    }

    @DeleteMapping(path = "/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'EMPLOYEE')")
    public OperationStatusModel deleteProduct(@PathVariable Long id) {
        OperationStatusModel returnValue = new OperationStatusModel();
        returnValue.setOperationName(RequestOperationName.DELETE.name());
            try {
                productService.deleteProduct(id);
                returnValue.setOperationResult(RequestOperationStatus.SUCCESS.name());
                returnValue.setOperationMessage("Xoa Thanh Cong.");
            }catch (DataIntegrityViolationException exception){
                returnValue.setOperationResult(RequestOperationStatus.ERROR.name());
                returnValue.setOperationMessage("Lỗi khi xóa San Pham: San Pham có tham chiếu đến khoá ngoại.");
            }catch (Exception e){
                returnValue.setOperationResult(RequestOperationStatus.ERROR.name());
                returnValue.setOperationMessage("Lỗi khi xóa San Pham: " + e.getMessage());
            }
        return returnValue;
    }

    @GetMapping("/search")
    public List<ProductRest> searchProducts(@RequestParam(value = "productName") String productName,
                                              @RequestParam(value = "page", defaultValue = "0") int page,
                                              @RequestParam(value = "limit", defaultValue = "5") int limit) {
        List<ProductRest> returnValue = new ArrayList<>();

        List<ProductDto> products = productService.getProductByProductName(productName, page, limit);

        for (ProductDto productDto : products) {
            ProductRest productModel = new ProductRest();
            BeanUtils.copyProperties(productDto, productModel);
            returnValue.add(productModel);
        }

        return returnValue;
    }


    @GetMapping()
    public PaginationRest getProducts(@RequestParam(value = "page", defaultValue = "0") int page,
                                      @RequestParam(value = "limit", defaultValue = "5") int limit,
                                      @RequestParam(value = "filter", defaultValue = "") String filter) {
        List<ProductRest> returnValue = new ArrayList<>();

        List<ProductDto> products = productService.getProducts(page, limit, filter);

        for (ProductDto productDto : products) {
            ProductRest productModel = new ProductRest();
            BeanUtils.copyProperties(productDto, productModel);
            returnValue.add(productModel);
        }
        PaginationRest paginationRest = new PaginationRest();
        paginationRest.setList(returnValue);
        paginationRest.setTotal(productService.count(filter));

        return paginationRest;
    }

    @GetMapping("/{id}/image")
    public ResponseEntity<?> getImage(@PathVariable Long id) {
        Optional<ImageEntity> image = imageService.findById(id);
        if (!image.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Không tồn tại");
        }
        byte[] imageBytes = new byte[0];
        try {
            imageBytes = fileImgUtil.convertBlobToByteArray(image.get().getImage());
        } catch (SQLException | IOException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Lỗi đọc ảnh");
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);
        return new ResponseEntity<>(imageBytes, headers, HttpStatus.OK);
    }

    @GetMapping("/{id}/image-main")
    public ResponseEntity<?> getImageMain(@PathVariable Long id) {
        Optional<ProductEntity> productEntity = productService.findById(id);
        if (!productEntity.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Không tồn tại");
        }
        byte[] imageBytes = new byte[0];
        try {
            imageBytes = fileImgUtil.convertBlobToByteArray(productEntity.get().getMainImage());
        } catch (SQLException | IOException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Lỗi đọc ảnh");
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);
        return new ResponseEntity<>(imageBytes, headers, HttpStatus.OK);
    }
    @GetMapping("/imageID/{id}")
    public ResponseEntity<?> getProductIDImage(@PathVariable Long id) {
        List<Long> ids = imageService.findIdByProductId(id);
        return ResponseEntity.ok(ids);
    }

}

package com.fpt.duantn.ui.controller;

import com.fpt.duantn.io.entity.ImageEntity;
import com.fpt.duantn.io.entity.*;

import com.fpt.duantn.services.ImageService;
import com.fpt.duantn.services.ProductDetailService;
import com.fpt.duantn.services.ProductService;
import com.fpt.duantn.shrared.dto.CRUD.ProductDto;
import com.fpt.duantn.ui.model.request.ProductDetailRequest;
import com.fpt.duantn.ui.model.request.ProductRequest;
import com.fpt.duantn.ui.model.response.PaginationRest;
import com.fpt.duantn.ui.model.response.ProductRest;
import com.fpt.duantn.ui.model.response.OperationStatusModel;
import com.fpt.duantn.ui.model.response.RequestOperationStatus;
import com.fpt.duantn.util.FileImgUtil;
import com.fpt.duantn.util.FormErrorUtil;
import jakarta.validation.Valid;
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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.*;

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
    @Autowired
    ProductDetailService productDetailService;

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
    public String SignPass() {
        return passwordEncoder.encode("1");
    }

    @PostMapping()
    @PreAuthorize("hasAnyRole('ADMIN', 'EMPLOYEE')")
    public ProductRest createProduct(@Valid @ModelAttribute ProductRequest productDetails, @RequestPart(value = "images", required = false) MultipartFile[] multipartFiles) throws Exception {
        ProductRest returnValue = new ProductRest();
        System.out.println(multipartFiles.length);
        ModelMapper modelMapper = new ModelMapper();
        ProductDto productDto = modelMapper.map(productDetails, ProductDto.class);

        productDto.setCategory(productDetails.getCategory());
        productDto.setBrand(productDetails.getBrand());
        productDto.setMaterial(productDetails.getMaterial());
        productDto.setWaistband(productDetails.getWaistband());

        if (multipartFiles != null) {
            if (multipartFiles.length > 0) {
                productDto.setMainImage(new SerialBlob(multipartFiles[0].getBytes()));
            }
        }

        ProductDto productDto1 = productService.createProduct(productDto);
        ProductEntity product = new ProductEntity();
        product.setId(productDto1.getId());
        List<ImageEntity> imagesList = new ArrayList<>();
        boolean imgSelect = true;

        if (multipartFiles != null) {
            for (MultipartFile multipartFile : multipartFiles) {
                try {
                    Blob blob = fileImgUtil.convertMultipartFileToBlob(multipartFile);
                    if (blob != null) {
                        ImageEntity image = new ImageEntity();
                        image.setProduct(product);
                        image.setImage(blob);
                        if (imgSelect) {
                            image.setType(true);
                            imgSelect = false;
                        } else {
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


    @PostMapping(value = "/add")

    public ResponseEntity<?> addProduct(@Valid @ModelAttribute ProductRequest productRequest, BindingResult bindingResult) {


        if (bindingResult.hasErrors()) {
            Map errors = FormErrorUtil.changeToMapError(bindingResult);
            return ResponseEntity.badRequest().body(errors);
        }

        // Kiểm tra mã trùng
        ProductEntity existingProduct = productService.findByProductCode(productRequest.getCode());
        if (existingProduct != null) {
            Map<String, String> errors = new HashMap<>();
            errors.put("code", "Mã đã tồn tại");
            return ResponseEntity.badRequest().body(errors);
        }


//        Conver dữ liệu sang domain
        ProductEntity product = new ProductEntity();
        product.setProductCode(new Random().nextInt(100)+"");
        product.setProductName(productRequest.getProductName());
        product.setStatus(productRequest.getStatus());
        product.setBrand(productRequest.getBrand());
        product.setCategory(productRequest.getCategory());
        product.setDescription(productRequest.getDescription());
//        try {
////            product.setMainImage(new SerialBlob(mainImage.getBytes()));
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        ProductEntity productSaved = productService.save(product);

        try {

            List<ProductDetailEntity> productDetailEntities = new ArrayList<>();
            List<ProductDetailRequest> productDetails = productRequest.getProductDetails();
            productDetails.stream().forEach(item -> {
                ProductDetailEntity productDetailEntity = new ProductDetailEntity();
                productDetailEntity.setProduct(product);
                ColorEntity colorEntity = new ColorEntity();
                colorEntity.setId(item.getColorId());
                productDetailEntity.setColor(colorEntity);
                SizeEntity sizeEntity = new SizeEntity();
                sizeEntity.setId(item.getSizeId());
                productDetailEntity.setSize(sizeEntity);
                productDetailEntity.setQuantity(item.getQuantity());
                productDetailEntity.setPrice(item.getPrice());
                productDetailEntity.setStatus(item.getStatus());
                productDetailEntities.add(productDetailEntity);
            });

            productDetailService.saveAll(productDetailEntities);

            //        Thêm ảnh
            List<ImageEntity> imagesList = new ArrayList<>();
            boolean imgSelect = true;
            if (productRequest.getImgs()!=null){
                for (MultipartFile multipartFile : productRequest.getImgs()) {
                    try {
                        Blob blob = fileImgUtil.convertMultipartFileToBlob(multipartFile);

                        if (blob != null) {
                            ImageEntity image = new ImageEntity();
                            image.setProduct(productSaved);
                            image.setImage(blob);
                            if (imgSelect) {
                                image.setType(true);
                                imgSelect = false;
                            } else {
                                image.setType(true);
                            }
                            imagesList.add(image);
                        }


                    } catch (IOException | SQLException e) {
                        return ResponseEntity.badRequest().body("Không đọc ghi được ảnh (kiểm tra lại sản phảm vừa tạo)");
                    }
                }
            }
            imageService.saveAll(imagesList);

        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.badRequest().body("Có lỗi sảy ra (kiểm tra lại sản phảm vừa tạo)");
        }
        return ResponseEntity.ok(productSaved.getId());
    }

    @PutMapping(path = "/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'EMPLOYEE')")
    public ProductRest updateProduct(@PathVariable Long id, @RequestBody ProductRequest productRequest) {
        ProductRest returnValue = new ProductRest();

        ProductEntity productEntity = productService.findById(id).orElse(null);
        if (productEntity == null) {

        } else {
            productEntity.setCategory(productRequest.getCategory());
            productEntity.setBrand(productRequest.getBrand());
            productEntity.setMaterial(productRequest.getMaterial());
            productEntity.setWaistband(productRequest.getWaistband());
            productEntity.setStatus(productRequest.getStatus());
            productEntity.setProductName(productRequest.getProductName());
            productEntity.setDescription(productRequest.getDescription());
            productEntity.setCreateDate(productRequest.getCreateDate());
            productEntity.setUpdateDate(productRequest.getUpdateDate());
        }

        try {
            ProductEntity updateProduct = productService.save(productEntity);
            returnValue = new ModelMapper().map(updateProduct, ProductRest.class);
        } catch (Exception e) {
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
        } catch (DataIntegrityViolationException exception) {
            returnValue.setOperationResult(RequestOperationStatus.ERROR.name());
            returnValue.setOperationMessage("Lỗi khi xóa San Pham: San Pham có tham chiếu đến khoá ngoại.");
        } catch (Exception e) {
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

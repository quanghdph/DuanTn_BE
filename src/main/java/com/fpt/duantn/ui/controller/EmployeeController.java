package com.fpt.duantn.ui.controller;

import com.fpt.duantn.io.entity.EmployeeEntity;
import com.fpt.duantn.io.entity.ImageEntity;

import com.fpt.duantn.services.EmployeeService;
import com.fpt.duantn.services.ImageService;
import com.fpt.duantn.shrared.dto.CRUD.EmployeeDto;
import com.fpt.duantn.ui.model.request.EmployeeRequest;
import com.fpt.duantn.ui.model.response.*;
import com.fpt.duantn.util.FileImgUtil;
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
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @Autowired
    ImageService imageService;

    @Autowired
    FileImgUtil fileImgUtil;
    @GetMapping(path = "/{id}")
    public EmployeeRest getEmployee(@PathVariable Long id) {
        EmployeeRest returnValue = new EmployeeRest();

        EmployeeDto employeeDto = employeeService.getEmployeeById(id);
        ModelMapper modelMapper = new ModelMapper();
        returnValue = modelMapper.map(employeeDto, EmployeeRest.class);

        return returnValue;
    }

    @PostMapping()
    @PreAuthorize("hasAnyRole('ADMIN')")
    public EmployeeRest createEmployee(@Valid @RequestBody EmployeeRequest employeeDetails, @RequestPart(value = "images",required = false) MultipartFile []multipartFiles) throws Exception {
        EmployeeRest returnValue = new EmployeeRest();

        ModelMapper modelMapper = new ModelMapper();
        EmployeeDto employeeDto = modelMapper.map(employeeDetails, EmployeeDto.class);

        if (multipartFiles  != null){
            if (multipartFiles.length>0){
                employeeDto.setImage(new SerialBlob(multipartFiles[0].getBytes()));
            }
        }

        EmployeeDto employeeDto1 = employeeService.createEmployee(employeeDto);
        EmployeeEntity employeeEntity = new EmployeeEntity();
        employeeEntity.setId(employeeDto1.getId());
        List<ImageEntity> imageEntityList = new ArrayList<>();
        boolean imgSelect = true;

        if (multipartFiles != null){
            for (MultipartFile multipartFile : multipartFiles){
                try {
                    Blob blob =fileImgUtil.convertMultipartFileToBlob(multipartFile);
                    if (blob!=null){
                        ImageEntity imageEntity = new ImageEntity();
                        imageEntity.setImage(blob);

                        if (imgSelect){
                            imageEntity.setType(true);
                            imgSelect=false;
                        }else {
                            imageEntity.setType(true);
                        }
                        imageEntityList.add(imageEntity);
                    }
                } catch (IOException | SQLException e) {
                    System.out.println("Không đọc ghi được ảnh (kiểm tra lại sản phảm vừa tạo");
                }
            }
        }
        imageService.saveAll(imageEntityList);
        returnValue = modelMapper.map(employeeDto1, EmployeeRest.class);
        return returnValue;
    }



    @PutMapping(path = "/{id}")
    public EmployeeRest updateEmployee(@PathVariable Long id, @RequestBody EmployeeRequest employeeDetails) {
        EmployeeRest returnValue = new EmployeeRest();

        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto = new ModelMapper().map(employeeDetails, EmployeeDto.class);
        try {
            EmployeeDto updateEmployee = employeeService.updateEmployee(id, employeeDto);
            returnValue = new ModelMapper().map(updateEmployee, EmployeeRest.class);
        }catch (Exception e) {
            System.out.println(e);
        }
        return returnValue;
    }

    @DeleteMapping(path = "/{id}")
    public OperationStatusModel deleteEmployee(@PathVariable Long id) {
        OperationStatusModel returnValue = new OperationStatusModel();
        returnValue.setOperationName(RequestOperationName.DELETE.name());

        try {
            employeeService.deleteEmployee(id);
            returnValue.setOperationResult(RequestOperationStatus.SUCCESS.name());
            returnValue.setOperationMessage("Xoa Thanh Cong.");
        }catch (DataIntegrityViolationException exception){
            returnValue.setOperationResult(RequestOperationStatus.ERROR.name());
            returnValue.setOperationMessage("Lỗi khi xóa Nhan Vien: Nhan Vien có tham chiếu đến khoá ngoại.");
        }catch (Exception e){
            returnValue.setOperationResult(RequestOperationStatus.ERROR.name());
            returnValue.setOperationMessage("Lỗi khi xóa Nhan Vien: " + e.getMessage());
        }
        return returnValue;
    }



    @GetMapping("/search")
    public List<EmployeeRest> searchEmployees(@RequestParam(value = "employeeName") String employeeName,
                                              @RequestParam(value = "page", defaultValue = "0") int page,
                                              @RequestParam(value = "limit", defaultValue = "5") int limit) {
        List<EmployeeRest> returnValue = new ArrayList<>();

        List<EmployeeDto> employees = employeeService.getEmployeeByEmployeeName(employeeName, page, limit);

        for (EmployeeDto employeeDto : employees) {
            EmployeeRest employeeModel = new EmployeeRest();
            BeanUtils.copyProperties(employeeDto, employeeModel);
            returnValue.add(employeeModel);
        }

        return returnValue;
    }

    @GetMapping()
    public PaginationRest getEmployees(@RequestParam(value = "page", defaultValue = "0") int page,
                                       @RequestParam(value = "limit", defaultValue = "5") int limit,
                                       @RequestParam(value = "filter", defaultValue = "") String filter) {

        List<EmployeeRest> returnValue = new ArrayList<>();

        List<EmployeeDto> employees = employeeService.getEmployees(page, limit, filter);

        for (EmployeeDto employeeDto : employees) {
            EmployeeRest employeeModel = new EmployeeRest();
            BeanUtils.copyProperties(employeeDto, employeeModel);
            returnValue.add(employeeModel);
        }

        PaginationRest paginationRest = new PaginationRest();
        paginationRest.setListEmployees(returnValue);
        paginationRest.setTotal(employeeService.count(filter));


        return paginationRest;
    }

    @GetMapping("/{id}/image-employee")
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

    @GetMapping("/{id}/image-main-employee")
    public ResponseEntity<?> getImageMain(@PathVariable Long id) {
        Optional<ImageEntity> imageEntity = imageService.findById(id);
        if (!imageEntity.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Không tồn tại");
        }
        byte[] imageBytes = new byte[0];
        try {
            imageBytes = fileImgUtil.convertBlobToByteArray(imageEntity.get().getImage());
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

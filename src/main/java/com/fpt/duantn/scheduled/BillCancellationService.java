package com.fpt.duantn.scheduled;

import com.fpt.duantn.io.entity.BillDetailEntity;
import com.fpt.duantn.io.entity.BillEntity;
import com.fpt.duantn.io.entity.ProductDetailEntity;
import com.fpt.duantn.io.repository.BillDetailRepository;
import com.fpt.duantn.io.repository.BillRepository;
import com.fpt.duantn.io.repository.ProductDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
@Service
public class BillCancellationService {
    @Autowired
    private BillRepository billRepository;
    @Autowired
    private BillDetailRepository billDetailRepository;
    @Autowired
    private ProductDetailRepository productDetailRepository;

    @Transactional
    @Scheduled(fixedRate = 300000,initialDelay = 60000)
    public void cancelPendingOrders() {
        List<BillEntity> pendingOrders = billRepository.findByPaymentTypeAndStatusAndCreateDateBefore(
                2,-2,
                LocalDateTime.now().minusMinutes(1)
        );
        System.out.println("SYSTEM : "+  LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss dd-MM-yyyy"))+" :  Quét bill chờ thanh toán");
        for (BillEntity bill: pendingOrders) {


            try {
                if (bill.getPaymentAmount()==null||bill.getPaymentAmount().doubleValue()==0){
                    List<BillDetailEntity> billDetails = billDetailRepository.findByBillIdAndStatus(bill.getId(),1);
                    List<ProductDetailEntity> productDetails2  = new ArrayList<>();
                    for (BillDetailEntity billDetail:billDetails) {
                        ProductDetailEntity productDetail = billDetail.getProductDetail();
                        productDetail.setQuantity(productDetail.getQuantity()+billDetail.getQuantity());
                        productDetails2.add(productDetail);
                    }

                    bill.setStatus(0);
                    productDetailRepository.saveAll(productDetails2);
                    billRepository.save(bill);
                    System.out.println("Bill : "+ bill.getId()+" đã hủy do chưa thanh toán quá 35 phút ");
                }else {
                    System.out.println("Bill : "+ bill.getId()+" đã được thanh toán ở trạng thái chờ thanh toán");
                }
            }catch (Exception e){
                System.out.println("Bill : "+ bill.getId()+" Lỗi hủy đơn");
            }

        }
    }
}

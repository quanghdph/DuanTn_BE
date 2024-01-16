package com.fpt.duantn.ui.controller;

import com.fpt.duantn.services.*;
import com.fpt.duantn.io.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;




@RestController
public class BanHangController {
    @Autowired
    private ProductService productService;
    @Autowired
    private BillService billService;
//    @GetMapping("")
//    public String home() {
//        return "redirect:/login";
//    }

    @GetMapping("/payment/success")
    public String paymentsuccess(@RequestParam("billId") Long billId, @RequestParam("amount") Double amount, @RequestParam("transactionNo") String transactionNo, Model model) {
        BillEntity bill = billService.findById(billId).get();

        if (bill.getEmployee()!=null){
            EmployeeEntity employee =  new EmployeeEntity();
            employee.setId(bill.getEmployee().getId());
            employee.setFirstName(bill.getEmployee().getFirstName());
            employee.setLastName(bill.getEmployee().getLastName());
            bill.setEmployee(employee);
        }
        if (bill.getCustomer()!=null) {
            CustomerEntity customer = new CustomerEntity();
            customer.setId(bill.getCustomer().getId());
            customer.setFirstName(bill.getCustomer().getFirstName());
            customer.setLastName(bill.getCustomer().getLastName());
            bill.setCustomer(customer);
        }

//        if (bill.getPaymentEmployee()!=null) {
//            Employee paymentEmployee = new Employee();
//            paymentEmployee.setId(bill.getPaymentEmployee().getId());
//            paymentEmployee.setName(bill.getPaymentEmployee().getName());
//            bill.setPaymentEmployee(paymentEmployee);
//        }
        model.addAttribute("bill",bill);
        model.addAttribute("amount",amount/100);
        model.addAttribute("transactionNo",transactionNo);
        return "/admin/view/payment/success";
    }

    @GetMapping("/payment/error")
    public String paymenterror(@RequestParam("billId") Long billId, @RequestParam("transactionNo") String transactionNo, Model model) {
        BillEntity bill = billService.findById(billId).get();

        if (bill.getEmployee()!=null){
            EmployeeEntity employee =  new EmployeeEntity();
            employee.setId(bill.getEmployee().getId());
            employee.setFirstName(bill.getEmployee().getFirstName());
            employee.setLastName(bill.getEmployee().getLastName());
            bill.setEmployee(employee);
        }
        if (bill.getCustomer()!=null) {
            CustomerEntity customer = new CustomerEntity();
            customer.setId(bill.getCustomer().getId());
            customer.setFirstName(bill.getCustomer().getFirstName());
            customer.setLastName(bill.getCustomer().getLastName());
            bill.setCustomer(customer);
        }

//        if (bill.getPaymentEmployee()!=null) {
//            Employee paymentEmployee = new Employee();
//            paymentEmployee.setId(bill.getPaymentEmployee().getId());
//            paymentEmployee.setName(bill.getPaymentEmployee().getName());
//            bill.setPaymentEmployee(paymentEmployee);
//        }
        model.addAttribute("bill",bill);
        model.addAttribute("transactionNo",transactionNo);
        return "/admin/view/payment/error";
    }
}

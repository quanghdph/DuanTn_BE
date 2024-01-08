package com.fpt.duantn.ui.controller;

import com.fpt.duantn.services.SendMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fpt.duantn.services.*;
@RestController
@RequestMapping("api/send-mail")
public class SendMailController {
    @Autowired
    SendMailService sendMail;

    @Autowired
    CustomerService customerService;

    @PostMapping("/otp")
    public ResponseEntity<Integer> sendOpt(@RequestBody String email) {
        int random_otp = (int) Math.floor(Math.random() * (999999 - 100000 + 1) + 100000);
        if (customerService.existsByEmail(email)) {
            return ResponseEntity.notFound().build();
        }
        sendMailOtp(email, random_otp, "Xác nhận tài khoản!");
        return ResponseEntity.ok(random_otp);
    }


    // sendmail
    public void sendMailOtp(String email, int password, String title) {
        String body = "<div>\r\n" + "<h3>Mật khẩu của bạn là: <span style=\"color:red; font-weight: bold;\">"
                + password + "</span></h3>\r\n" + "    </div>";
        sendMail.queue(email, title, body);
    }
}

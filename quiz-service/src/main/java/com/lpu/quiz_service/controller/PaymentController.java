package com.lpu.quiz_service.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PaymentController {

    @GetMapping("/quiz/payment-success")
   
//    @ResponseBody
    public String paymentSuccess() {
        // Return a success view for payment
        return "redirect:/payment-success.html";  // This would correspond to payment-success.html or payment-success.jsp
    }

    @GetMapping("/quiz/payment-cancel")
//   @ResponseBody
    public String paymentCancel() {
        // Return a cancel view for payment
        return "redirect:/payment-cancel.html";  // This would correspond to payment-cancel.html or payment-cancel.jsp
    }
}

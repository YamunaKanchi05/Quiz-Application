package com.lpu.payment_service.service;

import com.lpu.payment_service.dto.PaymentRequest;
import com.lpu.payment_service.dto.PaymentResponse;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class StripeService {

    @Value("${stripe.api.key}")
    private String secretKey;

    public PaymentResponse checkout(PaymentRequest request) {
        Stripe.apiKey = secretKey;

        try {
            SessionCreateParams.LineItem.PriceData.ProductData productData =
                    SessionCreateParams.LineItem.PriceData.ProductData.builder()
                            .setName(request.getQuizTitle())
                            .build();

            SessionCreateParams.LineItem.PriceData priceData =
                    SessionCreateParams.LineItem.PriceData.builder()
                            .setCurrency(request.getCurrency() != null ? request.getCurrency() : "inr")
                            .setUnitAmount(request.getAmount()) // amount in paise
                            .setProductData(productData)
                            .build();

            SessionCreateParams.LineItem lineItem =
                    SessionCreateParams.LineItem.builder()
                            .setQuantity(request.getQuantity())
                            .setPriceData(priceData)
                            .build();

            SessionCreateParams params = SessionCreateParams.builder()
                    .setMode(SessionCreateParams.Mode.PAYMENT)
                    .setSuccessUrl("http://localhost:8090/quiz/payment-success") // your frontend URL
                    .setCancelUrl("http://localhost:8090/quiz/payment-cancel")
                    .addLineItem(lineItem)
                    .build();

            Session session = Session.create(params);

            return PaymentResponse.create(
            	    "SUCCESS",
            	    "Payment session created",
            	    session.getId(),
            	    session.getUrl()
            	);


        } catch (StripeException e) {
        	 return PaymentResponse.create(
             	    "Failed",
             	    "Payment session not created",
             	    null,
             	    null
             	);
        }
            
    }
}

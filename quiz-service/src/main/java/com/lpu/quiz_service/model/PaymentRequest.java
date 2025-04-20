package com.lpu.quiz_service.model;

public class PaymentRequest {
    private String quizTitle;
    private Long amount;       // In paise (₹100 = 10000)
    private Long quantity;
    private String currency;   // e.g., "inr"
	public String getQuizTitle() {
		return quizTitle;
	}
	public void setQuizTitle(String quizTitle) {
		this.quizTitle = quizTitle;
	}
	public Long getAmount() {
		return amount;
	}
	public void setAmount(Long amount) {
		this.amount = amount;
	}
	public Long getQuantity() {
		return quantity;
	}
	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public PaymentRequest(String quizTitle, Long amount, Long quantity, String currency) {
		super();
		this.quizTitle = quizTitle;
		this.amount = amount;
		this.quantity = quantity;
		this.currency = currency;
	}
	public PaymentRequest() {
		super();
	}
    
}

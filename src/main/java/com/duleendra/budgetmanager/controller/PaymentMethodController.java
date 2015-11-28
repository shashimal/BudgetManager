package com.duleendra.budgetmanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.duleendra.budgetmanager.service.expenseservice.PaymentMethodService;

@Controller
public class PaymentMethodController {

	@Autowired
	PaymentMethodService paymentMethodService;

	public PaymentMethodService getPaymentMethodService() {
		return paymentMethodService;
	}

	public void setPaymentMethodService(PaymentMethodService paymentMethodService) {
		this.paymentMethodService = paymentMethodService;
	}
	
	@RequestMapping(value = "/payment-method-list.html")
	public ModelAndView paymentMethodList() {
		return new ModelAndView("payment_method_list","paymentMethods",this.getPaymentMethodService().getPaymentMethods());
	}
}

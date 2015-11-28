package com.duleendra.budgetmanager.service.expenseservice;

import java.util.List;

import com.duleendra.budgetmanager.model.PaymentMethod;

public interface PaymentMethodService {

	public List<PaymentMethod> getPaymentMethods();
	public PaymentMethod findPaymentMethod(long id);
        public PaymentMethod findPaymentMethodByName(String name);
}

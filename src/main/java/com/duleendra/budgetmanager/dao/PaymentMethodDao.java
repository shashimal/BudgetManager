package com.duleendra.budgetmanager.dao;

import java.util.List;

import com.duleendra.budgetmanager.model.PaymentMethod;

public interface PaymentMethodDao {
	
	public  PaymentMethod findPaymentMethod(long id);
	public List<PaymentMethod> getPaymentMethods();
        public PaymentMethod findPaymentMethodByName(String name);
}

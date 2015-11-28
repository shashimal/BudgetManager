package com.duleendra.budgetmanager.service.expenseservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.duleendra.budgetmanager.dao.PaymentMethodDao;
import com.duleendra.budgetmanager.model.PaymentMethod;

@Service
public class PaymentMethodServiceImpl implements PaymentMethodService {

	@Autowired
	PaymentMethodDao paymentMethodDao;
	
	@Override
	public List<PaymentMethod> getPaymentMethods() {
		return paymentMethodDao.getPaymentMethods();
	}

	@Override
	public PaymentMethod findPaymentMethod(long id) {
		return paymentMethodDao.findPaymentMethod(id);
	}

    @Override
    public PaymentMethod findPaymentMethodByName(String name) {
        return paymentMethodDao.findPaymentMethodByName(name);
    }

}

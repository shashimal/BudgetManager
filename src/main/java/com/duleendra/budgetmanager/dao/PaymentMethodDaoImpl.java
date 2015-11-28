package com.duleendra.budgetmanager.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.duleendra.budgetmanager.model.ExpenseSubCategory;
import com.duleendra.budgetmanager.model.PaymentMethod;
import org.hibernate.Query;

@Repository
@Transactional
public class PaymentMethodDaoImpl implements PaymentMethodDao{

	@Autowired
	private SessionFactory sessionFactory;

	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	/*
	 * Get the expense category list
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<PaymentMethod> getPaymentMethods() {
		return this.getSession().createQuery("from PaymentMethod m order by m.method").list();
	}

	@Override
	public PaymentMethod findPaymentMethod(long id) {
		return (PaymentMethod) sessionFactory.getCurrentSession().get(PaymentMethod.class, id);
	}

    @Override
    public PaymentMethod findPaymentMethodByName(String name) {
        Query query = sessionFactory.getCurrentSession().createQuery("from PaymentMethod c where c.method= :p");
        query.setString("p", name);
        if (!query.list().isEmpty()) {
            return (PaymentMethod) query.list().get(0);
        } else {
            return null;
        }
    }

	
}

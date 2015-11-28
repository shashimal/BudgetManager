package com.duleendra.budgetmanager.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.duleendra.budgetmanager.model.ExpenseID;

@Repository
@Transactional
public class ExpenseIDGeneratorDaoImpl implements ExpenseIDGeneratorDao {

	@Autowired
	private SessionFactory sessionFactory;

	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@SuppressWarnings("unchecked")
	@Override
	public long getCurrentId() {
		long currentId = 0;
		List<ExpenseID> list = this.getSession().createQuery("from ExpenseID p").list();
		if (list.size() == 1) {
			currentId = list.get(0).getValue();
		}

		return currentId;

	}

	@Override
	public int updateCurrentId(long newId) {
		Query query = sessionFactory.getCurrentSession().createQuery("update ExpenseID set value=:value where id=:id");
		query.setParameter("value", newId);
		query.setParameter("id", 1L);
		int updated = query.executeUpdate();
		return updated;
	}

}

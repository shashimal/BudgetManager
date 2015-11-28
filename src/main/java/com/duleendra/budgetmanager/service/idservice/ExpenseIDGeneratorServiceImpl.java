package com.duleendra.budgetmanager.service.idservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.duleendra.budgetmanager.dao.ExpenseIDGeneratorDao;

@Service
public class ExpenseIDGeneratorServiceImpl implements ExpenseIDGeneratorService {

	@Autowired
	ExpenseIDGeneratorDao expenseIDGeneratorDao;
	
	@Override
	public long generateID() {
		long currentID =  expenseIDGeneratorDao.getCurrentId();
		long newId = currentID + 1;
		return newId;
	}

	@Override
	public int updateCurrentId(long newId) {
		return expenseIDGeneratorDao.updateCurrentId(newId);
	}

}

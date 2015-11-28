package com.duleendra.budgetmanager.dao;


public interface ExpenseIDGeneratorDao extends IDGeneratorDao{
	public int updateCurrentId(long newId);

}

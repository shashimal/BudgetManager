package com.duleendra.budgetmanager.dao;

import java.util.List;

import com.duleendra.budgetmanager.model.Payee;

public interface PayeeDao {

	public List<Payee> getPayees(int offSet, int recordsPerPage);
        public List<Payee> allPayees();
        public int getPayeeCount();
	public Payee findPayee(long id);
        public void savePayee(Payee payee);
        public int deletePayee(long id);
        public Payee findPayeeById(long id);
        public Payee findPayeeByName(String name);
}

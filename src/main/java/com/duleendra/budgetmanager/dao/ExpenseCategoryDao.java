package com.duleendra.budgetmanager.dao;

import java.util.List;

import com.duleendra.budgetmanager.model.ExpenseCategory;

public interface ExpenseCategoryDao {

        public List<ExpenseCategory> allExpenseCategories();
	public List<ExpenseCategory> getExpenseCategories(int offSet, int recordsPerPage);
        public int getExpenseCategoryCount();
	public void saveExpenseCategory(ExpenseCategory expenseCategory);
	public int deleteExpenseCategory(long id);
	public ExpenseCategory findExpenseCategory(long id);
        public ExpenseCategory findExpenseCategoryByName(String name);
                
}

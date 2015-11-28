package com.duleendra.budgetmanager.service.expenseservice;

import java.util.List;

import com.duleendra.budgetmanager.model.ExpenseCategory;

public interface ExpenseCategoryService {

        public List<ExpenseCategory> allExpenseCategories();
	public List<ExpenseCategory> getExpenseCategories(int offSet, int recordsPerPage);
	public void saveExpenseCategory(ExpenseCategory expenseCategory);
	public int deleteExpenseCategory(long id);
	public ExpenseCategory findExpenseCategory(long id);
	public List<ExpenseCategory> getUnAssignedCategories();
        public int getExpenseCategoryCount();
        public ExpenseCategory findExpenseCategoryByName(String name);

}

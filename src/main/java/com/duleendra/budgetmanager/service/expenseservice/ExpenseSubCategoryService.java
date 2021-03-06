package com.duleendra.budgetmanager.service.expenseservice;

import java.util.List;

import com.duleendra.budgetmanager.model.ExpenseSubCategory;

public interface ExpenseSubCategoryService {

	public List<ExpenseSubCategory> getExpenseSubCategories(int offSet, int recordsPerPage);
        public int getExpenseSubCategoryCount();
	public void saveExpenseSubCategory(ExpenseSubCategory expenseSubCategory);
	public int deleteExpenseSubCategory(long id);
	public ExpenseSubCategory findExpenseSubCategory(long id);
	public List<ExpenseSubCategory> getExpenseSubCategoriesByExpenseId(long expenseCategoryId);
        public ExpenseSubCategory findExpenseSubCategoryByName(String name) ;

}

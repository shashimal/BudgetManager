package com.duleendra.budgetmanager.service.expenseservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.duleendra.budgetmanager.dao.ExpenseSubCategoryDao;
import com.duleendra.budgetmanager.model.ExpenseSubCategory;

@Service
public class ExpenseSubCategoryServiceImpl implements ExpenseSubCategoryService {

    @Autowired
    ExpenseSubCategoryDao expenseSubCategoryDao;

    /*
	 * Get the expense sub category list
     */
    @Override
    public List<ExpenseSubCategory> getExpenseSubCategories(int offSet, int recordsPerPage) {
        return expenseSubCategoryDao.getExpenseSubCategories(offSet, recordsPerPage);
    }

    /*
	 * Save an expense sub category
     */
    @Override
    public void saveExpenseSubCategory(ExpenseSubCategory expenseSubCategory) {
        expenseSubCategoryDao.saveExpenseSubCategory(expenseSubCategory);

    }

    /*
	 * Delete an expense sub category
     */
    @Override
    public int deleteExpenseSubCategory(long id) {
        return expenseSubCategoryDao.deleteExpenseSubCategory(id);
    }

    /*
	 * Find an expense sub category
     */
    @Override
    public ExpenseSubCategory findExpenseSubCategory(long id) {
        return expenseSubCategoryDao.findExpenseSubCategory(id);
    }

    @Override
    public List<ExpenseSubCategory> getExpenseSubCategoriesByExpenseId(long expenseCategoryId) {
        return expenseSubCategoryDao.getExpenseSubCategoriesByExpenseId(expenseCategoryId);
    }

    @Override
    public int getExpenseSubCategoryCount() {
        return expenseSubCategoryDao.getExpenseSubCategoryCount();
    }

    @Override
    public ExpenseSubCategory findExpenseSubCategoryByName(String name) {
        return expenseSubCategoryDao.findExpenseSubCategoryByName(name);
    }

}

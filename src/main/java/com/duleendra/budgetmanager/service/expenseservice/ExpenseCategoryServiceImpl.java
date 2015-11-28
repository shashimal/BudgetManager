package com.duleendra.budgetmanager.service.expenseservice;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.duleendra.budgetmanager.dao.ExpenseCategoryDao;
import com.duleendra.budgetmanager.model.ExpenseCategory;

@Service
public class ExpenseCategoryServiceImpl implements ExpenseCategoryService {

	@Autowired
	ExpenseCategoryDao expenseCategoryDao;

	/*
	 * Get the expense category list
	 */
	@Override
	public List<ExpenseCategory> getExpenseCategories(int offSet, int recordsPerPage) {
		return expenseCategoryDao.getExpenseCategories(offSet, recordsPerPage);
	}

	/*
	 * Save an expense category object
	 */
        @Override
	public void saveExpenseCategory(ExpenseCategory expenseCategory) {
		expenseCategoryDao.saveExpenseCategory(expenseCategory);
	}

	/*
	 * Delete an expense category
	 */
	@Override
	public int deleteExpenseCategory(long id) {
		return expenseCategoryDao.deleteExpenseCategory(id);
	}

	/*
	 * Find an expense category by id
	 */
	@Override
	public ExpenseCategory findExpenseCategory(long id) {
		return expenseCategoryDao.findExpenseCategory(id);
	}

	@Override
	public List<ExpenseCategory> getUnAssignedCategories() {
		List<ExpenseCategory> list = null;
//		if(this.getExpenseCategories().size() > 0 ){
//			list = new ArrayList<>();
//			for(ExpenseCategory expCat: this.getExpenseCategories()){
//				if(expCat.getSubcategories().isEmpty()){
//					list.add(expCat);
//				}
//			}
//		}
		
		return list;
	}

    @Override
    public int getExpenseCategoryCount() {
        return expenseCategoryDao.getExpenseCategoryCount();
    }

    @Override
    public List<ExpenseCategory> allExpenseCategories() {
        return expenseCategoryDao.allExpenseCategories();
    }

    @Override
    public ExpenseCategory findExpenseCategoryByName(String name) {
        return expenseCategoryDao.findExpenseCategoryByName(name);
    }

}

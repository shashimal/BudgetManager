/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.duleendra.budgetmanager.service.expenseservice;

import com.duleendra.budgetmanager.model.Expense;
import com.duleendra.budgetmanager.model.elasticsearch.ExpenseDocument;

/**
 *
 * @author duleendra
 */
public interface ExpenseService {
    
    public ExpenseDocument findExpenseById(Long id);
}

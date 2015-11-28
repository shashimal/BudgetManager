/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.duleendra.budgetmanager.service.esservice;

import com.duleendra.budgetmanager.elasticsearch.ESDocument;
import com.duleendra.budgetmanager.model.elasticsearch.ExpenseDocument;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.query.SearchQuery;

/**
 *
 * @author duleendra
 */
public interface ElasticsearchExpenseService {
    
    public ExpenseDocument findExpenseDcoumentById(Long id);
    public ESDocument createDocument(ExpenseDocument esDocument);
    public SearchQuery getAllExpenses(Pageable pageable);
    public void deleteDocument(ExpenseDocument esDocument);
}

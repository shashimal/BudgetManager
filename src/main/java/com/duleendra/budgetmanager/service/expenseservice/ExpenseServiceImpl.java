/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.duleendra.budgetmanager.service.expenseservice;

import com.duleendra.budgetmanager.elasticsearch.ESDocument;
import com.duleendra.budgetmanager.model.Expense;
import com.duleendra.budgetmanager.model.elasticsearch.ExpenseDocument;
import com.duleendra.budgetmanager.repository.ExpenseRepository;
import java.util.List;
import org.elasticsearch.index.query.FilterBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

/**
 *
 * @author duleendra
 */
@Service
public class ExpenseServiceImpl implements ExpenseService{

 @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;
    
    @Override
    public ExpenseDocument findExpenseById(Long documentId) {
        ExpenseDocument expenseDocument = null;
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                                    .withFilter(FilterBuilders.termFilter("documentId", documentId)).build();
     List<ExpenseDocument> listExpenseDocument = elasticsearchTemplate.queryForList(searchQuery, ExpenseDocument.class);
     
     if(!listExpenseDocument.isEmpty()){
         expenseDocument =  listExpenseDocument.get(0);
     }
     
     return expenseDocument;
    
    }
    
}

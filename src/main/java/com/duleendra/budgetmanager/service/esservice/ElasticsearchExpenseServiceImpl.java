/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.duleendra.budgetmanager.service.esservice;

import com.duleendra.budgetmanager.elasticsearch.ESDocument;
import com.duleendra.budgetmanager.elasticsearch.ESField;
import com.duleendra.budgetmanager.model.elasticsearch.ExpenseDocument;
import com.duleendra.budgetmanager.repository.ExpenseRepository;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

/**
 *
 * @author duleendra
 */
@Service
public class ElasticsearchExpenseServiceImpl implements ElasticsearchExpenseService {

    @Autowired
    ExpenseRepository expenseRepository;

    /*
     *Find an expense document
     */
    @Override
    public ExpenseDocument findExpenseDcoumentById(Long id) {
        return expenseRepository.findOne(id);
    }

    /*
     *Create an expense document
     */
    @Override
    public ESDocument createDocument(ExpenseDocument esDocument) {
        ESDocument savedDocument = null;
        if (esDocument != null) {
            savedDocument = expenseRepository.save(esDocument);

        }
        return savedDocument;
    }

    /*
     *Delete an expense document
     */
    @Override
    public void deleteDocument(ExpenseDocument expenseDcoument) {
        expenseRepository.delete(expenseDcoument.getDocumentId());
    }

    /*
     *Get all expenses search query
     *SearchQuery
     */
    @Override
    public SearchQuery getAllExpenses(Pageable pageable) {
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(
                        QueryBuilders.matchAllQuery()
                )
                .withSort(SortBuilders.fieldSort(ESField.DATE).order(SortOrder.DESC))
                .withSort(SortBuilders.fieldSort(ESField.CATEGORY).order(SortOrder.ASC))
                .build();

        searchQuery.setPageable(pageable);

        return searchQuery;
    }

}

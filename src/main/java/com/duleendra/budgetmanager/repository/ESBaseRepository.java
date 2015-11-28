package com.duleendra.budgetmanager.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.duleendra.budgetmanager.elasticsearch.ESDocument;

public interface ESBaseRepository<T extends ESDocument>  extends ElasticsearchRepository<T,Long> {

}

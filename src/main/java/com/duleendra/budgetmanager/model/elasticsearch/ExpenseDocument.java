package com.duleendra.budgetmanager.model.elasticsearch;

import org.springframework.data.elasticsearch.annotations.Document;

import com.duleendra.budgetmanager.elasticsearch.ESDocument;

@Document(indexName = "eimanager", type = "expense", shards = 5, replicas = 0, refreshInterval = "-1", indexStoreType = "memory")
public class ExpenseDocument extends ESDocument {

	private String category;
	private String subcategory;
	private double amount;
	private String description;
	private String paidby;
	private String payee;
	private String[] tag;
	private String paymentMethod;
	
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getSubcategory() {
		return subcategory;
	}
	public void setSubcategory(String subcategory) {
		this.subcategory = subcategory;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPaidby() {
		return paidby;
	}
	public void setPaidby(String paidby) {
		this.paidby = paidby;
	}
	public String getPayee() {
		return payee;
	}
	public void setPayee(String payee) {
		this.payee = payee;
	}
	public String[] getTag() {
		return tag;
	}
	public void setTag(String[] tag) {
		this.tag = tag;
	}
	public String getPaymentMethod() {
		return paymentMethod;
	}
	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	
	
}

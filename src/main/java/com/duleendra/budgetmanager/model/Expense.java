package com.duleendra.budgetmanager.model;

import java.util.Date;

import javax.persistence.Entity;


import org.springframework.format.annotation.DateTimeFormat;

public class Expense {

	private long id;

	private long expenseCategoryId;

	private long subCategoryId;

	private long paymentMethodId;

	private long payeeId;

	private double amount;

	private String description;

	private String paidby;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date date;

	private Date createdDate;
	private Date updatedDate;


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getExpenseCategoryId() {
		return expenseCategoryId;
	}

	public void setExpenseCategoryId(long expenseCategoryId) {
		this.expenseCategoryId = expenseCategoryId;
	}

	public long getSubCategoryId() {
		return subCategoryId;
	}

	public void setSubCategoryId(long subCategoryId) {
		this.subCategoryId = subCategoryId;
	}

	public long getPaymentMethodId() {
		return paymentMethodId;
	}

	public void setPaymentMethodId(long paymentMethodId) {
		this.paymentMethodId = paymentMethodId;
	}

	public long getPayeeId() {
		return payeeId;
	}

	public void setPayeeId(long payeeId) {
		this.payeeId = payeeId;
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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

}

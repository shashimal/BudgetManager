package com.duleendra.budgetmanager.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "expense_subcategory")
public class ExpenseSubCategory implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private long id;

	@Column(name = "expense_category_id")
	private long expenseCategoryId;

	@Column(name = "name")
	@NotEmpty
	private String name;

	@NotEmpty
	@Column(name = "description")
	private String description;

	@ManyToOne
    @JoinColumn(name="expense_category_id",referencedColumnName="id",insertable = false, updatable = false)
	private ExpenseCategory expenseCategory;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public long getExpenseCategoryId() {
		return expenseCategoryId;
	}

	public void setExpenseCategoryId(long expenseCategoryId) {
		this.expenseCategoryId = expenseCategoryId;
	}

	public ExpenseCategory getExpenseCategory() {
		return expenseCategory;
	}

	public void setExpenseCategory(ExpenseCategory expenseCategory) {
		this.expenseCategory = expenseCategory;
	}
	
}

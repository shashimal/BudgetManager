package com.duleendra.budgetmanager.model;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "expense_category")
public class ExpenseCategory implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private long id;

	@Column(name = "name")
	@NotEmpty
	private String name;

	@NotEmpty
	@Column(name = "description")
	private String description;

	@OneToMany(mappedBy = "expenseCategory", targetEntity = ExpenseSubCategory.class, fetch=FetchType.EAGER)
	private Collection subcategories;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Collection getSubcategories() {
		return subcategories;
	}

	public void setSubcategories(Collection subcategories) {
		this.subcategories = subcategories;
	}
	
}

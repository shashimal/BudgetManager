package com.duleendra.budgetmanager.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="expense_id")
public class ExpenseID implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "id")
	private long id;
	
	@Column(name = "value")
	private long value;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public long getValue() {
		return value;
	}

	public void setValue(long value) {
		this.value = value;
	}

	
}

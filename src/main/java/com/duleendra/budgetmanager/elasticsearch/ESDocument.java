package com.duleendra.budgetmanager.elasticsearch;

import java.util.Date;

import org.springframework.data.elasticsearch.annotations.Document;
@Document(indexName = "eimanager")
public class ESDocument {

	protected long documentId;
	protected String date;
	protected String createdDate;
	protected String updatedDate;
	protected long day;
	protected long year;
	protected long month;
	
	public long getDocumentId() {
		return this.documentId;
	}
	
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public void setDocumentId(long documentId) {
		this.documentId= documentId;
	}
	public String getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	public String getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(String updatedDate) {
		this.updatedDate = updatedDate;
	}
	public long getDay() {
		return day;
	}
	public void setDay(long day) {
		this.day = day;
	}
	public long getYear() {
		return year;
	}
	public void setYear(long year) {
		this.year = year;
	}
	public long getMonth() {
		return month;
	}
	public void setMonth(long month) {
		this.month = month;
	}
	
}

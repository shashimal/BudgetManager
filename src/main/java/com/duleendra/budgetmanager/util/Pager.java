package com.duleendra.budgetmanager.util;

/**
 *
 * @author duleendra
 */
public class Pager {

    private int page = 1;
    private int recordsPerPage = 20;
    private int offSet;
    private int noOfPages;
    private int noOfRecords;

    public void setCurrentPage(int page) {
        this.page = page;
    }

    public int getCurrentPage() {
        return this.page;
    }

    public void setRecordsPerPage(int recordsPerPage) {
        this.recordsPerPage = recordsPerPage;
    }

    public int getRecordsPerPage() {
        return this.recordsPerPage;
    }

    public void setNoOfRecords(int noOfRecords) {
        this.noOfRecords = noOfRecords;
    }

    public int getNoOfRecords() {
        return this.noOfRecords;
    }

    public int getOffSet() {
        this.offSet = (this.page - 1) * this.recordsPerPage;
        return this.offSet;
    }

    public int getNoOfPages() {
        this.noOfPages = (int) Math.ceil(this.noOfRecords * 1.0 / this.recordsPerPage);
        return this.noOfPages;
    }

}

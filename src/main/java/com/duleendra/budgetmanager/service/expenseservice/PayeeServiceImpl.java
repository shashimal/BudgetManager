package com.duleendra.budgetmanager.service.expenseservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.duleendra.budgetmanager.dao.PayeeDao;
import com.duleendra.budgetmanager.model.Payee;

@Service
public class PayeeServiceImpl implements PayeeService {

    @Autowired
    PayeeDao payeeDao;

    public PayeeDao getPayeeDao() {
        return payeeDao;
    }

    public void setPayeeDao(PayeeDao payeeDao) {
        this.payeeDao = payeeDao;
    }

    @Override
    public List<Payee> getPayees(int offSet, int recordsPerPage) {
        return this.getPayeeDao().getPayees(offSet, recordsPerPage);
    }

    @Override
    public Payee findPayee(long id) {
        return this.getPayeeDao().findPayee(id);
    }

    @Override
    public List<Payee> allPayees() {
        return this.getPayeeDao().allPayees();
    }

    @Override
    public int getPayeeCount() {
        return this.getPayeeDao().getPayeeCount();
    }

    @Override
    public void savePayee(Payee payee) {
        this.getPayeeDao().savePayee(payee);
    }

    @Override
    public int deletePayee(long id) {
       return this.getPayeeDao().deletePayee(id);
    }

    @Override
    public Payee findPayeeById(long id) {
       return this.getPayeeDao().findPayeeById(id);
    }

    @Override
    public Payee findPayeeByName(String name) {
        return this.getPayeeDao().findPayeeByName(name);
    }

}

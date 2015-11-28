package com.duleendra.budgetmanager.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.duleendra.budgetmanager.model.ExpenseSubCategory;
import com.duleendra.budgetmanager.model.Payee;
import org.hibernate.Query;

@Repository
@Transactional
public class PayeeDaoImpl implements PayeeDao {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Payee> getPayees(int offSet, int recordsPerPage) {
        return this.getSession().createQuery("from Payee p order by p.name")
                .setFirstResult(offSet)
                .setMaxResults(recordsPerPage)
                .list();
    }

    @Override
    public Payee findPayee(long id) {
        return (Payee) sessionFactory.getCurrentSession().get(Payee.class, id);
    }

    @Override
    public List<Payee> allPayees() {
        return this.getSession().createQuery("from Payee p order by p.name").list();
    }

    @Override
    public int getPayeeCount() {
        Query query = this.getSession().createQuery("from Payee p order by p.name");
        return query.list().size();
    }

    @Override
    public void savePayee(Payee payee) {
        if (payee.getId() != 0) {
            this.getSession().merge(payee);
        } else {
            this.getSession().persist(payee);
        }
    }

    @Override
    public int deletePayee(long id) {
        Query query = sessionFactory.getCurrentSession().createQuery("delete from Payee c where c.id= :p");
        int count = query.setParameter("p", id).executeUpdate();
        return count;
    }

    @Override
    public Payee findPayeeById(long id) {
       return (Payee) sessionFactory.getCurrentSession().get(Payee.class, id);
    }

    @Override
    public Payee findPayeeByName(String name) {
        Query query = sessionFactory.getCurrentSession().createQuery("from Payee c where c.name= :p");
        query.setString("p", name);
        if (!query.list().isEmpty()) {
            return (Payee) query.list().get(0);
        } else {
            return null;
        }
    }

}

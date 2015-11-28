package com.duleendra.budgetmanager.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.duleendra.budgetmanager.model.ExpenseCategory;

@Repository
@Transactional
public class ExpenseCategoryDaoImpl implements ExpenseCategoryDao {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    /*
	 * Get the expense category list
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<ExpenseCategory> getExpenseCategories(int offSet, int recordsPerPage) {

        Query query = this.getSession().createQuery("from ExpenseCategory c order by c.name Asc");
        query.setFirstResult(offSet);
        query.setMaxResults(recordsPerPage);
        return query.list();

    }

    /*
	 * Save an expense category object
     */
    @Override
    @Transactional
    public void saveExpenseCategory(ExpenseCategory expenseCategory) {
        if (expenseCategory.getId() != 0) {
            this.getSession().merge(expenseCategory);
        } else {
            this.getSession().persist(expenseCategory);
        }
    }

    /*
	 * Delete an expense category
     */
    @Override
    @Transactional
    public int deleteExpenseCategory(long id) {
        /*
		 * ExpenseCategory expenseCategory = (ExpenseCategory)
		 * sessionFactory.getCurrentSession().load(ExpenseCategory.class, new
		 * Integer(id)); if(expenseCategory != null){
		 * sessionFactory.getCurrentSession().delete(expenseCategory); }
         */
        Query query = sessionFactory.getCurrentSession().createQuery("delete from ExpenseCategory c where c.id= :p");
        int count = query.setParameter("p", id).executeUpdate();
        return count;
    }

    /*
	 * Find an expense category by id
     */
    @Override
    public ExpenseCategory findExpenseCategory(long id) {
        return (ExpenseCategory) sessionFactory.getCurrentSession().get(ExpenseCategory.class, id);
    }

    @Override
    public int getExpenseCategoryCount() {
        Query query = this.getSession().createQuery("from ExpenseCategory c order by c.name Asc");
        return query.list().size();
    }

    @Override
    public List<ExpenseCategory> allExpenseCategories() {
        Query query = this.getSession().createQuery("from ExpenseCategory c order by c.name Asc");
        return query.list();
    }

    @Override
    public ExpenseCategory findExpenseCategoryByName(String name) {
        Query query = sessionFactory.getCurrentSession().createQuery("from ExpenseCategory c where c.name= :p");
        query.setString("p", name);
        if(!query.list().isEmpty()){
            return (ExpenseCategory)query.list().get(0);
        }else{
            return null;
        }
        
    }

}

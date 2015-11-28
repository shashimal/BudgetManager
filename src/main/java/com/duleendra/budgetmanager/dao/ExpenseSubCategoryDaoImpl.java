package com.duleendra.budgetmanager.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.duleendra.budgetmanager.model.ExpenseSubCategory;

@Repository
@Transactional
public class ExpenseSubCategoryDaoImpl implements ExpenseSubCategoryDao {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    /*
	 * Get the expense sub category list
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<ExpenseSubCategory> getExpenseSubCategories(int offSet, int recordsPerPage) {
        return this.getSession().createQuery("from ExpenseSubCategory c order by c.expenseCategory.name Asc")
                .setFirstResult(offSet)
                .setMaxResults(recordsPerPage)
                .list();
    }

    /*
	 * Save an expense category object
     */
    @Override
    @Transactional
    public void saveExpenseSubCategory(ExpenseSubCategory expenseSubCategory) {
        if (expenseSubCategory.getId() != 0) {
            this.getSession().merge(expenseSubCategory);
        } else {
            this.getSession().persist(expenseSubCategory);
        }

    }

    /*
	 * Delete an expense category
     */
    @Override
    @Transactional
    public int deleteExpenseSubCategory(long id) {
        Query query = sessionFactory.getCurrentSession().createQuery("delete from ExpenseSubCategory c where c.id= :p");
        int count = query.setParameter("p", id).executeUpdate();
        return count;
    }

    /*
	 * Find an expense category by id
     */
    @Override
    public ExpenseSubCategory findExpenseSubCategory(long id) {
        return (ExpenseSubCategory) sessionFactory.getCurrentSession().get(ExpenseSubCategory.class, id);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<ExpenseSubCategory> getExpenseSubCategoriesByExpenseId(long expenseCategoryId) {
        List<ExpenseSubCategory> list = null;
        Query query = sessionFactory.getCurrentSession()
                .createQuery("from ExpenseSubCategory c where c.expenseCategoryId= :p");
        return list = query.setParameter("p", expenseCategoryId).list();
    }

    @Override
    public int getExpenseSubCategoryCount() {
        Query query = this.getSession().createQuery("from ExpenseSubCategory c order by c.expenseCategory.name Asc");
        return query.list().size();
    }

    @Override
    public ExpenseSubCategory findExpenseSubCategoryByName(String name) {
        Query query = sessionFactory.getCurrentSession().createQuery("from ExpenseSubCategory c where c.name= :p");
        query.setString("p", name);
        if (!query.list().isEmpty()) {
            return (ExpenseSubCategory) query.list().get(0);
        } else {
            return null;
        }
    }

}

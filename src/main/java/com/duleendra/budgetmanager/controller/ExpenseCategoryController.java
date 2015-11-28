package com.duleendra.budgetmanager.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.duleendra.budgetmanager.model.ExpenseCategory;
import com.duleendra.budgetmanager.service.expenseservice.ExpenseCategoryService;
import com.duleendra.budgetmanager.util.Pager;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.ui.Model;

@Controller
public class ExpenseCategoryController {

    @Autowired
    ExpenseCategoryService expenseCategoryService;

    private String message;

    public ExpenseCategoryService getExpenseCategoryService() {
        return expenseCategoryService;
    }

    public void setExpenseCategoryService(ExpenseCategoryService expenseCategoryService) {
        this.expenseCategoryService = expenseCategoryService;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    /*
	 * Display the expense category list
     */
    @RequestMapping(value = "/expense-category-list.html")
    public String expenseCategoryList(HttpServletRequest request, Model model) {
        Pager pager = new Pager();

        int page = 1;

        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }

        pager.setCurrentPage(page);
        pager.setNoOfRecords(expenseCategoryService.getExpenseCategoryCount());
        List<ExpenseCategory> expCategories = expenseCategoryService.getExpenseCategories(pager.getOffSet(), pager.getRecordsPerPage());

        model.addAttribute("expCategories", expCategories);
        model.addAttribute("noOfPages", pager.getNoOfPages());
        model.addAttribute("currentPage", pager.getCurrentPage());

        return "expense_category_list";

    }

    /*
	 * Create a new expense category
     */
    @RequestMapping(value = "/expense-category-create.html")
    public ModelAndView newExpenseCategory() {
        return new ModelAndView("expense_category_create", "expenseCategory", new ExpenseCategory());
    }

    /*
	 * View an expense category
     */
    @RequestMapping(value = "/expense-category-edit/{id}")
    public ModelAndView editExpenseCategory(@PathVariable long id) {
        return new ModelAndView("expense_category_edit", "expenseCategory", expenseCategoryService.findExpenseCategory(id));
    }

    /*
	 * Save an expense category
     */
    @RequestMapping(value = "/expense-category-save.html", method = RequestMethod.POST)
    public ModelAndView submit(@ModelAttribute("expenseCategory")
            @Valid ExpenseCategory command, BindingResult results,
            RedirectAttributes redirectAttr
    ) {
        if (results.hasErrors()) {
            return new ModelAndView("expense_category_create", "expenseCategory", command);
        } else {
            expenseCategoryService.saveExpenseCategory(command);
        }

        redirectAttr.addFlashAttribute("message", "Recrod saved successfully");
        return new ModelAndView("redirect:/expense-category-list.html");
    }

    /*
	 * Delete an expense category
     */
    @RequestMapping(value = "/expense-category-delete/{id}", method = RequestMethod.GET)
    public ModelAndView deleteExpenseCategory(@PathVariable long id, RedirectAttributes redirectAttr
    ) {
        int count = 0;
        if (id > 0) {
            count = expenseCategoryService.deleteExpenseCategory(id);
            if (count > 0) {
                this.message = "Record deleted successfully";
            } else {
                this.message = "Record deletion failed";
            }
        } else {
            this.message = "Invalid category id";
        }

        redirectAttr.addFlashAttribute("message", this.message);
        return new ModelAndView("redirect:/expense-category-list.html");
    }

}

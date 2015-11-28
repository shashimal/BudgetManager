package com.duleendra.budgetmanager.controller;

import java.util.List;

import javax.validation.Valid;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.duleendra.budgetmanager.model.ExpenseSubCategory;
import com.duleendra.budgetmanager.service.expenseservice.ExpenseCategoryService;
import com.duleendra.budgetmanager.service.expenseservice.ExpenseSubCategoryService;
import com.duleendra.budgetmanager.util.Pager;
import javax.servlet.http.HttpServletRequest;

@Controller
public class ExpenseSubCategoryController {

    @Autowired
    ExpenseSubCategoryService expenseSubCategoryService;

    @Autowired
    ExpenseCategoryService expenseCategoryService;

    private String message;

    public ExpenseSubCategoryService getExpenseCategoryService() {
        return expenseSubCategoryService;
    }

    public void setExpenseSubCategoryService(ExpenseSubCategoryService expenseSubCategoryService) {
        this.expenseSubCategoryService = expenseSubCategoryService;
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
    @RequestMapping(value = "/expense-subcategory-list.html")
    public String expenseCategoryList(HttpServletRequest request, Model model) {

        Pager pager = new Pager();

        int page = 1;

        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }

        pager.setCurrentPage(page);
        pager.setNoOfRecords(expenseSubCategoryService.getExpenseSubCategoryCount());
        List<ExpenseSubCategory> expSubCategories = expenseSubCategoryService.getExpenseSubCategories(pager.getOffSet(), pager.getRecordsPerPage());

        model.addAttribute("expSubCategories", expSubCategories);
        model.addAttribute("noOfPages", pager.getNoOfPages());
        model.addAttribute("currentPage", pager.getCurrentPage());

        return "expense_subcategory_list";
    }

    /*
	 * Create a new expense category
     */
    @RequestMapping(value = "/expense-subcategory-create.html")
    public String newExpenseCategory(Model model) {
        model.addAttribute("expenseCategories", expenseCategoryService.allExpenseCategories());
        model.addAttribute("expenseSubCategory", new ExpenseSubCategory());
        return "expense_subcategory_create";
    }

    /*
	 * View an expense category
     */
    @RequestMapping(value = "/expense-subcategory-edit/{id}")
    public ModelAndView editExpenseCategory(@PathVariable long id) {
        System.out.println(expenseSubCategoryService.findExpenseSubCategory(id).getExpenseCategory().getName());
        return new ModelAndView("expense_subcategory_edit", "expenseSubCategory", expenseSubCategoryService.findExpenseSubCategory(id));
    }

    /*
	 * Save an expense category
     */
    @RequestMapping(value = "/expense-subcategory-save.html", method = RequestMethod.POST)
    public ModelAndView submit(@ModelAttribute("expenseSubCategory") @Valid ExpenseSubCategory command, BindingResult results,
            RedirectAttributes redirectAttr) {
        if (results.hasErrors()) {
            System.out.println(results.toString());
            return new ModelAndView("expense_subcategory_create", "expenseSubCategory", command);
        } else {
            expenseSubCategoryService.saveExpenseSubCategory(command);
        }

        redirectAttr.addFlashAttribute("message", "Recrod saved successfully");
        return new ModelAndView("redirect:/expense-subcategory-list.html");
    }

    /*
	 * Delete an expense category
     */
    @RequestMapping(value = "/expense-subcategory-delete/{id}", method = RequestMethod.GET)
    public ModelAndView deleteExpenseCategory(@PathVariable long id, RedirectAttributes redirectAttr) {
        int count = 0;
        if (id > 0) {
            count = expenseSubCategoryService.deleteExpenseSubCategory(id);
            if (count > 0) {
                this.message = "Record deleted successfully";
            } else {
                this.message = "Record deletion failed";
            }
        } else {
            this.message = "Invalid category id";
        }

        redirectAttr.addFlashAttribute("message", this.message);
        return new ModelAndView("redirect:/expense-subcategory-list.html");
    }

    /*
     * Get the sub categories by expenseCategoryId
     */
    @RequestMapping(value = "/expense-subcategories", method = RequestMethod.POST)
    public @ResponseBody
    String subCategories(
            @RequestParam(value = "expenseCategoryId", required = true) long expenseCategoryId) {
        List<ExpenseSubCategory> list = expenseSubCategoryService.getExpenseSubCategoriesByExpenseId(expenseCategoryId);

        JSONArray ja = new JSONArray();

        for (ExpenseSubCategory subCategory : list) {
            JSONObject jo = new JSONObject();
            jo.put("id", subCategory.getId());
            jo.put("name", subCategory.getName());
            ja.put(jo);
        }

        JSONObject mainObj = new JSONObject();
        mainObj.put("subcategories", ja);

        return mainObj.toString();
    }
}

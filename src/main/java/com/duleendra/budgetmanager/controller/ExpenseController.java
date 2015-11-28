package com.duleendra.budgetmanager.controller;

import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.validation.Valid;

import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.duleendra.budgetmanager.elasticsearch.ESDocument;
import com.duleendra.budgetmanager.model.Expense;
import com.duleendra.budgetmanager.model.elasticsearch.ExpenseDocument;
import com.duleendra.budgetmanager.service.esservice.ElasticsearchExpenseService;
import com.duleendra.budgetmanager.service.expenseservice.ExpenseCategoryService;
import com.duleendra.budgetmanager.service.idservice.ExpenseIDGeneratorService;
import com.duleendra.budgetmanager.service.expenseservice.ExpenseSubCategoryService;
import com.duleendra.budgetmanager.service.expenseservice.PayeeService;
import com.duleendra.budgetmanager.service.expenseservice.PaymentMethodService;
import com.duleendra.budgetmanager.util.DateUtil;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;

import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.SearchQuery;

import org.springframework.data.elasticsearch.core.query.DeleteQuery;
import org.springframework.data.web.PageableDefault;

@Controller
public class ExpenseController {

    @Autowired
    ExpenseCategoryService expenseCategoryService;

    @Autowired
    PayeeService payeeService;

    @Autowired
    PaymentMethodService paymentMethodService;

    @Autowired
    ExpenseSubCategoryService expenseSubCategoryService;

    @Autowired
    ElasticsearchExpenseService esExpenseService;

    @Autowired
    ExpenseIDGeneratorService expenseIDGeneratorService;

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    private String message;

    /*
     * Create a new expense
     */
    @RequestMapping(value = "/expense-create.html")
    public String createExpense(Model model) {
        this.setCommomModelParameters(model);
        model.addAttribute("expenseObj", new Expense());
        return "expense_create";
    }

    /*
     * Inserting an expense document to Elasticsearch 
     * Update the existing dcoumentId
     */
    @RequestMapping(value = "/expense-save.html", method = RequestMethod.POST)
    public ModelAndView createExpense(@ModelAttribute("expenseObj") @Valid Expense expenseObj, BindingResult results,
            RedirectAttributes redirectAttr) {
        if (results.hasErrors()) {
            redirectAttr.addFlashAttribute("message", "Errors found");
            return new ModelAndView("redirect:/expense-list.html");
        } else {
            ExpenseDocument preparedESDoc = this.getPreparedESDocument(expenseObj);

            if (preparedESDoc != null) {
                ESDocument savedESDoc = esExpenseService.createDocument(preparedESDoc);
                if (savedESDoc != null) {
                    expenseIDGeneratorService.updateCurrentId(savedESDoc.getDocumentId());
                }
            } else {
                redirectAttr.addFlashAttribute("message", "ExpenseDocument is null");
                return new ModelAndView("redirect:/expense-list.html");
            }

        }
        redirectAttr.addFlashAttribute("message", "Recrod saved successfully");
        return new ModelAndView("redirect:/expense-list.html");
    }

    /**
     * Display the expense list
     *
     * @PageableDefault(sort = { "surname", "forename", "address.town" }, value
     * = 50)
     */
    @RequestMapping(value = "/expense-list.html")
    public String search(@PageableDefault(value = 20) Pageable pageable, Model model, HttpServletRequest req) {
        SearchQuery searchQuery = esExpenseService.getAllExpenses(pageable);
        Page<ExpenseDocument> pager = elasticsearchTemplate.queryForPage(searchQuery, ExpenseDocument.class);

        model.addAttribute("expensePager", pager);
        model.addAttribute("noOfPages", pager.getTotalPages());
        model.addAttribute("currentPage", pager.getNumber());
        return "expense_list";

    }

    /*
     * Edit an expense document in Elasticsearch 
     */
    @RequestMapping(value = "/expense-edit/{id}")
    public String editExpense(@PathVariable Long id, Model model) {
        ExpenseDocument expenseDocument = esExpenseService.findExpenseDcoumentById(id);
        model.addAttribute("expenseObj", this.getExpenseObject(expenseDocument));
        model.addAttribute("expenseSubCategories", expenseSubCategoryService.getExpenseSubCategoriesByExpenseId(this.getExpenseObject(expenseDocument).getExpenseCategoryId()));
        this.setCommomModelParameters(model);
        return "expense_edit";
    }

    /*
     * Delete an expense document in Elasticsearch 
     */
    @RequestMapping(value = "/expense-delete/{id}", method = RequestMethod.GET)
    public ModelAndView deleteExpense(@PathVariable Long id, RedirectAttributes redirectAttr) {
        if (id > 0) {
            ExpenseDocument expenseDocument = esExpenseService.findExpenseDcoumentById(id);

            if (expenseDocument != null) {
                DeleteQuery dq = new DeleteQuery();
                dq.setIndex("eimanager");
                dq.setType("expense");
                dq.setQuery(QueryBuilders.termQuery("documentId", id));
                elasticsearchTemplate.delete(dq);
            }

        } else {
            this.message = "Invalid expense document id";
        }

        redirectAttr.addFlashAttribute(this.message, "Record deleted successfully");
        return new ModelAndView("redirect:/expense-list.html");
    }

    /*
     * Get an expense Object
     */
    private Expense getExpenseObject(ExpenseDocument expenseDcoument) {
        Expense expense = new Expense();
        expense.setId(expenseDcoument.getDocumentId());
        expense.setAmount(expenseDcoument.getAmount());
        expense.setDescription(expenseDcoument.getDescription());

        try {
            expense.setDate(DateUtil.getSimpleDateFormatObject(DateUtil.DATE_FORMAT_YYMMDD).parse(expenseDcoument.getDate()));
        } catch (ParseException ex) {
            Logger.getLogger(ExpenseController.class.getName()).log(Level.SEVERE, null, ex);
        }

        expense.setExpenseCategoryId(expenseCategoryService.findExpenseCategoryByName(expenseDcoument.getCategory()).getId());
        expense.setSubCategoryId(expenseSubCategoryService.findExpenseSubCategoryByName(expenseDcoument.getSubcategory()).getId());
        expense.setPayeeId(payeeService.findPayeeByName(expenseDcoument.getPayee()).getId());
        expense.setPaymentMethodId(paymentMethodService.findPaymentMethodByName(expenseDcoument.getPaymentMethod()).getId());
        expense.setPaidby(expenseDcoument.getPaidby());

        return expense;

    }

    /*
     *Prepare the expense document object for Elasticsearch return
     *ExpenseDocument
     */
    private ExpenseDocument getPreparedESDocument(Expense expense) {
        ExpenseDocument esDoc = null;

        if (expense != null) {

            esDoc = new ExpenseDocument();

            if (expense.getId() != 0) {
                esDoc.setDocumentId(expense.getId());
            } else {
                long newDcoumentId = expenseIDGeneratorService.generateID();
                esDoc.setDocumentId(newDcoumentId);
            }

            GregorianCalendar cal = new GregorianCalendar();
            cal.setTime(expense.getDate());

            esDoc.setDate(DateUtil.getSimpleDateFormatObject(DateUtil.DATE_FORMAT_YYMMDD).format(cal.getTime()));
            esDoc.setYear(cal.get(Calendar.YEAR));
            esDoc.setMonth(cal.get(Calendar.MONTH) + 1);
            esDoc.setDay(cal.get(Calendar.DATE));

            GregorianCalendar cal2 = new GregorianCalendar();
            String updatedDate = DateUtil.getSimpleDateFormatObject(DateUtil.DATE_FORMAT_YYMMDDHHMMSS)
                    .format(cal2.getTime());
            esDoc.setCreatedDate(updatedDate);
            esDoc.setUpdatedDate(updatedDate);

            esDoc.setCategory(expenseCategoryService.findExpenseCategory(expense.getExpenseCategoryId()).getName());
            esDoc.setSubcategory(
                    expenseSubCategoryService.findExpenseSubCategory(expense.getSubCategoryId()).getName());
            esDoc.setAmount(expense.getAmount());
            esDoc.setPaidby(expense.getPaidby());
            esDoc.setPayee(payeeService.findPayee(expense.getPayeeId()).getName());
            esDoc.setPaymentMethod(paymentMethodService.findPaymentMethod(expense.getPaymentMethodId()).getMethod());
            esDoc.setDescription(expense.getDescription());

        }

        return esDoc;
    }

    /*
     *Set the common Model object parameters
     *ExpenseDocument
     */
    private void setCommomModelParameters(Model model) {
        model.addAttribute("expCategories", expenseCategoryService.allExpenseCategories());
        model.addAttribute("payees", payeeService.allPayees());
        model.addAttribute("paymentMethods", paymentMethodService.getPaymentMethods());
    }

}

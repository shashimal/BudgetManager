package com.duleendra.budgetmanager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.duleendra.budgetmanager.model.Payee;

import com.duleendra.budgetmanager.service.expenseservice.PayeeService;
import com.duleendra.budgetmanager.util.Pager;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class PayeeController {

    String message;
    
    @Autowired
    PayeeService payeeService;

    public PayeeService getPayeeService() {
        return payeeService;
    }

    public void setPayeeService(PayeeService payeeService) {
        this.payeeService = payeeService;
    }

    /*
	 * Display the expense category list
     */
    @RequestMapping(value = "/payee-list.html")
    public String payeeList(HttpServletRequest request, Model model) {
        Pager pager = new Pager();

        int page = 1;

        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }

        pager.setCurrentPage(page);
        pager.setNoOfRecords(payeeService.getPayeeCount());
        List<Payee> payees = payeeService.getPayees(pager.getOffSet(), pager.getRecordsPerPage());

        model.addAttribute("payees", payees);
        model.addAttribute("noOfPages", pager.getNoOfPages());
        model.addAttribute("currentPage", pager.getCurrentPage());
        
        return "payee_list";

    }
    
     /*
	 * Create a new payee
     */
    @RequestMapping(value = "/payee-create.html")
    public ModelAndView newPayee() {
        return new ModelAndView("payee_create", "payee", new Payee());
    }
    
     /*
	 * Save an expense category
     */
    @RequestMapping(value = "/payee-save.html", method = RequestMethod.POST)
    public ModelAndView submit(@ModelAttribute("payee")
            @Valid Payee payee, BindingResult results,
            RedirectAttributes redirectAttr
    ) {
        if (results.hasErrors()) {
            return new ModelAndView("payee_create", "payee", payee);
        } else {
            payeeService.savePayee(payee);
        }

        redirectAttr.addFlashAttribute("message", "Recrod saved successfully");
        return new ModelAndView("redirect:/payee-list.html");
    }
    
     /*
	 * View an expense category
     */
    @RequestMapping(value = "/payee-edit/{id}")
    public ModelAndView editExpenseCategory(@PathVariable long id) {
        return new ModelAndView("payee_edit", "payee", payeeService.findPayeeById(id));
    }
    
     /*
	 * Delete an expense category
     */
    @RequestMapping(value = "/payee-delete/{id}", method = RequestMethod.GET)
    public ModelAndView deletePayee(@PathVariable long id, RedirectAttributes redirectAttr
    ) {
        int count = 0;
        if (id > 0) {
            count = payeeService.deletePayee(id);
            if (count > 0) {
                this.message = "Record deleted successfully";
            } else {
                this.message = "Record deletion failed";
            }
        } else {
            this.message = "Invalid payee id";
        }

        redirectAttr.addFlashAttribute("message", this.message);
        return new ModelAndView("redirect:/payee-list.html");
    }
}

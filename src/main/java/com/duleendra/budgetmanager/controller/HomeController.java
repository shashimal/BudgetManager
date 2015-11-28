/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.duleendra.budgetmanager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author duleendra
 */
@Controller
public class HomeController {
    
    @RequestMapping(value="index.html")
    public String home(){
        return "index";
    }
    
    @RequestMapping(value="test.html")
    public String test(Model model){
        model.addAttribute("name", "Imalka and shashimal");
        return "test";
    }
}

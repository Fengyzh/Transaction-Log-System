package com.SW.transactionLogApplication.Controller;

import com.SW.transactionLogApplication.Mapper.TransactionMapper;
import com.SW.transactionLogApplication.Service.TransactionService;
import com.SW.transactionLogApplication.pojo.SearchType;
import com.SW.transactionLogApplication.pojo.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.List;


@Controller
@RequestMapping
public class transactionController {

    @Autowired
    private TransactionMapper transactionMapper;

    @Autowired
    private TransactionService transactionService;


    @RequestMapping("/items")
    @ResponseBody
    public List<Transaction> index() {
        List<Transaction> result = transactionMapper.getAll();
        return result;
    }

    @PostMapping("/items")
    public String AddLog(Transaction transaction) {

        transactionService.processNewTransaction(transaction);

        //transactionMapper.addTransaction(transaction);
        return "redirect:/";
    }


    @RequestMapping("/")
    public String landingPage(Model model) {
        List<Transaction> result = transactionMapper.getAll();
        model.addAttribute("data", result);
        return "index";
    }




    /*
    POST request template

    @PostMapping("/post")
    @ResponseBody
    public String addlog(Transaction transaction) {
        try {
            transactionMapper.addTransaction(transaction);
            return "OKAY";
        } catch (Exception e) {
            return "Item can not be added";
        }
    }
     */


    // Redirect to update page with the data about the selected item
    @GetMapping("/update")
    public String toUpdatePage(Model model, @RequestParam(defaultValue = "0") int ID) {
        Transaction result = transactionMapper.getItemByID(ID);
        model.addAttribute("data", result);
        return "update";
    }

    @PostMapping("items/update")
    public String updatelogByPost(Transaction transaction) {
        transactionMapper.updateTransaction(transaction);
        return "redirect:/";
    }



    /*
    PUT Request Template

    @PutMapping("/update")
    @ResponseBody
    public String updatelog(Transaction transaction) {
        try {
            transactionMapper.updateTransaction(transaction);
            return "Update OKAY";
        } catch (Exception e) {
            return "Item can not be updated";
        }
    }
    */





    @GetMapping("/delete")
    public String deletelog(@RequestParam(defaultValue = "0") int ID) {
        try {
            transactionMapper.deleteTransaction(ID);
            System.out.println(ID);
            return "redirect:/";
        } catch (Exception e) {
            return "redirect:/";
        }
    }




    @GetMapping("/add")
    public String toAddPage() {
        return "add";
    }




    // ViewS redirect
    @GetMapping("/viewS")
    public String viewSpecificPage(Model model, SearchType searchType) {
        List<Transaction> result = transactionMapper.getSpecifyItem(searchType);
        model.addAttribute("data", result);
        return "viewS";
    }


    /*
    Init API test


    @PostMapping("/test")
    @ResponseBody
    public String test(Transaction transaction) {
        transactionService.processNewTransaction(transaction);
        return "Okay";
    }
    */



}

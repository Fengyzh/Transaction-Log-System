package com.SW.transactionLogApplication.Controller;

import com.SW.transactionLogApplication.Mapper.TransactionMapper;
import com.SW.transactionLogApplication.Service.TransactionService;
import com.SW.transactionLogApplication.pojo.SearchType;
import com.SW.transactionLogApplication.pojo.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
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


    @RequestMapping("/")
    @ResponseBody
    public List<Transaction> index() {
        List<Transaction> result = transactionMapper.getAll();
        return result;
    }


    @RequestMapping("/landing")
    public String landingPage(Model model) {
        List<Transaction> result = transactionMapper.getAll();
        model.addAttribute("data", result);
        return "index";
    }


    @PostMapping("/post")
    @ResponseBody
    public String addlog(Transaction transaction) {
        transactionMapper.addTransaction(transaction);
        return "OKAY";
    }

    @PutMapping("/update")
    @ResponseBody
    public String updatelog(Transaction transaction) {
        transactionMapper.updateTransaction(transaction);
        return "Update OKAY";
    }

    @PostMapping("/update")
    public String updatelogByPost(Transaction transaction) {
        transactionMapper.updateTransaction(transaction);
        //System.out.println("------------- " + transaction.getAmount() + " " + transaction.getName() + " " + transaction.getID() + " " + transaction.getDate() + " " + transaction.getPurpose());
        return "redirect:/landing";
    }

    @GetMapping("/delete")
    //@ResponseBody
    public String deletelog(@RequestParam(defaultValue = "0") int ID) {
        transactionMapper.deleteTransaction(ID);
        System.out.println(ID);
        return "redirect:/landing";
    }

    @GetMapping("/add")
    public String toAddPage() {
        return "add";
    }

    @GetMapping("/update")
    public String toUpdatePage(Model model, @RequestParam(defaultValue = "0") int ID) {
        Transaction result = transactionMapper.getItemByID(ID);
        model.addAttribute("data", result);
        return "update";
    }


    @GetMapping("/viewS")
    public String viewSpecificPage(Model model, SearchType searchType) {
        List<Transaction> result = transactionMapper.getSpecifyItem(searchType);
        model.addAttribute("data", result);
        return "viewS";
    }



/*
    @PostMapping("/viewS")
    public String viewSpecificItem(SearchType searchType, Model model) {
        List<Transaction> result = transactionMapper.getSpecifyItem(searchType);
        model.addAttribute("data", result);
        return "viewS";
    }
*/

    @PostMapping("/add")
    public String AddLog(Transaction transaction) {

        transactionService.processNewTransaction(transaction);

        //transactionMapper.addTransaction(transaction);
        return "redirect:/landing";
    }

    @PostMapping("/test")
    @ResponseBody
    public String test(Transaction transaction) {
        transactionService.processNewTransaction(transaction);
        return "Okay";
    }




}

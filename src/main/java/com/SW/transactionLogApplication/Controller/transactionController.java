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
        try {
            transactionMapper.addTransaction(transaction);
            return "OKAY";
        } catch (Exception e) {
            return "Item can not be added";
        }
    }

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

    @PostMapping("/update")
    public String updatelogByPost(Transaction transaction) {
        transactionMapper.updateTransaction(transaction);
        return "redirect:/landing";
    }

    @GetMapping("/delete")
    public String deletelog(@RequestParam(defaultValue = "0") int ID) {
        try {
            transactionMapper.deleteTransaction(ID);
            System.out.println(ID);
            return "redirect:/landing";
        } catch (Exception e) {
            return "redirect:/landing";
        }
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

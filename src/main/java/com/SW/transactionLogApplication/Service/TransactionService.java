package com.SW.transactionLogApplication.Service;


import com.SW.transactionLogApplication.Mapper.TransactionMapper;
import com.SW.transactionLogApplication.pojo.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {


    @Autowired
    private TransactionMapper transactionMapper;

    public void processNewTransaction(Transaction transaction) {
        if (transaction.getNote().equals("") || transaction.getNote() == null){
            transaction.setNote("b");
            transactionMapper.addTransaction(transaction);
        } else {
            transactionMapper.addTransaction(transaction);
        }
    }


}

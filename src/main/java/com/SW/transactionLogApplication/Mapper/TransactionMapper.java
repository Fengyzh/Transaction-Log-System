package com.SW.transactionLogApplication.Mapper;

import com.SW.transactionLogApplication.pojo.SearchType;
import com.SW.transactionLogApplication.pojo.Transaction;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface TransactionMapper {

    List<Transaction> getAll();

    int addTransaction(Transaction transaction);

    int updateTransaction(Transaction transaction);

    int deleteTransaction(int id);

    List<Transaction> getSpecifyItem(SearchType searchType);

    Transaction getItemByID(int ID);

}

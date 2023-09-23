package com.example.ewallet;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

//    @Query(value = "update transaction set transaction_status = ?2 where txn_id = ?1", nativeQuery = true)
//    void updateTxn(String txnId, TransactionStatus transactionStatus);

    @Query(value = "select * from transaction where txn_id = ?1", nativeQuery = true)
    Transaction getTxn(String txnId);
}

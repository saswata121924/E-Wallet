package com.example.ewallet;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @PostMapping("/transact")
    public String createTxn(@Valid @RequestBody TransactionCreateRequest transactionCreateRequest){
        String txnId = transactionService.createTxn(transactionCreateRequest.to());
        return  "Your transaction has been initiated, here is the transaction Id: "+txnId;
    }
}

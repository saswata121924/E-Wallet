package com.example.ewallet;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransactionCreateRequest {

    @NotNull
    private int senderUserId;

    @NotNull
    private int receiverUserId;

    @NotNull
    private Double amount;

    private String purpose;

    public Transaction to(){
        return Transaction.builder()
                .senderUserId(senderUserId)
                .receiverUserId(receiverUserId)
                .amount(amount)
                .purpose(purpose)
                .build();
    }
}

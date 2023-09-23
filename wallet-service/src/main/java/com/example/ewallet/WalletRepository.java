package com.example.ewallet;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface WalletRepository extends JpaRepository<Wallet, Integer> {

    @Query(value = "select * from wallet where user_id = ?1", nativeQuery = true)
    Wallet findByUserId(int userId);

    @Transactional
    @Modifying
    @Query(value = "update wallet w set w.balance = w.balance + ?2 where w.user_id = ?1", nativeQuery = true)
    void updateWallet(int userId, Double amount);
}

package com.example.ewallet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WalletController {
    private static final String PERSON_REDIS_STRING_KEY_PREFIX = "per::";

    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    @Autowired
    WalletService walletService;

    @GetMapping("/wallet")
    public Wallet getWallet(@RequestParam("userId") int userId){
        Wallet w = (Wallet) redisTemplate.opsForValue().get(getUserKey(userId));
        if(w==null){
            w = walletService.getWallet(userId);
            redisTemplate.opsForValue().set(getUserKey(userId), w);
        }
        return w;
    }

    private String getUserKey(Integer userId){
        return PERSON_REDIS_STRING_KEY_PREFIX + userId;
    }
}

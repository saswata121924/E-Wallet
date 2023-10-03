package com.example.ewallet;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class UserService{
    private static final String USER_CREATE_TOPIC = "user_create";
    @Autowired
    UserRepository userRepository;

    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;
    
    public User createUser(User user) {
        user = userRepository.save(user);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("userId", user.getId());
        jsonObject.put("userEmail", user.getEmail());
        jsonObject.put("userContact", user.getContact());
        kafkaTemplate.send(USER_CREATE_TOPIC, jsonObject.toJSONString());
        return user;
    }

    public User getUser(int id){
        return userRepository.findById(id).orElse(null);
    }

}

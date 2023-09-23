package com.example.ewallet;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    @Autowired
    JavaMailSender javaMailSender;

    @Autowired
    SimpleMailMessage simpleMailMessage;

    private static final String TXN_COMPLETE_TOPIC = "txn_complete";

    @KafkaListener(topics = {TXN_COMPLETE_TOPIC}, groupId = "e-wallet")
    public void sendNotification(String message) throws Exception {
        JSONObject jsonObject = (JSONObject) new JSONParser().parse(message);
        if(!jsonObject.containsKey("txnId") ||
                !jsonObject.containsKey("sender") ||
                !jsonObject.containsKey("receiver") ||
                !jsonObject.containsKey("status"))
            throw new Exception("Notification Details are missing");

        String txnId = (String) jsonObject.get("txnId");
        String senderEmail = (String) jsonObject.get("sender");
        String receiverEmail = (String) jsonObject.get("receiver");
        String status = (String) jsonObject.get("status");
        Double amount = (Double) jsonObject.get("amount");

        simpleMailMessage.setText("Hi, your txn with id: "+txnId+" got "+status);
        simpleMailMessage.setTo(senderEmail);
        simpleMailMessage.setSubject("Payment Details");
        simpleMailMessage.setFrom("digitalpayment460@gmail.com");
        javaMailSender.send(simpleMailMessage);

        if("SUCCESSFUL".equals(status)){
            simpleMailMessage.setText("Hi, you got amount Rs"+amount+" from "+senderEmail+" in your wallet");
            simpleMailMessage.setTo(receiverEmail);
            javaMailSender.send(simpleMailMessage);
        }
    }
}

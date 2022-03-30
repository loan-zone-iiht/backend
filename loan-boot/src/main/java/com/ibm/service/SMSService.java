package com.ibm.service;



import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;

import com.ibm.pojo.SMS;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
@Component
public class SMSService {
     private final String ACCOUNT_SID ="AC1272336dfaf920cf19319a0b06280b7f";

    private final String AUTH_TOKEN = "043f16a4bb78aae55f3b1e424d03d651";

    private final String FROM_NUMBER = "+14043417638";

    public void send(SMS sms) throws ParseException {
    	Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
      
    	
        int min = 100000;  
         int max = 999999; 
        int number=(int)(Math.random()*(max-min+1)+min);
      
        
        String msg ="Your OTP - "+number+ " please verify this OTP in your Application by Er Prince kumar Technoidentity.com";
       
        
        Message message = Message.creator(new PhoneNumber(sms.getPhoneNo()), new PhoneNumber(FROM_NUMBER), msg)
                .create();
       
    }

    public void receive(MultiValueMap<String, String> smscallback) {
   
    }

}
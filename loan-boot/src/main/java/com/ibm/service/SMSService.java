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
     private final String ACCOUNT_SID ="ACf53066d73bbd78a7372d9644c98c3a21";

    private final String AUTH_TOKEN = "a605cd7cb05801fb4f84c3bb2226f5a3";

    private final String FROM_NUMBER = "+14432321808";

    public void send(SMS sms) throws ParseException {
    	Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
      
    	
        int min = 100000;  
         int max = 999999; 
        int number=(int)(Math.random()*(max-min+1)+min);
      
        
        String msg ="Your OTP - "+number+ " Please verify this OTP in your Application by Loan-Zone";
       
        
        Message message = Message.creator(new PhoneNumber(sms.getPhoneNo()), new PhoneNumber(FROM_NUMBER), msg)
                .create();
       
    }

    public void receive(MultiValueMap<String, String> smscallback) {
   
    }

}
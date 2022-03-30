
package com.ibm.message;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;

import com.ibm.pojo.SMS;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
@Component
public class MessageSender {
	@Autowired
	private Environment env;
     private final String ACCOUNT_SID =env.getProperty("twilio.accountId");

    private final String AUTH_TOKEN = env.getProperty("twilio.authtoken");

    private final String FROM_NUMBER = env.getProperty("twilio.senderphonenumber");

    public void send(String sms,int otp) {
    	
    	System.out.println(sms);
    	Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
      
        
        String msg ="Your OTP - "+otp+ " Please verify this OTP in your Application by Loan-Zone";
       
        
        Message message = Message.creator(new PhoneNumber(sms), new PhoneNumber(FROM_NUMBER), msg)
                .create();
       
    }

    public void receive(MultiValueMap<String, String> smscallback) {
   
    }

}
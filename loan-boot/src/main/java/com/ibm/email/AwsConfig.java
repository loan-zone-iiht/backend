package com.ibm.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;


/**
 * The {AwsConfig} class is for setting up the configuration for AWS
 * We've used AWS SES for sending email.
 * 
 * 
 * @author Saswata Dutta
 */



@Configuration
public class AwsConfig {
	@Autowired
	private Environment env;
	/** Method to provide AWS credentials */
	public AWSStaticCredentialsProvider awsCredentials() {
		
		/** reading credentials from properties file */
		String accessKey = env.getProperty("aws.access.key");
		String accessSecret = env.getProperty("aws.access.secret");
		// System.err.println(accessKey + " " +accessSecret);
		BasicAWSCredentials credentials = new BasicAWSCredentials(accessKey, accessSecret);
		return new AWSStaticCredentialsProvider(credentials);
	}
	
	/** Method to set-up AWS SES */
	@Bean
	public AmazonSimpleEmailService getAmazonSimpleEmailService() {
		return AmazonSimpleEmailServiceClientBuilder.standard().withCredentials(awsCredentials())
				.withRegion("ap-south-1").build();
	}
}

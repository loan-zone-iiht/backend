package com.ibm.pojo;
/**
 * Class {LoginPOJO} is a simple POJO
 * to handle the incoming request object from 
 * login controller
 * 
 * @author Saswata Dutta
 */
public class LoginPOJO {
	private String email;
	private String phone;
	private String password;
	private int otp;
	private int id;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getOtp() {
		return otp;
	}
	public void setOtp(int otp) {
		this.otp = otp;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
}

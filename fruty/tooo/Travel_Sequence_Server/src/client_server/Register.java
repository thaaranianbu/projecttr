package client_server;

import java.io.Serializable;

public class Register implements Serializable
{
	
	String name;
	String username;
	String password;
	String email;
	String blood_g;
	public String getName() {
		return name;
	}
	public String getBlood_g() {
		return blood_g;
	}
	public void setBlood_g(String blood_g) {
		this.blood_g = blood_g;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobile_no() {
		return mobile_no;
	}
	public void setMobile_no(String mobile_no) {
		this.mobile_no = mobile_no;
	}
	public byte[] getImages() {
		return images;
	}
	public void setImages(byte[] images) {
		this.images = images;
	}
	String mobile_no;
	byte[] images;
	

}

package com.kakao.kitkat.entities;

import org.springframework.stereotype.Component;

@Component
public class Member {
	private String email;
	private String name;
	private String password;
	private String phone1;
	private String phone2;
	private String phone3;
	private int memlevel;
	private String photo;
	private String oldphoto;
	private String oldpassword;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone1() {
		return phone1;
	}

	public void setPhone1(String phone1) {
		this.phone1 = phone1;
	}

	public String getPhone2() {
		return phone2;
	}

	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	}

	public String getPhone3() {
		return phone3;
	}

	public void setPhone3(String phone3) {
		this.phone3 = phone3;
	}

	public int getMemlevel() {
		return memlevel;
	}

	public void setMemlevel(int memlevel) {
		this.memlevel = memlevel;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getOldphoto() {
		return oldphoto;
	}

	public void setOldphoto(String oldphoto) {
		this.oldphoto = oldphoto;
	}

	public String getOldpassword() {
		return oldpassword;
	}

	public void setOldpassword(String oldpassword) {
		this.oldpassword = oldpassword;
	}
	
}

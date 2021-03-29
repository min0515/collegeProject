package com.kakao.kitkat.entities;

import org.springframework.stereotype.Component;

@Component
public class Tb_registration {
	private String preregistrationyn;
	private String registrationyn;

	public String getPreregistrationyn() {
		return preregistrationyn;
	}

	public void setPreregistrationyn(String preregistrationyn) {
		this.preregistrationyn = preregistrationyn;
	}

	public String getRegistrationyn() {
		return registrationyn;
	}

	public void setRegistrationyn(String registrationyn) {
		this.registrationyn = registrationyn;
	}

}

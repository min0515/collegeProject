package com.kakao.kitkat.entities;

import org.springframework.stereotype.Component;

@Component
public class Tb_reservation {
	private int room_no;
	private int seq;
	private String room_name;
	private String reservationYn;
	private String student_no;

	public int getRoom_no() {
		return room_no;
	}

	public void setRoom_no(int room_no) {
		this.room_no = room_no;
	}

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public String getRoom_name() {
		return room_name;
	}

	public void setRoom_name(String room_name) {
		this.room_name = room_name;
	}

	public String getReservationYn() {
		return reservationYn;
	}

	public void setReservationYn(String reservationYn) {
		this.reservationYn = reservationYn;
	}

	public String getStudent_no() {
		return student_no;
	}

	public void setStudent_no(String student_no) {
		this.student_no = student_no;
	}

}

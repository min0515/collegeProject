package com.kakao.kitkat.dao;

import java.util.ArrayList;

import com.kakao.kitkat.entities.Tb_reservation;

public interface ReservationDao {
	public ArrayList<Tb_reservation> selectSitList(int room_no);

	public int updateRow(Tb_reservation tb_reservation) throws Exception;

	public int alreadySelectOne(String student_no) throws Exception;

	public ArrayList<Tb_reservation> myReservationList(String student_no);

	public int reservationCancelUpdateRow(Tb_reservation tb_reservation) throws Exception;

	public ArrayList<Tb_reservation> spaceReservationAlreadyList() throws Exception;

	public int spaceReservationUpdate(Tb_reservation tb_reservation) throws Exception;

	public int alreadySpaceSelectOne(String student_no) throws Exception;

	public int MyReservationCountSelectOne(String student_no) throws Exception;
}

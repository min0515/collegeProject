package com.kakao.kitkat;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kakao.kitkat.dao.ReservationDao;
import com.kakao.kitkat.entities.Tb_reservation;

@Controller
public class ReservationController {

	@Autowired
	private SqlSession sqlSession;
	@Autowired
	Tb_reservation tb_reservation;

	@RequestMapping(value = "/reservationPageGo", method = RequestMethod.GET)
	public String reservationPageGo(Model model) throws Exception {
		ReservationDao dao = sqlSession.getMapper(ReservationDao.class);
		ArrayList<Tb_reservation> seqs = dao.selectSitList(1);
		model.addAttribute("seqs", seqs);
		return "reservation/reservation";
	}

	@RequestMapping(value = "/reservationAjax", method = RequestMethod.POST)
	@ResponseBody
	public String reservationAjax(@RequestParam int seq, @RequestParam int room_no, HttpSession session)
			throws Exception {
		ReservationDao dao = sqlSession.getMapper(ReservationDao.class);
		String student_no = (String) session.getAttribute("sessionMember_id");
		if (student_no == null) {
			return "login";
		} else if (dao.alreadySelectOne(student_no) > 0) {
			return "already";
		} else {
			tb_reservation.setRoom_no(room_no);
			tb_reservation.setSeq(seq);
			tb_reservation.setStudent_no(student_no);
			dao.updateRow(tb_reservation);
			return "y";
		}
	}

	@RequestMapping(value = "/reservationView")
	public String reservationView(@RequestParam int room_no, Model model) throws Exception {
		ReservationDao dao = sqlSession.getMapper(ReservationDao.class);
		ArrayList<Tb_reservation> seqs = dao.selectSitList(room_no);
		model.addAttribute("seqs", seqs);
		return "reservation/reservation :: #reservationColumn";
	}

	@RequestMapping(value = "/reservationView2")
	public String reservationView2(@RequestParam int room_no, Model model) throws Exception {
		ReservationDao dao = sqlSession.getMapper(ReservationDao.class);
		ArrayList<Tb_reservation> seqs = dao.selectSitList(room_no);
		model.addAttribute("seqs", seqs);
		return "reservation/reservation :: #reservationColumn";
	}

	@RequestMapping(value = "/reservationView3")
	public String reservationView3(@RequestParam int room_no, Model model) throws Exception {
		ReservationDao dao = sqlSession.getMapper(ReservationDao.class);
		ArrayList<Tb_reservation> seqs = dao.selectSitList(room_no);
		model.addAttribute("seqs", seqs);
		return "reservation/reservation :: #reservationColumn";
	}

	@RequestMapping(value = "/myReservationPageGo", method = RequestMethod.GET)
	public String myReservationPageGo(Model model, HttpSession session) throws Exception {
		ReservationDao dao = sqlSession.getMapper(ReservationDao.class);
		String student_no = (String) session.getAttribute("sessionMember_id");
		if (student_no == null) {
			return "login/login";
		} else {
			ArrayList<Tb_reservation> myReservation = dao.myReservationList(student_no);
			model.addAttribute("myReservations", myReservation);
			int myReservationCount = dao.MyReservationCountSelectOne(student_no);
			model.addAttribute("myReservationCount", myReservationCount);
		}
		return "reservation/myReservation";
	}

	@RequestMapping(value = "/reservationDeleteAjax", method = RequestMethod.POST)
	@ResponseBody
	public String reservationDeleteAjax(@RequestParam int seq, @RequestParam int room_no, HttpSession session)
			throws Exception {
		ReservationDao dao = sqlSession.getMapper(ReservationDao.class);
		tb_reservation.setRoom_no(room_no);
		tb_reservation.setSeq(seq);
		dao.reservationCancelUpdateRow(tb_reservation);
		return "y";
	}

	@RequestMapping(value = "/spaceReservationPageGo", method = RequestMethod.GET)
	public String spaceReservationPageGo(Model model, HttpSession session) throws Exception {
		ReservationDao dao = sqlSession.getMapper(ReservationDao.class);
		ArrayList<Tb_reservation> AllList = dao.spaceReservationAlreadyList();
		String[] YnList = new String[AllList.size()];
		int a = 0;
		for (Tb_reservation List : AllList) {
			YnList[a] = List.getReservationYn();
			a++;
		}
		model.addAttribute("YnList", YnList);
		return "reservation/spaceReservation";
	}

	@RequestMapping(value = "/spaceReservationAjax", method = RequestMethod.POST)
	@ResponseBody
	public String spaceReservationAjax(@RequestParam int room_no, HttpSession session) throws Exception {
		ReservationDao dao = sqlSession.getMapper(ReservationDao.class);
		String student_no = (String) session.getAttribute("sessionMember_id");
		System.out.println("dlal");
		if (student_no == null) {
			return "login";
		} else if (dao.alreadySpaceSelectOne(student_no) > 0) {
			return "already";
		} else {
			tb_reservation.setRoom_no(room_no);
			tb_reservation.setStudent_no(student_no);
			dao.spaceReservationUpdate(tb_reservation);
			return "y";
		}

	}

}

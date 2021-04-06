package com.kakao.kitkat;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.kakao.kitkat.dao.BoardDao;
import com.kakao.kitkat.dao.GoodsDao;
import com.kakao.kitkat.dao.OrdersDao;
import com.kakao.kitkat.entities.Board;
import com.kakao.kitkat.entities.CartList;
import com.kakao.kitkat.entities.Goods;
import com.kakao.kitkat.entities.GoodsPaging;
import com.kakao.kitkat.entities.Goods_info;
import com.kakao.kitkat.entities.Orders;
import com.kakao.kitkat.entities.Tb_cart;

@Controller
public class GoodsController {

	@Autowired
	private SqlSession sqlSession;
	@Autowired
	Goods goods;
	@Autowired
	Tb_cart tb_cart;
	@Autowired
	Orders orders;
	@Autowired
	GoodsPaging goodspaging;
	static String find;
	@Autowired
	Goods_info goods_info;

	ArrayList<byte[]> attachs = new ArrayList<byte[]>();

	@RequestMapping(value = "/goodsWriteAttach", method = RequestMethod.POST)
	@ResponseBody
	public String goodsWriteAttach(Map<String, MultipartFile> formData, MultipartHttpServletRequest mpRequest)
			throws Exception {
		Map<String, MultipartFile> files = mpRequest.getFileMap();
		attachs.add(files.get("file").getBytes());
		return "filename";
	}

	@RequestMapping(value = "/goodsWriteSave", method = RequestMethod.POST)
	public String goodsWriteSave(@ModelAttribute Goods_info goods_info, @ModelAttribute Goods goods) throws Exception {
		String path = "C:/Users/IT-5C/git/collegeProject/src/main/resources/static/uploadattachs/";
		GoodsDao dao = sqlSession.getMapper(GoodsDao.class);
		dao.goodsInsertRow(goods);
		int a = 0;
		for (byte[] attach : attachs) {
			String realpath = "uploadattachs/"; // server path
			int g_seq = dao.goodsSelectG_seqOne();
			String filename = String.valueOf(g_seq + "-" + String.valueOf(a) + ".png");
			if (!filename.equals("")) {
				byte bytes[] = attach;
				goods_info.setG_seq(g_seq);
				goods_info.setG_attach(realpath + filename);
				dao.goodsInfoInsertRow(goods_info);
				try {
					BufferedOutputStream output = new BufferedOutputStream(new FileOutputStream(path + filename));
					output.write(bytes);
					output.flush();
					output.close();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}

			}
			a++;
		}
		return "index";
	}

	@RequestMapping(value = "/qnaBoardWriteSave", method = RequestMethod.POST)
	public String qnaBoardWriteSave(Model model, @ModelAttribute Board board,
			@RequestParam("b_attachfile") MultipartFile b_attachfile, HttpServletRequest request) throws Exception {
		String filename = b_attachfile.getOriginalFilename();
		String path = "F:/SPRINGBOOTSOURCE/eyeconspringboot (1)/src/main/resources/static/uploadattachs/";
		String realpath = "/uploadattachs/";
		if (!filename.equals("")) {
			byte bytes[] = b_attachfile.getBytes();
			try {
				BufferedOutputStream output = new BufferedOutputStream(new FileOutputStream(path + filename));
				output.write(bytes);
				output.flush();
				output.close();
				board.setB_attach(realpath + filename);

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		SimpleDateFormat df = new SimpleDateFormat("yyyy년 MM월 dd일 hh시 mm:ss");
		Date date = new Date();
		String today = df.format(date);
		board.setB_inputtime(today);
		BoardDao dao = sqlSession.getMapper(BoardDao.class);
		dao.insertRow(board);

		return "goods/board_qna";
	}

	@RequestMapping(value = "/qnaWrite", method = RequestMethod.GET)
	public String boardWrite(Locale locale, Model model) {
		return "goods/qna_write";
	}

	@RequestMapping(value = "/shoppingAboutus", method = RequestMethod.GET)
	public String shoppingAboutus(Model model) throws Exception {
		return "goods/shopping_aboutus";
	}

	@RequestMapping(value = "/boardReview", method = RequestMethod.GET)
	public String boardReview(Model model) throws Exception {
		return "goods/board_review";
	}

	@RequestMapping(value = "/boardQna", method = RequestMethod.GET)
	public String boardQna(Model model) throws Exception {
		return "goods/board_qna";
	}

	@RequestMapping(value = "/manageCancel", method = RequestMethod.GET)
	public String manageCancel(Model model) throws Exception {
		return "goods/manage_cancel";
	}

	@RequestMapping(value = "/manageGoodsList", method = RequestMethod.GET)
	public String manageGoodsList(Model model) throws Exception {
		GoodsDao dao = sqlSession.getMapper(GoodsDao.class);

		int rowcount = dao.selectCountFirst();
		int pagesize = 10;
		int page = 1;
		int startrow = (page - 1) * pagesize;
		int endrow = 10;
		if (goodspaging.getFind() == null) {
			goodspaging.setFind("");
		}
		goodspaging.setStartrow(startrow);
		goodspaging.setEndrow(endrow);
		int absPage = 1;
		if (rowcount % pagesize == 0) {
			absPage = 0;
		}

		int pagecount = rowcount / pagesize + absPage;
		int pages[] = new int[pagecount];
		for (int i = 0; i < pagecount; i++) {
			pages[i] = i + 1;
		}

		ArrayList<Goods> goodses = dao.goodsSelectPageList(goodspaging);

		model.addAttribute("goodses", goodses);
		model.addAttribute("pages", pages);
		return "goods/manage_goods";
	}

	@RequestMapping(value = "/managePageSelect", method = RequestMethod.GET)
	public String managePageSelect(Model model, @RequestParam int page) throws Exception {
		GoodsDao dao = sqlSession.getMapper(GoodsDao.class);

		int pagesize = 10;
		int startrow = (page - 1) * pagesize;
		int endrow = 10;

		goodspaging.setFind(find);
		if (goodspaging.getFind() == null) {
			goodspaging.setFind("");
		}
		goodspaging.setStartrow(startrow);
		goodspaging.setEndrow(endrow);
		int rowcount = dao.goodsSelectCount(goodspaging);
		int absPage = 1;
		if (rowcount % pagesize == 0) {
			absPage = 0;
		}

		int pagecount = rowcount / pagesize + absPage;
		int pages[] = new int[pagecount];
		for (int i = 0; i < pagecount; i++) {
			pages[i] = i + 1;
		}
		ArrayList<Goods> goodses = dao.goodsSelectPageList(goodspaging);
		model.addAttribute("goodses", goodses);
		model.addAttribute("pages", pages);
		model.addAttribute("find", find);
		return "goods/manage_goods";
	}

	@RequestMapping(value = "/findListmanage", method = RequestMethod.GET)
	public String findListmanage(Model model, @RequestParam String find) throws Exception {
		GoodsDao dao = sqlSession.getMapper(GoodsDao.class);

		int pagesize = 10;
		int page = 1;
		int startrow = (page - 1) * pagesize;
		int endrow = 10;

		goodspaging.setFind(find);
		if (goodspaging.getFind() == null) {
			goodspaging.setFind("");
		}
		goodspaging.setStartrow(startrow);
		goodspaging.setEndrow(endrow);
		int rowcount = dao.goodsSelectCount(goodspaging);
		int absPage = 1;
		if (rowcount % pagesize == 0) {
			absPage = 0;
		}

		int pagecount = rowcount / pagesize + absPage;
		int pages[] = new int[pagecount];
		for (int i = 0; i < pagecount; i++) {
			pages[i] = i + 1;
		}

		ArrayList<Goods> goodses = dao.goodsSelectPageList(goodspaging);
		model.addAttribute("goodses", goodses);
		model.addAttribute("pages", pages);
		model.addAttribute("find", find);

		return "goods/manage_goods";
	}

	@RequestMapping(value = "/manageOrder", method = RequestMethod.GET)
	public String manageOrder(Model model) throws Exception {
		return "goods/manage_order";
	}

	@RequestMapping(value = "/myPage", method = RequestMethod.GET)
	public String myPage(Model model) throws Exception {
		return "goods/mypage";
	}

	@RequestMapping(value = "/myPageOrderList", method = RequestMethod.GET)
	public String myPageOrderList(Model model) throws Exception {
		return "goods/mypage_order_list";
	}

	@RequestMapping(value = "/goodsPageList", method = RequestMethod.GET)
	public String goodsPageList(Locale locale, Model model) throws Exception {
		GoodsDao dao = sqlSession.getMapper(GoodsDao.class);
		int pagesize = 10;
		int page = 1;
		int startrow = (page - 1) * pagesize;
		int endrow = 10;

		goodspaging.setFind(this.find);
		if (goodspaging.getFind() == null) {
			goodspaging.setFind("");
		}

		goodspaging.setStartrow(startrow);
		goodspaging.setEndrow(endrow);
		int rowcount = dao.goodsSelectCountFirst(goodspaging);
		int absPage = 1;

		if (rowcount % pagesize == 0) {
			absPage = 0;
		}
		int pagecount = rowcount / pagesize + absPage;
		int pages[] = new int[pagecount];
		for (int i = 0; i < pagecount; i++) {
			pages[i] = i + 1;
		}
		ArrayList<Goods> goodses = dao.goodsSelectPageList(goodspaging);
		model.addAttribute("goodses", goodses);
		model.addAttribute("pages", pages);
		return "goods/goods_page_list";
	}

	@RequestMapping(value = "/onePaymentGo", method = RequestMethod.POST)
	public String goodsDelivery(Model model, @RequestParam int g_seq, @RequestParam int qty, HttpSession session)
			throws Exception {
		GoodsDao dao = sqlSession.getMapper(GoodsDao.class);
		goods = dao.goodsSelectOne(g_seq);
		goods.setG_qty(qty);
		model.addAttribute("goods", goods);
		return "goods/payment";
	}

	@RequestMapping(value = "/goodsDetail2", method = RequestMethod.GET)
	public String goodsDetail2(Model model, @RequestParam int g_seq, HttpSession session) throws Exception {
		GoodsDao dao = sqlSession.getMapper(GoodsDao.class);
		goods = dao.goodsAllSelectOne(g_seq);
		ArrayList<Goods_info> attachs = dao.goodsInfoAllSelectOne(g_seq);
		model.addAttribute("goods", goods);
		model.addAttribute("attachs", attachs);
		return "goods/goods_detail2";
	}

	@RequestMapping(value = "/goodsWrite", method = RequestMethod.GET)
	public String goodsWrite(Locale locale, Model model) {
		return "goods/goods_write";
	}

	@RequestMapping(value = "/goodsMyList", method = RequestMethod.GET)
	public String goodsMyList(Model model, HttpSession session) throws Exception {
		String member_id = (String) session.getAttribute("sessionMember_id");
		GoodsDao dao = sqlSession.getMapper(GoodsDao.class);
		OrdersDao dao2 = sqlSession.getMapper(OrdersDao.class);
		int sum = dao2.myOrdersqtySum(member_id);
		int totalsum = dao2.myOrderstotalSum(member_id);
		int mycartcount = dao.myGoodsCartCount(member_id);
		ArrayList<CartList> selectproyo = dao2.selectproyo(member_id);
		Tb_cart cartprice = dao.myGoodsCartCheckedSelect(member_id);
		model.addAttribute("cartprice", cartprice);
		model.addAttribute("cartyb", selectproyo);
		model.addAttribute("mycartcount", mycartcount);
		model.addAttribute("sum", sum);
		model.addAttribute("sumtotal", totalsum);
		return "goods/goods_mylist";
	}

	/* 장바구니 담기 버튼 */
	@RequestMapping(value = "/goodsCartIn", method = RequestMethod.POST)
	@ResponseBody
	public String goodsCartIn(Model model, Tb_cart tb_cart, HttpSession session) throws Exception {
		GoodsDao dao = sqlSession.getMapper(GoodsDao.class);
		String member_id = (String) session.getAttribute("sessionMember_id");

		if (member_id == null) {
			return "login/login2";
		} else {
			tb_cart.setMember_id(member_id);
			tb_cart.setTotalprice(tb_cart.getG_price() * tb_cart.getQty());
			try {
				dao.cartInsertRow(tb_cart);
				return "y";
			} catch (Exception e) {
				dao.cartUpdateRow(tb_cart);
				return "y";
			}

		}

	}

	@RequestMapping(value = "/goodsCartGo", method = RequestMethod.GET)
	public String goodsCartGo(Model model, HttpSession session) throws Exception {
		GoodsDao dao = sqlSession.getMapper(GoodsDao.class);
		String member_id = (String) session.getAttribute("sessionMember_id");
		if (member_id == null) {
			return "login/login2";
		} else {
			int mycartcount = dao.myGoodsCartCount(member_id);
			ArrayList<Tb_cart> myProducts = dao.myGoodsCartSelect(member_id);
			Tb_cart cartprice = dao.myGoodsCartCheckedSelect(member_id);
			model.addAttribute("mycartcount", mycartcount);
			model.addAttribute("myProducts", myProducts);
			if (cartprice == null) {
				tb_cart.setDeliveryTotalPrice(0);
				tb_cart.setTotalprice(0);
				model.addAttribute("cartprice", tb_cart);
			} else {
				model.addAttribute("cartprice", cartprice);

			}
			return "goods/goods_cart";
		}

	}

	@RequestMapping(value = "/cartPaymentGo", method = RequestMethod.GET)
	public String cartPaymentGo(Locale locale, Model model, HttpSession session) throws Exception {
		GoodsDao dao = sqlSession.getMapper(GoodsDao.class);
		String member_id = (String) session.getAttribute("sessionMember_id");
		if (member_id == null) {
			return "login/login2";
		} else {
			ArrayList<Tb_cart> cartPayments = dao.myGoodsCartCheckedPaymentSelect(member_id);
			model.addAttribute("cartPayments", cartPayments);
			Tb_cart cartprice = dao.myGoodsCartCheckedSelect(member_id);
			model.addAttribute("cartprice", cartprice);
			return "goods/payment_cart";
		}
	}

	@RequestMapping(value = "/paymentComplete", method = RequestMethod.POST)
	public String paymentComplete(Model model, @ModelAttribute Orders orders, @RequestParam int g_seq,
			@RequestParam String orders_address1, @RequestParam String orders_address2, HttpSession session)
			throws Exception {
		GoodsDao dao = sqlSession.getMapper(GoodsDao.class);
		OrdersDao dao2 = sqlSession.getMapper(OrdersDao.class);
		String member_id = (String) session.getAttribute("sessionMember_id");
		if (member_id == null) {
			return "login/login2";
		} else {
			orders.setMember_id(member_id);
			orders.setOrders_address(orders_address1 + "-" + orders_address2);
			String pattern = "yyyyMMdd";
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
			String date = simpleDateFormat.format(new Date());
			orders.setOrders_date(date);
			dao2.OrderssAdd(orders);
			model.addAttribute("ord", orders);
			return "goods/payment_complete";
		}

	}

	@RequestMapping(value = "/cartPaymentGoYo", method = RequestMethod.POST)
	public String cartPaymentGoYo(Model model, @ModelAttribute Orders orders, @RequestParam int totalprice,
			@RequestParam String orders_address1, @RequestParam String orders_address2, HttpSession session)
			throws Exception {
		GoodsDao dao = sqlSession.getMapper(GoodsDao.class);
		OrdersDao dao2 = sqlSession.getMapper(OrdersDao.class);
		String member_id = (String) session.getAttribute("sessionMember_id");
		if (member_id == null) {
			return "login/login2";
		} else {
			orders.setOrders_address(orders_address1 + "-" + orders_address2);
			String pattern = "yyyyMMdd";
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
			String date = simpleDateFormat.format(new Date());
			orders.setMember_id(member_id);
			orders.setOrders_date(date);
			ArrayList<Tb_cart> cartyb = dao.cartyb(member_id);
			String dateG_seq = date;
			for (Tb_cart tb_cart : cartyb) {
				tb_cart.setOrders_date(date);
				dao2.OrderssAdd2(tb_cart);
				dao2.ordersUpdateInsert(orders);
				dateG_seq = dateG_seq + tb_cart.getG_seq();
			}
			model.addAttribute("dateG_seq", dateG_seq);
			model.addAttribute("totalprice", totalprice);
			return "goods/payment_cartcomplete";
		}
	}

	@RequestMapping(value = "/cartProductCheckYnAjax", method = RequestMethod.POST)
	@ResponseBody
	public String cartProductCheckYnAjax(Model model, @RequestParam int g_seq, HttpSession session) throws Exception {
		GoodsDao dao = sqlSession.getMapper(GoodsDao.class);
		String member_id = (String) session.getAttribute("sessionMember_id");
		tb_cart.setMember_id(member_id);
		tb_cart.setG_seq(g_seq);
		tb_cart.setCheckYn("y");
		if (member_id == null) {
			return "login";
		} else {
			dao.cartCheckUpdateRow(tb_cart);
			return "y";

		}

	}

	@RequestMapping(value = "/cartProductNonCheckYnAjax", method = RequestMethod.POST)
	@ResponseBody
	public String cartProductNonCheckYnAjax(Model model, @RequestParam int g_seq, HttpSession session)
			throws Exception {
		GoodsDao dao = sqlSession.getMapper(GoodsDao.class);
		String member_id = (String) session.getAttribute("sessionMember_id");
		tb_cart.setMember_id(member_id);
		tb_cart.setG_seq(g_seq);
		tb_cart.setCheckYn("n");
		if (member_id == null) {
			return "login";
		} else {
			dao.cartCheckUpdateRow(tb_cart);
			return "y";

		}

	}

	@RequestMapping(value = "/ProductDeleteAjax", method = RequestMethod.POST)
	@ResponseBody
	public String ProductDeleteAjax(@RequestParam int g_seq, HttpSession session) throws Exception {
		GoodsDao dao = sqlSession.getMapper(GoodsDao.class);
		String member_id = (String) session.getAttribute("sessionMember_id");
		tb_cart.setMember_id(member_id);
		tb_cart.setG_seq(g_seq);
		int result = dao.ProductDeleteAjax(tb_cart);
		if (result > 0) {
			return "y";

		} else {
			return "n";
		}
	}

	@RequestMapping(value = "/cartProductQtyAjax", method = RequestMethod.POST)
	@ResponseBody
	public String cartProductQtyAjax(Model model, @RequestParam int g_seq, @RequestParam int qty, HttpSession session)
			throws Exception {
		GoodsDao dao = sqlSession.getMapper(GoodsDao.class);
		String member_id = (String) session.getAttribute("sessionMember_id");
		tb_cart.setMember_id(member_id);
		tb_cart.setG_seq(g_seq);
		tb_cart.setQty(qty);
		if (member_id == null) {
			return "login";
		} else {
			dao.cartQtyUpdateRow(tb_cart);
			return "y";

		}

	}

	@RequestMapping(value = "/cartProductDownQtyAjax", method = RequestMethod.POST)
	@ResponseBody
	public String cartProductDownQtyAjax(Model model, @RequestParam int g_seq, HttpSession session) throws Exception {
		GoodsDao dao = sqlSession.getMapper(GoodsDao.class);
		String member_id = (String) session.getAttribute("sessionMember_id");
		tb_cart.setMember_id(member_id);
		tb_cart.setG_seq(g_seq);
		tb_cart.setCheckYn("n");
		if (member_id == null) {
			return "login";
		} else {
			dao.cartCheckUpdateRow(tb_cart);
			return "y";

		}

	}

}
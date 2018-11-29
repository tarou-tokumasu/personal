package sv;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.BuyDetailBeans;
import beans.ItemBeans;
import beans.UserBeans;
import dao.BuyDAO;
import dao.BuyDetailDAO;
import dao.UserDAO;

/**
 * Servlet implementation class Bought
 */
@WebServlet("/Bought")
public class Bought extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Bought() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//購入確定　DBに登録する

		//登録に必要なデータ取り出す
		HttpSession se = request.getSession();
		//カートの中身
		List<ItemBeans> cart = (List<ItemBeans>) se.getAttribute("cart");
		//配送方法、値段
		DeliBeans deli = (DeliBeans) se.getAttribute("deli");
		//総額
		int total2 = (int) se.getAttribute("total1");
		//ユーザー情報
		UserBeans us = (UserBeans) se.getAttribute("userInfo");
		//時間
		Date date = new Date();
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//使用したポイント
		int point = (int) se.getAttribute("point");
		//生の整数 or 0

		int point2 = (point>0) ? //使ったかどうか
				point*=-1 ://使ったらマイナス数字にする ex:-200
				total2/100;//使わなかったら1%還元 ex:41

		System.out.println(point2);

		//購入データ登録　購入IDも控える（でないと購入商品登録でどのタイミングで買ったかわからなくなる）
		//追加　ポイント使った場合（ポイント減らす）　ポイント使ってない場合（ポイント付与する）
		int buy_id = BuyDAO.addBuy(deli , total2 , us , sdf1.format(date) , point2);

		//ポイントを更新する
		UserDAO.updatePoint(us.getId(), point2);

		//購入した商品登録　カートの回数分ループ
		for(ItemBeans c : cart) {
			BuyDetailBeans bd = new BuyDetailBeans();
			bd.setItem_id(c.getId());
			bd.setBuy_id(buy_id);
			bd.setLast_price(c.getitem_pricez());
			BuyDetailDAO.addDetail(bd);
		}
		//セッションから色々消す
		se.removeAttribute("cart");
		se.removeAttribute("total1");
		se.removeAttribute("total2");
		se.removeAttribute("deli");

		//リダレ

		se.setAttribute("point2", point2);
		response.sendRedirect("BuyResult");
	}

}

package sv;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.ItemBeans;
import beans.UserBeans;
import dao.DeliDAO;

/**
 * Servlet implementation class Cart
 */
@WebServlet("/Cart")
public class Cart extends HttpServlet {
	private static final long serialVersionUID = 1L;



	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		List<DeliBeans> deliList =  DeliDAO.getALLDeli();
		HttpSession se = request.getSession();

		//カートの中身全部足したい
		ArrayList<ItemBeans> IL = (ArrayList<ItemBeans>) se.getAttribute("cart");
		System.out.println("IL :" + IL);


		//中身入ってる時
		if(IL !=null) {
			if(IL.isEmpty()) {
				request.setAttribute("items", "0");
			}
			else {
		int total = sc.getTotal(IL);
		se.setAttribute("total", total);
		request.setAttribute("items", "1");
	    }
		}

		else {
		//中身入ってない時
		request.setAttribute("items", "0");
		}

		se.setAttribute("deliList", deliList);
		request.getRequestDispatcher(sc.CART).forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		String[] dList = request.getParameterValues("delete");

		//この配列には商品id入ってる
		HttpSession se = request.getSession();

		ArrayList<ItemBeans> cart = (ArrayList<ItemBeans>) se.getAttribute("cart");


		if(request.getParameter("action").equals("doDel")) {

		if(dList!=null) {
		for(String dID : dList) {//削除したい配列ぶんループ
			for(ItemBeans i : cart ) {//カートの中身ぶんループ
				if(i.getId() == Integer.parseInt(dID)) {
					cart.remove(i);
					break;//削除完了したらもう残りを探す必要がないので
				}
			}
		}
		request.setAttribute("notice", "選択した商品を削除しました");
		if(cart.isEmpty()) {
			request.setAttribute("items", "0");
		}else {
			request.setAttribute("items", "1");
		}
		se.setAttribute("cart", cart);

		}

			else {
		request.setAttribute("Err", "商品が選択されていません");
				}

		doGet(request,response);
		}

		if(request.getParameter("action").equals("doRegi")) {

			String deli = request.getParameter("deli");

			//jspのほうで表示させてない時はnullで表示させた上で空欄だと""になる
			String poin = request.getParameter("point");

			if(poin != null && !(poin.equals(""))) {
			//自分の手持ちオーバーしてるかチェック
			UserBeans userInfo = (UserBeans) se.getAttribute("userInfo");
			int point =Integer.parseInt(poin);
			int mypoint = userInfo.getPoint();

			if(point>mypoint) {
				request.setAttribute("Err", "手持ちのポイントをオーバーしています");
				doGet(request, response);
			}
			else {
			se.setAttribute("point", point);
			}
			}
			else {
			se.setAttribute("point", 0);
			}

			DeliBeans deliB = DeliDAO.getDeli(deli);

			se.setAttribute("deli", deliB);

			response.sendRedirect("Regi");
		}

	}

}

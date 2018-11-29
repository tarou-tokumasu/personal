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
import dao.DeliDAO;
import dao.ItemDAO;

/**
 * Servlet implementation class AddCart
 */
@WebServlet("/AddCart")
public class AddCart extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession se = request.getSession();
		String idd = request.getParameter("id");


		ItemDAO id = new ItemDAO();

		//DAOのメソッドをstatic化する事でDAO宣言略せる
		ItemBeans item = id.searchID(idd);
		//これはtryで囲っておいた方がいい？
		ArrayList<ItemBeans> cart = (ArrayList<ItemBeans>) se.getAttribute("cart");

		//カートがないなら作る
		if(cart==null) {
			cart = new  ArrayList<ItemBeans>();
		}

		//商品追加・保存
		cart.add(item);
		se.setAttribute("cart", cart);

		List<DeliBeans> deliList =  DeliDAO.getALLDeli();
		se.setAttribute("deliList", deliList);

		response.sendRedirect("Cart");

		//TODO:マイリストに入ってる商品をマイリストからではなく商品ぺージからカートに追加した時マイリストのデータを消す（わかりづらい）

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

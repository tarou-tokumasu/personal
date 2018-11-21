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
		int total = sc.getTotal(IL);

		se.setAttribute("total", total);
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


		}

			else {
		request.setAttribute("Err", "商品が選択されていません");
				}

		request.getRequestDispatcher(sc.CART).forward(request, response);
		}

		if(request.getParameter("action").equals("doRegi")) {

			String deli = request.getParameter("deli");
			System.out.println(deli);

			DeliBeans deliB = DeliDAO.getDeli(deli);

			se.setAttribute("deli", deliB);

			response.sendRedirect("Regi");
		}

	}

}

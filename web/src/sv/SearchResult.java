package sv;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.ItemBeans;

/**
 * Servlet implementation class SearchResult
 */
@WebServlet("/SearchResult")
public class SearchResult extends HttpServlet {
	private static final long serialVersionUID = 1L;


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//多すぎる商品をページ別に分けるやつ

		//結果リスト引っ張り出す
		HttpSession se = request.getSession();
		List<ItemBeans> il =(List<ItemBeans>) se.getAttribute("searchList");

		//

		//今何ページ目かをURLから引っ張り出す
		//拡張if　条件式 ? true : false ; pageに入ってなかったら1ページ目　入ってたらそのページ数
		int page = Integer.parseInt(request.getParameter("page") == null ? "1": request.getParameter("page"));

		//商品数のカウント
		double size= il.size();

		//何ページ必要か　ceilは切り上げ
		int max = (int)Math.ceil(size / sc.ITEMS);


		//今いるページ数によって検索結果が入った配列を表示させる　1ページ目[0]～[4]  2ページ目[5]～[9]…みたいに

		request.setAttribute("items", sc.ITEMS);
		request.setAttribute("max", max);
		request.setAttribute("page", page);

		request.getRequestDispatcher(sc.SEARCH).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

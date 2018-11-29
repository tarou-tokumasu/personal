package sv;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.BuyDataBeans;
import beans.UserBeans;
import dao.BuyDAO;
import dao.UserDAO;

/**
 * Servlet implementation class UUserDetail
 */
@WebServlet("/UUserDetail")
public class UUserDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;



	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//ログインチェック
		HttpSession se = request.getSession();
		UserBeans user =(UserBeans) se.getAttribute("userInfo");
		String id = request.getParameter("id");

		//TODO:今はログインセッションに入ってるユーザーデータ取ってるが管理画面から来た時はそのユーザーの情報を投げる
		//管理者チェック
		if(user!=null && user.getId()==1 && id!=null) {
			UserBeans u = UserDAO.searchID(id);
			se.setAttribute("user2", u);

			List<BuyDataBeans> bd =  BuyDAO.searchMyBuy(u.getId());
			se.setAttribute("myDB", bd);

			//今何ページ目かをURLから引っ張り出す
			int page = Integer.parseInt(request.getParameter("page") == null ? "1": request.getParameter("page"));

			//履歴いくつある？
			double size = bd.size();

			//いくつページいる？
			int max = (int)Math.ceil(size / sc.ITEMS);

			request.setAttribute("items", sc.ITEMS);
			request.setAttribute("max", max);
			request.setAttribute("page", page);


		request.getRequestDispatcher(sc.USER_DETAIL).forward(request, response);
		}

		else if(user==null) {
			response.sendRedirect("Login");
		}else {


		//ユーザーIDを基に購入履歴出す
		List<BuyDataBeans> bd =  BuyDAO.searchMyBuy(user.getId());
		se.setAttribute("myDB", bd);

		//今何ページ目かをURLから引っ張り出す
		int page = Integer.parseInt(request.getParameter("page") == null ? "1": request.getParameter("page"));

		//履歴いくつある？
		double size = bd.size();

		//いくつページいる？
		int max = (int)Math.ceil(size / sc.ITEMS);

		request.setAttribute("items", sc.ITEMS);
		request.setAttribute("max", max);
		request.setAttribute("page", page);


	request.getRequestDispatcher(sc.USER_DETAIL).forward(request, response);
	}
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

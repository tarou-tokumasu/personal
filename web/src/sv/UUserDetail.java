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

		if(user==null) {
			response.sendRedirect("Login");
		}
		System.out.println(user.getId());

		//ユーザーIDを基に購入履歴出す
		List<BuyDataBeans> bd =  BuyDAO.searchMyBuy(user.getId());
		se.setAttribute("myDB", bd);

	request.getRequestDispatcher(sc.USER_DETAIL).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

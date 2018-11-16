package sv;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.ItemBeans;
import beans.UserBeans;
import dao.ItemDAO;

/**
 * Servlet implementation class ItemDetail
 */
@WebServlet("/ItemDetail")
public class ItemDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ItemDetail() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//管理者限定メニューチェッカー
				HttpSession se = request.getSession();
				UserBeans user =(UserBeans) se.getAttribute("userInfo");

				//そもそもログインしてない
				if(user==null) {
					response.sendRedirect("Login");
				}
				//adminかどうかチェック
				else if(user.getLogin_id().equals("admin")) {

					String idd =request.getParameter("id");
					//参照先のデータを参照
					ItemDAO id = new ItemDAO();

					ItemBeans i = id.searchID(idd);
					request.setAttribute("thisItem", i);
				request.getRequestDispatcher(sc.AD_ITEM_DETAIL).forward(request, response);}
				else {
					response.sendRedirect("index");
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

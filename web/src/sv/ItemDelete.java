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
 * Servlet implementation class ItemDelete
 */
@WebServlet("/ItemDelete")
public class ItemDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ItemDelete() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//idチェック
				String idd = request.getParameter("id");
				//管理者限定メニューチェッカー
				HttpSession se = request.getSession();
				UserBeans user =(UserBeans) se.getAttribute("userInfo");

				//そもそもログインしてない
				if(user==null) {
					response.sendRedirect("Login");
				}
				//adminかどうかチェック
				else if(user.getLogin_id().equals("admin")) {

					//ユーザー情報取得
					ItemDAO ud = new ItemDAO();

					ItemBeans u = ud.searchID(idd);
					request.setAttribute("thisItem", u);
				request.getRequestDispatcher(sc.AD_ITEM_DELETE).forward(request, response);}
				else {
					response.sendRedirect("index");
				}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//アイテム名を持ってきた
		String item_name = request.getParameter("item_name");
		ItemDAO id = new ItemDAO();
		id.deleteItem(item_name);
		response.sendRedirect("ItemList");
	}

}

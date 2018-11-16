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
import beans.UserBeans;
import dao.ItemDAO;

/**
 * Servlet implementation class ItemList
 */
@WebServlet("/ItemList")
public class ItemList extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ItemList() {
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

			//ユーザーリスト一覧を取得
			ItemDAO id = new ItemDAO();

			List<ItemBeans> itemList = id.getAllItem();
			request.setAttribute("itemList", itemList);
		request.getRequestDispatcher(sc.ITEM_LIST).forward(request, response);}
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

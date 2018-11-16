package sv;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.UserBeans;
import dao.UserDAO;

/**
 * Servlet implementation class UserDelete
 */
@WebServlet("/UserDelete")
public class UserDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserDelete() {
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
			UserDAO ud = new UserDAO();

			UserBeans u = ud.searchID(idd);
			request.setAttribute("thisUser", u);
		request.getRequestDispatcher(sc.AD_USER_DELETE).forward(request, response);}
		else {
			response.sendRedirect("index");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String login_ID = request.getParameter("login_ID");
		System.out.println(login_ID);
		UserDAO ud = new UserDAO();
		ud.deleteUser(login_ID);
		response.sendRedirect("UserList");
	}

}

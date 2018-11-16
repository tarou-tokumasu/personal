package sv;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.UserBeans;
import dao.UserDAO;

/**
 * Servlet implementation class UserUpdate
 */
@WebServlet("/UserUpdate")
public class UserUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserUpdate() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

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
		request.getRequestDispatcher(sc.AD_USER_UPDATE).forward(request, response);}
		else {
			response.sendRedirect("index");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String login_ID = request.getParameter("login_ID");
		String user_name =request.getParameter("user_name");
		String password = request.getParameter("password");
		String kakunin = request.getParameter("kakunin");
		String birth_date = request.getParameter("birth_date");
		String address = request.getParameter("address");
		Date update_date = new Date();
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		//DAO使う宣言
		UserDAO ud = new UserDAO();

		//パス確認
		if
		(ud.checkPass(password , kakunin)==false){//パスが違う（片方空欄）なら
			request.setAttribute("Err", "入力されたパスワードが異なっています");
			request.getRequestDispatcher(sc.AD_USER_UPDATE).forward(request, response);
		}
		else if(password!=null && kakunin!=null) { //中身が入ってるなら
		//パス更新
			ud.UpdatePassword(login_ID,password);
		}

		//ユーザー情報更新
		ud.UpdateUser(login_ID , user_name,birth_date,address,sdf1.format(update_date));
		response.sendRedirect("UserList");

	}

}

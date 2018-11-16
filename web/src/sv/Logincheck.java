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
 * Servlet implementation class Logincheck
 */
@WebServlet("/Logincheck")
public class Logincheck extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Logincheck() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String loginID =request.getParameter("loginID");
		String password = request.getParameter("password");

		//DBで照会
		UserDAO ud = new UserDAO();
		UserBeans u = ud.findLoginID(loginID, password);

		//セッション用意
		HttpSession se = request.getSession();

		//入ってないぞ
		if(u==null) {
			se.setAttribute("loginErr", "ユーザーIDかパスワードが間違っています");
			response.sendRedirect("Login");

		}
		//見つかったぞ
		else {

		//セッションに保存
		se.setAttribute("userInfo", u);
		se.removeAttribute("loginErr");
		response.sendRedirect("index");
	}
	}

}

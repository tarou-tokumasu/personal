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
 * Servlet implementation class index
 */
@WebServlet("/index")
public class index extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public index() {
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

		//入ってないぞ
		if(u==null) {
			System.out.println("dame");
			request.setAttribute("errMsg", "ログインに失敗しました。");
			request.getRequestDispatcher(sc.LOGIN).forward(request, response);

		}
		//見つかったぞ
		else {
			System.out.println("ok");

		//セッションに保存
		HttpSession se = request.getSession();
		se.setAttribute("UserInfo", u);

       request.getRequestDispatcher(sc.INDEX).forward(request, response);
	}

	}
}

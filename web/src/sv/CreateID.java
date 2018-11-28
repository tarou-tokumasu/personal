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
 * Servlet implementation class CreateID
 */
@WebServlet("/CreateID")
public class CreateID extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateID() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher(sc.NEWID).forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String login_ID = request.getParameter("login_ID");
		String user_name =request.getParameter("user_name");
		String password = sc.encrypt(request.getParameter("password"));
		String kakunin =  sc.encrypt(request.getParameter("kakunin"));
		String birth_date = request.getParameter("birth_date");
		String address = request.getParameter("address");
		Date create_date = new Date();
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


		//DAO使う宣言
		UserDAO ud = new UserDAO();

		//IDの重複確認
		if(ud.checkID(login_ID)==false) {
			request.setAttribute("Err", "入力したIDは使用されています");
			request.getRequestDispatcher(sc.NEWID).forward(request, response);
		}
		//パスワード確認
		else if(ud.checkPass(password , kakunin)==false){
			request.setAttribute("Err", "入力されたパスワードが異なっています");
			request.getRequestDispatcher(sc.NEWID).forward(request, response);
		}
		else{
		//DBに追加してログイン
			ud.addUser(login_ID,user_name,password,birth_date,address,sdf1.format(create_date));
			UserBeans user = ud.findLoginID(login_ID,password);
			HttpSession se = request.getSession();
			se.setAttribute("userInfo", user);
			response.sendRedirect("index");

		}


	}

}

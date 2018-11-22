package sv;

import java.io.IOException;
import java.text.NumberFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Regi
 */
@WebServlet("/Regi")
public class Regi extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Regi() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession se = request.getSession();

		//ログインしてなかったらログイン促す
		if(se.getAttribute("userInfo")==null) {
			//ログインした後にどこに飛ばすか
			se.setAttribute("returnURL", "Regi");
			//ログイン画面送り
			response.sendRedirect("Login");
		}
		else {
		//レジ画面に
		DeliBeans deli = (DeliBeans) se.getAttribute("deli");
		int delip = deli.getDeli_price();
		int total2 = (int) se.getAttribute("total") + delip;
		NumberFormat nf = NumberFormat.getCurrencyInstance();
		//カンマ付けてない総額が必要（db登録用）
		se.setAttribute("total1", total2);
		se.setAttribute("total2", nf.format(total2));

		request.getRequestDispatcher(sc.REGI).forward(request, response);
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

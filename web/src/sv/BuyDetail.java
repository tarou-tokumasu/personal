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
import beans.BuyDetailBeans;
import beans.UserBeans;
import dao.BuyDAO;
import dao.BuyDetailDAO;

/**
 * Servlet implementation class BuyDetail
 */
@WebServlet("/BuyDetail")
public class BuyDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public BuyDetail() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//自分のか管理者目線かの違いを
		HttpSession se = request.getSession();

		UserBeans user2 =(UserBeans) se.getAttribute("user2");
		UserBeans user =(UserBeans) se.getAttribute("userInfo");
		String id = request.getParameter("id");


		BuyDataBeans bd = (user2==null) ? BuyDAO.searchMyBuy(user.getId(),id):
			 BuyDAO.searchMyBuy(user2.getId(),id);

		List<BuyDetailBeans> bbd = BuyDetailDAO.getDetail(id);

		request.setAttribute("iDB", bd);
		request.setAttribute("BuyD", bbd);

		request.getRequestDispatcher(sc.BUY_DETAIL).forward(request, response);


	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

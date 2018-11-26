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
import beans.ReviewBeans;
import beans.UserBeans;
import dao.BuyDAO;
import dao.ItemDAO;
import dao.ReviewDAO;

/**
 * Servlet implementation class UItemDetail
 */
@WebServlet("/UItemDetail")
public class UItemDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UItemDetail() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession se = request.getSession();

		//データ貰う
		UserBeans user = (UserBeans) se.getAttribute("userInfo");
		String idd =request.getParameter("id");
		//参照先のデータを取得
		ItemDAO id = new ItemDAO();
		ItemBeans i = id.searchID(idd);
		request.setAttribute("thisItem", i);

		//レビューする権利持ってる？
		if(user!=null) {
		boolean rev = BuyDAO.checkBuy(user.getId() , idd);
		request.setAttribute("rev", rev);
		System.out.println("権利は" + rev);
		}

		//レビュー表示用
		List<ReviewBeans> revs = ReviewDAO.getReviewByID(idd);
		request.setAttribute("revs", revs);


		request.getRequestDispatcher(sc.ITEM_DETAIL).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

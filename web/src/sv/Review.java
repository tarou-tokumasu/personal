package sv;

import java.io.IOException;

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
 * Servlet implementation class Review
 */
@WebServlet("/Review")
public class Review extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Review() {
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
		if(user!=null && BuyDAO.checkBuy(user.getId() , idd)) {

		//レビュー書いてたらデフォルトに設置する
		ReviewBeans re = ReviewDAO.checkRev(user.getId() , idd);
		request.setAttribute("def", re);
		request.getRequestDispatcher(sc.ITEM_REVIEW).forward(request, response);
		}
		else {//持ってないなら追い返す
		response.sendRedirect("index");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {



		//フォームからデータ読み込み
		int vote =  Integer.parseInt(request.getParameter("vote"));
		String review = request.getParameter("review");
		String item_id = request.getParameter("item");
		int user_id = Integer.parseInt(request.getParameter("user"));

		ReviewBeans re = ReviewDAO.checkRev(user_id , item_id);
		System.out.println("レビューしてる？" + re);
		//レビュー経験済みかチェック
		if (re == null) {
		ReviewDAO.addReview(vote,item_id,user_id,review);
		request.setAttribute("msg", "レビューの投稿が完了しました");
		}
		else {
		ReviewDAO.updateReview(vote,item_id,user_id,review);
		request.setAttribute("msg", "レビューの更新が完了しました");}

		request.getRequestDispatcher(sc.REVIEW_FIN).forward(request, response);
	}

}

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
import beans.ReReviewBeans;
import beans.ReviewBeans;
import beans.UserBeans;
import dao.BuyDAO;
import dao.ItemDAO;
import dao.MyListDAO;
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

		//ユーザーデータ貰う
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
		}

		//レビュー情報の全てを取得
		//

		List<ReviewBeans> revs = ReviewDAO.getReviewByID(idd);


		//ソート番号は?未指定だったら新着
		String sort = (request.getParameter("sort")==null)? "1" : request.getParameter("sort");
		int sort2 = Integer.parseInt(sort);

		if(revs!=null) {
		switch(sort2) {

		case 1://新着
			revs.sort((b,a)-> (int)(a.getRe_date().getTime() - b.getRe_date().getTime()) );
			break;
		case 2://評価順
			revs.sort((b,a)-> a.getRe_vote() - b.getRe_vote()) ;
			break;
		}
		se.setAttribute("sort2", sort2);
		}


		se.setAttribute("revs", revs);
		//商品評価獲得用
		ItemBeans revote = ItemDAO.getVote(idd);
		request.setAttribute("revote", revote);

		//レビューを評価したかチェック用
		if(user!=null) {
			//これだとレビューが複数あった時に対応できない！
		//int recheck = ReviewDAO.checkRerev(user.getId() , idd);
		List<ReReviewBeans> recheck = ReviewDAO.checkRerev(user.getId());
		request.setAttribute("recheck", recheck);
		}



		request.getRequestDispatcher(sc.ITEM_DETAIL).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession se = request.getSession();

		//ユーザーデータ貰う
		UserBeans user = (UserBeans) se.getAttribute("userInfo");

		String id = request.getParameter("id");
		String idd = request.getParameter("idd");
		String action = request.getParameter("action");


		if(action.equals("del")) {
		ReviewDAO.deleteReview(id);
		request.setAttribute("Notice", "レビューを削除しました");
		}

		if(action.equals("add")) {
		boolean check = MyListDAO.addItem(id,user.getId());
		if(!check) {
		request.setAttribute("Err", "すでに登録されている商品です");
		}
		else {
		request.setAttribute("Notice", "マイリストに追加しました");
		}
		}


		//見栄えはまあいいがurl欠けてるので更新でアウト
		//jspのform actionの所で末尾に設定すればok
		response.sendRedirect("UItemDetail?id=" + idd);
//商品id持ち込めなくて画面がぐちゃる		request.getRequestDispatcher(sc.ITEM_DETAIL).forward(request, response);
//リクエストスコープ持ち込めない		response.sendRedirect("UItemDetail?id=" + id);

	}

}

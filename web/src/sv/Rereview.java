package sv;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.UserBeans;
import dao.ReviewDAO;

/**
 * Servlet implementation class Rereview
 */
@WebServlet("/Rereview")
public class Rereview extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Rereview() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession se = request.getSession();

		String item_id = request.getParameter("idd");
		int vote = Integer.parseInt(request.getParameter("vote"));
		String review = request.getParameter("id");
		int recheck = Integer.parseInt(request.getParameter("recheck"));
		UserBeans user = (UserBeans) se.getAttribute("userInfo");

		//既に評価しててまた同じ奴にしたらdelete(0に戻す) 違ったらupdate 新規はinsert
		System.out.println("vote:" + vote + " recheck:" + recheck);
		if(vote == recheck) {//削除
			ReviewDAO.deleteReReview(user.getId() , review);
			System.out.println("削除しました");
		}
		else if(vote != recheck && recheck!= 0) { //アップデート
		ReviewDAO.updateReReview(user.getId() , vote, review);
		System.out.println("アップデートしました");
		}
		else {//新規
		ReviewDAO.addReReview(user.getId() , vote, review);
		System.out.println("新規");
		}
		response.sendRedirect("UItemDetail?id="+ item_id);
		}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

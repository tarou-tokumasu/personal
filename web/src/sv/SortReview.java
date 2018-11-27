package sv;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.ReviewBeans;

/**
 * Servlet implementation class SortReview
 */
@WebServlet("/SortReview")
public class SortReview extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public SortReview() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//ソートするとここに飛ぶ
				HttpSession se = request.getSession();


				List<ReviewBeans> il = (List<ReviewBeans>) se.getAttribute("revs");


				//ソート番号は
				String id = request.getParameter("id");
				int sort = Integer.parseInt(request.getParameter("sort"));


				switch(sort) {

				case 1://新着
					il.sort((b,a)-> (int)(a.getRe_date().getTime() - b.getRe_date().getTime()) );
					break;
				case 2://評価順
					il.sort((b,a)-> a.getRe_vote() - b.getRe_vote()) ;
					break;
				}


				se.setAttribute("revs", il);
				se.setAttribute("sort2", sort);
				System.out.println("ソート終わったよ");

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

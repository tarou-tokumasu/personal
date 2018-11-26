package sv;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.CateBeans;
import beans.ItemBeans;
import dao.ItemDAO;

/**
 * Servlet implementation class Ranking
 */
@WebServlet("/Ranking")
public class Ranking extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Ranking() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//何のランキング？（トップから来たら総合）
		String id = (request.getParameter("id") ==null)? "99" : request.getParameter("id");
		int idd = Integer.parseInt(id);

		//ランキング配列取得(拡張if）
		List<ItemBeans> ranking = (idd==99)? ItemDAO.getRanking():
			ItemDAO.getRanking(idd);


		request.setAttribute("ranking", ranking);

		//各種配列用意
		List<CateBeans> cateList = new ArrayList<CateBeans>();
		cateList = ItemDAO.getAllCate();


		request.setAttribute("scate", id);
		request.setAttribute("cateList", cateList);
		request.getRequestDispatcher(sc.RANKING).forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

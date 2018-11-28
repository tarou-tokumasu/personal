package sv;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.CateBeans;
import beans.ItemBeans;
import beans.MakerBeans;
import dao.ItemDAO;

/**
 * Servlet implementation class index
 */
@WebServlet("/Index")
public class Index extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession se = request.getSession();

		//4つだけアイテム引き出したいので
		ItemDAO id = new ItemDAO();

		//引数に数字入れる
		List<ItemBeans> newList = id.getNewItem(4);
		List<ItemBeans> saleList = id.getSaleItem(4);

		//各種配列用意
		List<CateBeans> cateList = new ArrayList<CateBeans>();
		cateList = id.getAllCate();

		List<MakerBeans> makerList = new ArrayList<MakerBeans>();
		makerList = id.getAllMaker();


		//スコープに放り込む数々
		se.setAttribute("cateList", cateList);
		se.setAttribute("makerList", makerList);
		request.setAttribute("newList", newList);
		request.setAttribute("saleList", saleList);
		request.getRequestDispatcher(sc.INDEX).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


	}
}

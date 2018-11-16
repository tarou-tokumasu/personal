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
import beans.UserBeans;
import dao.ItemDAO;

/**
 * Servlet implementation class ItemUpdate
 */
@WebServlet("/ItemUpdate")
public class ItemUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ItemUpdate() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//管理者限定メニューチェッカー
		HttpSession se = request.getSession();
		UserBeans user =(UserBeans) se.getAttribute("userInfo");

		//そもそもログインしてない
		if(user==null) {
			response.sendRedirect("Login");
		}
		//adminかどうかチェック
		else if(user.getLogin_id().equals("admin")) {

			String idd =request.getParameter("id");
			//参照先のデータを参照
			ItemDAO id = new ItemDAO();

			//アイテムカテゴリーやメーカーの一覧が必要
			//ビーンズは別途用意DAOはアイテムのとこで
			List<CateBeans> cateList = new ArrayList<CateBeans>();
			cateList = id.getAllCate();

			List<MakerBeans> makerList = new ArrayList<MakerBeans>();
			makerList = id.getAllMaker();

			request.setAttribute("cateList", cateList);
			request.setAttribute("makerList", makerList);

			ItemBeans i = id.searchID(idd);
			request.setAttribute("thisItem", i);
		request.getRequestDispatcher(sc.AD_ITEM_UPDATE).forward(request, response);}
		else {
			response.sendRedirect("index");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String item_id = request.getParameter("item_id");
		String item_cate = request.getParameter("item_cate");
		String item_maker = request.getParameter("item_maker");
		String item_price =request.getParameter("item_price");
		String item_price_down = request.getParameter("item_price_down");

		ItemDAO id = new ItemDAO();

		id.updateItem(item_id,item_cate,item_maker,item_price,item_price_down);

		response.sendRedirect("ItemList");
	}

}

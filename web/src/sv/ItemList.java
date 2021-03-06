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
 * Servlet implementation class ItemList
 */
@WebServlet("/ItemList")
public class ItemList extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ItemList() {
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

			//ユーザーリスト一覧を取得
			ItemDAO id = new ItemDAO();

			List<ItemBeans> itemList = id.getAllItem();
			request.setAttribute("itemList", itemList);

			//アイテムカテゴリーやメーカーの一覧が必要
			//ビーンズは別途用意DAOはアイテムのとこで
			List<CateBeans> cateList = new ArrayList<CateBeans>();
			cateList = id.getAllCate();

			List<MakerBeans> makerList = new ArrayList<MakerBeans>();
			makerList = id.getAllMaker();

			request.setAttribute("cateList", cateList);
			request.setAttribute("makerList", makerList);

			request.getRequestDispatcher(sc.ITEM_LIST).forward(request, response);}
			else {
				response.sendRedirect("index");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		//3（4）つの条件で検索　空欄だったらスルー　入ってたら whereの後に詰め込む条件文付け足していく
		//ただしsince until片方の入力だったらbetween使わないやつになる

	        String item_name = request.getParameter("item_name");
			String item_cate = request.getParameter("item_cate");
			String item_maker = request.getParameter("item_maker");
			String since = request.getParameter("since");
			String until = request.getParameter("until");

			String sql="SELECT * FROM item "
					+ " inner join maker ON item.item_maker = maker.id "
					+ " inner join cate ON  item.item_cate = cate.id WHERE ";
			boolean cm =false; //and入れるかどうか trueで前になんかしら居るというのを示す

			if(item_name != "") {//商品名入ってる？
				sql= sql + " item_name  like '%" + item_name + "%'";
				cm =  true;}

			if(cm==true && item_cate!="") {//前にデータあるか
				sql= sql + " AND ";
			}

			if(item_cate != "") {//カテ入ってる？
				sql= sql + " item_cate = " + item_cate;
				cm=true;}

			if(cm==true && item_maker!="") {//前にデータあるか
				sql= sql + " AND ";
			}

			if(item_maker != "") {//メーカー入ってる？
				sql= sql + " item_maker = " + item_maker;
				cm=true;}

			if(cm==true && (since!="" || until!="")) {//前にデータあるか
				sql= sql + " AND ";
			}

			if(since!="" && until !="") {//日付両方入ってる
				sql= sql + "item_date BETWEEN '" + since + "' AND '" + until + "' ";
				cm=true;
			}
			else if(since!="") { //sinceある
				sql= sql + "item_date >= '"+ since + "'";
				cm=true;
			}
			else if(until!="") {//untilある
				sql= sql + "item_date <= '"+ until + "'";
				cm=true;
			}


			if(cm==true)//検索条件どれか入ってる？
			{

			ItemDAO ud = new ItemDAO();
			List<ItemBeans> searchList = ud.getALLItem(sql);

			// リクエストスコープにユーザ一覧情報をセット
			request.setAttribute("itemList", searchList);

			}
			else {//何も入ってない
				request.setAttribute("Err", "検索条件を指定してください");

				// ユーザ一覧情報を取得
				ItemDAO ud = new ItemDAO();
				List<ItemBeans> itemList = ud.getAllItem();

				// リクエストスコープにユーザ一覧情報をセット
				request.setAttribute("itemList", itemList);

			}

			// ユーザ一覧のjspにフォワード
			request.getRequestDispatcher(sc.ITEM_LIST).forward(request, response);
	}

}

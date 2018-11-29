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

import beans.ItemBeans;
import dao.ItemDAO;

/**
 * Servlet implementation class SearchItem
 */
@WebServlet("/SearchItem")
public class SearchItem extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchItem() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//ソートするとここに飛ぶ
		ItemDAO id = new ItemDAO();
		HttpSession se = request.getSession();

		request.setCharacterEncoding("UTF-8");
		//3（4）つの条件で検索　空欄だったらスルー　入ってたら whereの後に詰め込む条件文付け足していく
		//ただしsince until片方の入力だったらbetween使わないやつになる

	        String item_name = request.getParameter("item_name");
			String item_cate = request.getParameter("item_cate");
			String item_maker = request.getParameter("item_maker");
			String since = request.getParameter("since");
			String until = request.getParameter("until");

			String sql="SELECT * , count(vote=1 or null) as upvote , count(vote=-1 or null) as downvote FROM item "
					+ " inner join maker ON item.item_maker = maker.id "
					+ " inner join cate ON  item.item_cate = cate.id "
					+ " LEFT join review ON item.id = review.item_id WHERE ";
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
				sql= sql + " group by item.id";
			List<ItemBeans> searchList = ItemDAO.getALLItem(sql);

			// リクエストスコープにユーザ一覧情報をセット
			se.setAttribute("item_name", item_name);
			se.setAttribute("item_cate", item_cate);
			se.setAttribute("item_maker", item_maker);
			se.setAttribute("since", since);
			se.setAttribute("until", until);
			se.setAttribute("searchList", searchList);
			se.setAttribute("sql", sql);


			}
			else {//何も入ってない
				//全件検索
				List<ItemBeans> searchList = ItemDAO.getAllItem();
				searchList.sort((b,a)-> (int)(a.getItem_date().getTime() - b.getItem_date().getTime()) );
				se.setAttribute("searchList", searchList);
				se.setAttribute("sort", 1);

				se.removeAttribute("item_name");
				se.removeAttribute("item_cate");
				se.removeAttribute("item_maker");
				se.removeAttribute("since");
				se.removeAttribute("until");


			}


		List<ItemBeans> il = new ArrayList<ItemBeans>();

		il = (List<ItemBeans>) se.getAttribute("searchList");

		if(il==null) {
			il= ItemDAO.getAllItem();
		}


		//ここまで検索


		//ソート
		String s = request.getParameter("sort");

		if(s==null) {
			s  = "1";
		}

		int sort = Integer.parseInt(s);

		switch(sort) {

		case 1://新着
			il.sort((b,a)-> (int)(a.getItem_date().getTime() - b.getItem_date().getTime()) );
			break;
		case 2://値段低い
			il.sort((a,b)-> a.getitem_pricez() - b.getitem_pricez()) ;
			break;
		case 3://値段高い
			il.sort((b,a)-> a.getitem_pricez() - b.getitem_pricez() );
			break;
		case 4://割り引かれてる順
			il.sort((b,a)-> a.getItem_price_down() - b.getItem_price_down() );
			break;
		case 5://評価
			il.sort((b,a)-> a.getUpvote() - b.getUpvote() );
			break;
		case 6:
			//ゴリラ
			for(int i = il.size()-1  ; i>=0 ; i--) {
				if(il.get(i).getItem_price_down() == 0) {

					il.remove(i);
				}
			}
			//かっこいい方法だけどオブジェクトでできない？　il.sort((b,a)-> a.getItem_price_down() - b.getItem_price_down() );
		}



		se.setAttribute("searchList", il);
		se.setAttribute("sort", sort);

		//ここまでソート

		//ここから商品のページ分け
		//今何ページ目かをURLから引っ張り出す
		//拡張if　条件式 ? true : false ; pageに入ってなかったら1ページ目　入ってたらそのページ数
		int page = Integer.parseInt(request.getParameter("page") == null ? "1": request.getParameter("page"));

		//商品数のカウント
		double size= il.size();

		//何ページ必要か　ceilは切り上げ
		int max = (int)Math.ceil(size / sc.ITEMS);


		//今いるページ数によって検索結果が入った配列を表示させる　1ページ目[0]～[4]  2ページ目[5]～[9]…みたいに

		request.setAttribute("items", sc.ITEMS);
		request.setAttribute("max", max);
		request.setAttribute("page", page);


		request.getRequestDispatcher(sc.SEARCH).forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		ItemDAO ud = new ItemDAO();

		HttpSession se= request.getSession();

		request.setCharacterEncoding("UTF-8");
		//3（4）つの条件で検索　空欄だったらスルー　入ってたら whereの後に詰め込む条件文付け足していく
		//ただしsince until片方の入力だったらbetween使わないやつになる

	        String item_name = request.getParameter("item_name");
			String item_cate = request.getParameter("item_cate");
			String item_maker = request.getParameter("item_maker");
			String since = request.getParameter("since");
			String until = request.getParameter("until");

			String sql="SELECT * , count(vote=1 or null) as upvote , count(vote=-1 or null) as downvote FROM item "
					+ " inner join maker ON item.item_maker = maker.id "
					+ " inner join cate ON  item.item_cate = cate.id "
					+ " LEFT join review ON item.id = review.item_id WHERE ";
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
				sql= sql + " group by item.id";
			List<ItemBeans> searchList = ud.getALLItem(sql);

			// リクエストスコープにユーザ一覧情報をセット
			se.setAttribute("item_name", item_name);
			se.setAttribute("item_cate", item_cate);
			se.setAttribute("item_maker", item_maker);
			se.setAttribute("since", since);
			se.setAttribute("until", until);
			se.setAttribute("searchList", searchList);
			se.setAttribute("sql", sql);

			// 検索結果フォワード
			response.sendRedirect("SearchResult");

			}
			else {//何も入ってない
				//全件検索
				List<ItemBeans> searchList = ud.getAllItem();
				searchList.sort((b,a)-> (int)(a.getItem_date().getTime() - b.getItem_date().getTime()) );
				se.setAttribute("searchList", searchList);
				se.setAttribute("sort", 1);

				se.removeAttribute("item_name");
				se.removeAttribute("item_cate");
				se.removeAttribute("item_maker");
				se.removeAttribute("since");
				se.removeAttribute("until");

				response.sendRedirect("SearchResult?sort=1");

			}


	}


}
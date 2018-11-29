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
import beans.MyListB;
import beans.UserBeans;
import dao.ItemDAO;
import dao.MyListDAO;

/**
 * Servlet implementation class MyList
 */
@WebServlet("/MyList")
public class MyList extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession se = request.getSession();
		UserBeans user = (UserBeans) se.getAttribute("userInfo");

		List<MyListB> mylist = MyListDAO.getMyList(user.getId());

		if(mylist==null) {
			request.setAttribute("items", "0");
		}
		if(mylist!=null) {
			if(mylist.isEmpty()) { //分けないとぬるぽで詰まる
			request.setAttribute("items", "0");
			}
			else {
			request.setAttribute("items", "1");
			}
		}
		se.setAttribute("mylist", mylist);

		request.getRequestDispatcher(sc.MYLIST).forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//チェック商品の削除 or カートに追加
		HttpSession se = request.getSession();
		UserBeans user = (UserBeans) se.getAttribute("userInfo");

		List<MyListB> mylist = (List<MyListB>) se.getAttribute("mylist");

		String [] dlist = request.getParameterValues("delete");

		//ゲッパラでいなかった時はエラー起きない
		//equalとかでnullだったらアウト？

		if(request.getParameter("action")!= null) {

		//ここから削除パート
		if(request.getParameter("action").equals("del")) {

			if(dlist==null) {
				request.setAttribute("Err", "チェックされていません");
			}
			else {
			for(String dl : dlist) {
				for(MyListB ml : mylist) {
					if(ml.getId()==Integer.parseInt(dl)) {
						mylist.remove(ml);
						//カートと違ってセッションだけじゃなくDBの方も消さないといけない
						MyListDAO.deleteByID(ml.getId());
						break;//削除完了したらもう残りを探す必要がないので
					}
				}
			}

		//中身からっぽかチェック
			if(mylist.isEmpty()) {
				request.setAttribute("items", "0");
			}else {
				request.setAttribute("items", "1");
			}
			request.setAttribute("notice", "チェックされた商品の削除が完了しました");
			}
		}//削除パート終わり

		if(request.getParameter("action").equals("cart")) {
			if(dlist==null) {
				request.setAttribute("Err", "チェックされていません");
			}
			else {

				ArrayList<ItemBeans> cart = (ArrayList<ItemBeans>) se.getAttribute("cart");

				//カートがないなら作る
				if(cart==null) {
					cart = new  ArrayList<ItemBeans>();
				}

				for(String dl : dlist) {
					for(MyListB ml : mylist) {
						if(ml.getId()==Integer.parseInt(dl)) {
							mylist.remove(ml);
							//カートと違ってセッションだけじゃなくDBの方も消さないといけない
							MyListDAO.deleteByID(ml.getId());
							//カートに追加する
							ItemBeans item = ItemDAO.searchID(String.valueOf(ml.getItem_id()));
							System.out.println(item);
							cart.add(item);
							break;//追加完了したらもう残りを探す必要がないので
						}
					}
				}
				request.setAttribute("notice", "チェックされた商品をカートに追加しました");
				se.setAttribute("cart", cart);
			}
		}//カートに追加ここまで

		}

		se.setAttribute("mylist", mylist);

		doGet(request, response);
	}

}

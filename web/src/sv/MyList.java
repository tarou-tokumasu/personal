package sv;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.MyListB;
import beans.UserBeans;
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
		System.out.println("mylist:"+ mylist);
		if(mylist==null) {
			request.setAttribute("items", "0");
		}
		if(mylist!=null) {
			if(mylist.isEmpty()) { //分けないとぬるぽで詰まる
			request.setAttribute("items", "0");
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

		List<MyListB> mylist = (List<MyListB>) se.getAttribute("mylist");

		String [] dlist = request.getParameterValues("delete");

		//ここから削除パート
		if(request.getParameter("action").equals("del")) {
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
			se.setAttribute("mylist", mylist);
		}//削除パート終わり


		//カートに追加
		if(request.getParameter("action").equals("cart")) {}

		request.getRequestDispatcher(sc.MYLIST).forward(request, response);
	}

}

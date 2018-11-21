package sv;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.UserBeans;
import dao.UserDAO;

/**
 * Servlet implementation class UserList
 */
@WebServlet("/UserList")
public class UserList extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserList() {
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
					UserDAO ud = new UserDAO();

					List<UserBeans> userList = ud.getALLUser();
					request.setAttribute("userList", userList);
				request.getRequestDispatcher(sc.USER_LIST).forward(request, response);}
				else {
					response.sendRedirect("index");
				}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO:検索処理全般
		request.setCharacterEncoding("UTF-8");
	//3（4）つの条件で検索　空欄だったらスルー　入ってたら whereの後に詰め込む条件文付け足していく
	//ただしsince until片方の入力だったらbetween使わないやつになる

        String loginId = request.getParameter("loginId");
		String userName = request.getParameter("userName");
		String address = request.getParameter("address");
		String since = request.getParameter("since");
		String until = request.getParameter("until");

		String sql="SELECT * FROM user WHERE ";
		boolean cm =false; //and入れるかどうか trueで前になんかしら居るというのを示す

		if(loginId != "") {//ログインID入ってる？
			sql= sql + " login_id  like '" + loginId + "'";
			cm =  true;}

		if(cm==true && userName!="") {//前にデータあるか
			sql= sql + " AND ";
		}

		if(userName != "") {//名前入ってる？
			sql= sql + "name like'%" + userName +"%'";
			cm=true;}

		if(cm==true && address!="") {//前にデータあるか
			sql= sql + " AND ";
		}

		if(address != "") {//住所入ってる？
			sql= sql + "address like'%" + address +"%'";
			cm=true;}

		if(cm==true && (since!="" || until!="")) {//前にデータあるか
			sql= sql + " AND ";
		}

		if(since!="" && until !="") {//日付両方入ってる
			sql= sql + "birth_date BETWEEN '" + since + "' AND '" + until + "' ";
			cm=true;
		}
		else if(since!="") { //sinceある
			sql= sql + "birth_date >= '"+ since + "'";
			cm=true;
		}
		else if(until!="") {//untilある
			sql= sql + "birth_date <= '"+ until + "'";
			cm=true;
		}


		if(cm==true)//検索条件どれか入ってる？
		{

		UserDAO ud = new UserDAO();
		List<UserBeans> searchList = ud.getALLUser(sql);

		// リクエストスコープにユーザ一覧情報をセット
		request.setAttribute("userList", searchList);

		}
		else {//何も入ってない
			request.setAttribute("Err", "検索条件を指定してください");

			// ユーザ一覧情報を取得
			UserDAO ud = new UserDAO();
			List<UserBeans> userList = ud.getALLUser();

			// リクエストスコープにユーザ一覧情報をセット
			request.setAttribute("userList", userList);

		}

		// ユーザ一覧のjspにフォワード
		request.getRequestDispatcher(sc.USER_LIST).forward(request, response);
	}

}

package sv;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import beans.CateBeans;
import beans.MakerBeans;
import beans.UserBeans;
import dao.ItemDAO;

/**
 * Servlet implementation class AddItem
 */
@WebServlet("/AddItem")
@MultipartConfig(location="/tmp", maxFileSize=1048576)

public class AddItem extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddItem() {
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

		//アイテムカテゴリーやメーカーの一覧が必要
		//ビーンズは別途用意DAOはアイテムのとこで
		ItemDAO id= new ItemDAO();
		List<CateBeans> cateList = new ArrayList<CateBeans>();
		cateList = id.getAllCate();

		List<MakerBeans> makerList = new ArrayList<MakerBeans>();
		makerList = id.getAllMaker();

		request.setAttribute("cateList", cateList);
		request.setAttribute("makerList", makerList);

		request.getRequestDispatcher(sc.AD_ADD_ITEM).forward(request, response);}
		else {
			response.sendRedirect("index");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String item_name = request.getParameter("item_name");
		String item_cate = request.getParameter("item_cate");
		String item_maker = request.getParameter("item_maker");
		String item_price = request.getParameter("item_price");
		String item_price_down = request.getParameter("item_price_down");
		Date date = new Date();
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


		ItemDAO id = new ItemDAO();
		String files= request.getParameter("file");
		//空欄潰す ""指定だとエラー吐く
		if(item_name.length()==0 || item_cate.length()==0 || item_maker.length()==0 || item_price.length()==0 || item_price_down.length()==0 || files==null) {
		request.setAttribute("Err", "空欄があります");
		request.getRequestDispatcher(sc.AD_ADD_ITEM).forward(request, response);
		}
		//名前の重複潰す
		else if(id.chackName(item_name)==false)
		{
		request.setAttribute("Err", "既に登録されている商品です");
		request.getRequestDispatcher(sc.AD_ADD_ITEM).forward(request, response);
		}
		else {
		//ファイルアップロードのあれこれ　この時点ではorg.apache.catalina.core.ApplicationPart@20d467b6とか謎文字列
		Part part =request.getPart("file");
		//ファイル名だけ抽出してくれた dq11.jpg
		String name =this.getFileName(part);

		//DBに登録
		id.addItem(item_name, item_cate, item_maker , item_price , item_price_down , name,sdf1.format(date));


		//ファイルアップロードしてくれる　ただしクリーンで吹っ飛ぶ
		//同名ファイルあってもお構いなし
		String path = getServletContext().getRealPath("/img");
		part.write(getServletContext().getRealPath("/img") + "/" + name);

		response.sendRedirect("ItemList");


		}
	}

	private String getFileName(Part part) {
		String name = null;
		  for (String dispotion : part.getHeader("Content-Disposition").split(";")) {
	            if (dispotion.trim().startsWith("filename")) {
	                name = dispotion.substring(dispotion.indexOf("=") + 1).replace("\"", "").trim();
	                name = name.substring(name.lastIndexOf("\\") + 1);
	                break;
	            }
	        }
	        return name;
	    }

	}

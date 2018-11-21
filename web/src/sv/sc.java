package sv;

import java.util.ArrayList;

import beans.ItemBeans;

//ショートカット用いろいろ　進捗チェックにもなる
public class sc {
	//1ページに出る商品数
	static final int ITEMS = 5;

	//ログイン画面
	static final String LOGIN = "/WEB-INF/jsp/login.jsp";
	//メイン画面
	static final String INDEX = "/WEB-INF/jsp/index.jsp";
	//新規ID作成画面
	static final String NEWID = "/WEB-INF/jsp/createID.jsp";
	//管理者画面
	static final String AD_MENU = "/WEB-INF/jsp/ad_menu.jsp";
	//ユーザー一覧
	static final String USER_LIST = "/WEB-INF/jsp/userlist.jsp";
	//ユーザー詳細
	static final String AD_USER_DETAIL = "/WEB-INF/jsp/ad_userdetail.jsp";
	//ユーザーアプデ
	static final String AD_USER_UPDATE = "/WEB-INF/jsp/ad_userupdate.jsp";
	//ユーザー削除
	static final String AD_USER_DELETE = "/WEB-INF/jsp/ad_userdelete.jsp";
	//商品リスト
	static final String ITEM_LIST = "/WEB-INF/jsp/ad_itemlist.jsp";
	//商品詳細
    static final String AD_ITEM_DETAIL =  "/WEB-INF/jsp/ad_itemdetail.jsp";
    //商品追加
    static final String AD_ADD_ITEM ="/WEB-INF/jsp/ad_additem.jsp";
    //商品アプデ
	static final String AD_ITEM_UPDATE = "/WEB-INF/jsp/ad_itemupdate.jsp";
	//商品削除
	static final String AD_ITEM_DELETE = "/WEB-INF/jsp/ad_itemdelete.jsp";
	//商品検索結果
	static final String SEARCH = "/WEB-INF/jsp/search.jsp";
	//ユーザー視点での商品詳細ページ
	static final String ITEM_DETAIL ="/WEB-INF/jsp/itemdetail.jsp";
	//カート
	static final String CART ="/WEB-INF/jsp/cart.jsp";
	//レジ
	static final String REGI ="/WEB-INF/jsp/regi.jsp";


	public static int getTotal(ArrayList<ItemBeans> iL) {

		int total=0;

		for(ItemBeans a : iL) {
			total+= a.getitem_pricez();
		}

		return total;


	}



}


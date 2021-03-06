package sv;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import javax.xml.bind.DatatypeConverter;

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
	//管理画面ユーザー詳細
	static final String AD_USER_DETAIL = "/WEB-INF/jsp/ad_userdetail.jsp";
	//ユーザーアプデ
	static final String AD_USER_UPDATE = "/WEB-INF/jsp/ad_userupdate.jsp";
	//ユーザー削除
	static final String AD_USER_DELETE = "/WEB-INF/jsp/ad_userdelete.jsp";
	//商品リスト
	static final String ITEM_LIST = "/WEB-INF/jsp/ad_itemlist.jsp";
	//管理画面での商品詳細
    static final String AD_ITEM_DETAIL =  "/WEB-INF/jsp/ad_itemdetail.jsp";
    //商品追加
    static final String AD_ADD_ITEM ="/WEB-INF/jsp/ad_additem.jsp";
    //商品アプデ
	static final String AD_ITEM_UPDATE = "/WEB-INF/jsp/ad_itemupdate.jsp";
	//商品削除
	static final String AD_ITEM_DELETE = "/WEB-INF/jsp/ad_itemdelete.jsp";
	//商品検索結果
	static final String SEARCH = "/WEB-INF/jsp/search.jsp";
	//商品詳細ページ
	static final String ITEM_DETAIL ="/WEB-INF/jsp/itemdetail.jsp";
	//カート
	static final String CART ="/WEB-INF/jsp/cart.jsp";
	//レジ
	static final String REGI ="/WEB-INF/jsp/regi.jsp";
	//購入完了
	static final String BUY_RESULT ="/WEB-INF/jsp/buyresult.jsp";
	//ユーザー情報
	static final String USER_DETAIL ="/WEB-INF/jsp/userdetail.jsp";

	static final String BUY_DETAIL = "/WEB-INF/jsp/buydetail.jsp";

	public static final String RANKING = "/WEB-INF/jsp/ranking.jsp";

	public static final String ITEM_REVIEW = "/WEB-INF/jsp/review.jsp";

	public static final String REVIEW_FIN ="/WEB-INF/jsp/reviewfin.jsp";

	public static final String MYLIST ="/WEB-INF/jsp/mylist.jsp";

	public static final String AD_SALES = "/WEB-INF/jsp/ad_uriage.jsp";

	//便利メソッド

	//カート内全部足す
	public static int getTotal(ArrayList<ItemBeans> iL) {
		int total=0;

		for(ItemBeans a : iL) {
			total+= a.getitem_pricez();
		}
		return total;
	}

	public static String encrypt(String password) {

		//ハッシュ生成前にバイト配列に置き換える際のCharset
		Charset charset = StandardCharsets.UTF_8;
		//ハッシュアルゴリズム
		String algorithm = "MD5";

		//ハッシュ生成処理
		byte[] bytes;
		try {
			bytes = MessageDigest.getInstance(algorithm).digest(password.getBytes(charset));
			String result = DatatypeConverter.printHexBinary(bytes);
			return result;
		} catch (NoSuchAlgorithmException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
			return null;
	}





}


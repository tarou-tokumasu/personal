package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import beans.UserBeans;
import db.DBManager;
import sv.DeliBeans;

public class BuyDAO {

	public BuyDAO() {
		// TODO 自動生成されたコンストラクター・スタブ
	}

	public static int addBuy(DeliBeans deli, int total2, UserBeans us, String format) {
		Connection cone = DBManager.getConnection();

		int buy_id = -100;

		String sql = "INSERT INTO buy ("
				+ "user_id,"
				+ "total_price,"
				+ "deli_id,"
				+ "buy_date ) "
				+ "VALUES ("
				+ "?,"
				+ "?,"
				+ "?,"
				+ "? )";

		try {
			//主キー呼び出し
			PreparedStatement pst = cone.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			pst.setInt(1, us.getId());
			pst.setInt(2, total2);
			pst.setInt(3, deli.getId());
			pst.setString(4,format);
			pst.executeUpdate();
			System.out.println("購入データ登録完了");

			//主キー引き出しの儀
			try (ResultSet rs = pst.getGeneratedKeys()){
				if(rs.next()) {buy_id = rs.getInt(1);}
			}
			return buy_id;



		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			System.out.println("エラー");
			e.printStackTrace();
		}
		finally {
			try {
				cone.close();
			} catch (SQLException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
		}
		return buy_id;


	}

}

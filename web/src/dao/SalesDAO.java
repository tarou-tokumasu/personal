package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import beans.SalesB;
import db.DBManager;

public class SalesDAO {


	public static List<SalesB> getSales() {
		ArrayList<SalesB> sales = new ArrayList<SalesB>();

		Connection cone = null;
		cone = DBManager.getConnection();

		//何を足す?
		//ベースbuy buy_idbuy_detailのl_price
		//buy.idとbuy_detailのbuy_id紐付け
		//グループバイbuyの日付

		String sql= "SELECT DATE_FORMAT(buy_date , '%Y年%m月%d日') as date , SUM(buy_detail.l_price) as sales from buy "+
				" inner join buy_detail ON buy.id = buy_detail.buy_id " +
				" GROUP BY DATE_FORMAT(buy_date , '%Y%m%d') ORDER BY buy_date desc";
		try {
			Statement st = cone.createStatement();
			ResultSet rs =st.executeQuery(sql);

			while(rs.next()) {

				SalesB s = new SalesB();
                s.setDate(rs.getString("date"));
                s.setSales(rs.getInt("sales"));

                sales.add(s);

			}

		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}finally {try {
			cone.close();
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}}


		return sales;
	}


}

package dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import beans.BuyDetailBeans;
import db.DBManager;

public class BuyDetailDAO {

	public BuyDetailDAO() {
		// TODO 自動生成されたコンストラクター・スタブ
	}

	public static void addDetail(BuyDetailBeans bd) {
		Connection cone = DBManager.getConnection();

		String sql = "INSERT INTO buy_detail ("
				+ "buy_id ,"
				+ "item_id ,"
				+ "l_price) "
				+ "VALUES("
				+ "?,"
				+ "?,"
				+ "?)";

		try {
			PreparedStatement pst = cone.prepareStatement(sql);
			pst.setInt(1, bd.getBuy_id());
			pst.setInt(2, bd.getItem_id());
			pst.setInt(3, bd.getLast_price());
			pst.executeUpdate();

		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		finally {try {
			cone.close();
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}}

	}

	public static ArrayList<BuyDetailBeans> getDetail(String parameter) {
		Connection cone = DBManager.getConnection();

		ArrayList<BuyDetailBeans> bd = new ArrayList<BuyDetailBeans>();


		//何欲しい？
		String sql = "SELECT * FROM buy_detail inner join item ON buy_detail.item_id = item.id where buy_id=?";

		try {
			PreparedStatement pst = cone.prepareStatement(sql);
			pst.setString(1, parameter);
			ResultSet rs =pst.executeQuery();

			while(rs.next()) {

			BuyDetailBeans b = new BuyDetailBeans();
			b.setItem_name(rs.getString("item_name"));
			b.setLast_price(rs.getInt("l_price"));
			bd.add(b);

			}


		return bd;

		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		finally {try {
			cone.close();
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}}
		return bd;

}
}

package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.DBManager;
import sv.DeliBeans;

public class DeliDAO {

	public static List<DeliBeans> getALLDeli() {

		Connection cone = null;
		cone = DBManager.getConnection();

		ArrayList<DeliBeans> DeliList = new ArrayList<DeliBeans>();

		String sql= "SELECT * FROM deli";

		try {
			Statement st =cone.createStatement();
			ResultSet rs =st.executeQuery(sql);

			while(rs.next()) {
				DeliBeans deli = new DeliBeans();

				deli.setId(rs.getInt("id"));
				deli.setDeli_name(rs.getString("deli_name"));
				deli.setDeli_price(rs.getInt("deli_price"));
				DeliList.add(deli);
			}

			return DeliList;


		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
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


		return DeliList;

	}

	public static DeliBeans getDeli(String deli) {
		Connection cone = DBManager.getConnection();

		String sql = "SELECT * FROM deli WHERE id = ?";

		try {
			PreparedStatement pst = cone.prepareStatement(sql);
			pst.setString(1, deli);
			ResultSet rs = pst.executeQuery();

			if(rs.next()) {
			DeliBeans d = new DeliBeans();

			d.setId(rs.getInt("id"));
			d.setDeli_name(rs.getString("deli_name"));
			d.setDeli_price(rs.getInt("deli_price"));
			return d;
			}



		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}finally {
			try {
				cone.close();
			} catch (SQLException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
		}


		return null;
	}


}

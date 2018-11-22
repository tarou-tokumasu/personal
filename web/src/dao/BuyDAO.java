package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import beans.BuyDataBeans;
import beans.UserBeans;
import db.DBManager;
import sv.DeliBeans;

public class BuyDAO {

	public BuyDAO() {
		// TODO 自動生成されたコンストラクター・スタブ
	}

	public static int addBuy(DeliBeans deli, int total2, UserBeans us, String format, int point) {
		Connection cone = DBManager.getConnection();

		int buy_id = -100;

		String sql = "INSERT INTO buy ("
				+ "user_id,"
				+ "total_price,"
				+ "deli_id,"
				+ "buy_date,"
				+ "movpoint ) "
				+ "VALUES ("
				+ "?,"
				+ "?,"
				+ "?,"
				+ "?, "
				+ "?)";

		try {
			//主キー呼び出し
			PreparedStatement pst = cone.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			pst.setInt(1, us.getId());
			pst.setInt(2, total2);
			pst.setInt(3, deli.getId());
			pst.setString(4,format);
			pst.setInt(5, point);
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

	public static List<BuyDataBeans> searchMyBuy(int id) {
		Connection cone = DBManager.getConnection();

		ArrayList<BuyDataBeans> bdl =new  ArrayList<BuyDataBeans>();

		String sql = "SELECT * FROM buy inner join deli ON buy.deli_id = deli.id WHERE user_id = ?";


		try {
			PreparedStatement pst = cone.prepareStatement(sql);
			pst.setInt(1, id);
			ResultSet rs =pst.executeQuery();

			while(rs.next()) {
			BuyDataBeans bd = new BuyDataBeans();

			bd.setId(rs.getInt("id"));
			bd.setBuy_date(rs.getTimestamp("buy_date"));
			bd.setDeli_name(rs.getString("deli_name"));
			bd.setDeli_price(rs.getInt("deli_price"));
			bd.setTotal_price(rs.getInt("total_price"));
			bdl.add(bd);
			}

			return bdl;

		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}finally
		{
			try {
				cone.close();
			} catch (SQLException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
		}

		return bdl;
	}

	public static BuyDataBeans searchMyBuy(int id, String id2) {
		Connection cone = DBManager.getConnection();


		String sql = "SELECT * FROM buy inner join deli ON buy.deli_id = deli.id WHERE user_id = ? AND buy.id= ? ";


		BuyDataBeans bd = new BuyDataBeans();

		try {
			PreparedStatement pst = cone.prepareStatement(sql);
			pst.setInt(1, id);
			pst.setString(2, id2);
			ResultSet rs =pst.executeQuery();

			if(rs.next()) {

			bd.setId(rs.getInt("id"));
			bd.setBuy_date(rs.getTimestamp("buy_date"));
			bd.setDeli_name(rs.getString("deli_name"));
			bd.setDeli_price(rs.getInt("deli_price"));
			bd.setTotal_price(rs.getInt("total_price"));
			}

			return bd;

		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}finally
		{
			try {
				cone.close();
			} catch (SQLException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
		}

		return bd;
	}
	}
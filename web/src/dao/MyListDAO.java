package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.MyListB;
import db.DBManager;

public class MyListDAO {

	public MyListDAO() {
		// TODO 自動生成されたコンストラクター・スタブ
	}

	public static List<MyListB> getMyList(int id) {

		Connection cone = DBManager.getConnection();
		ArrayList<MyListB> mylist = new ArrayList<MyListB>();

//何欲しい？
//商品idから商品名と値段と割引率と評価
		String sql= "SELECT * ,count(vote=1 or null) as upvote , count(vote=-1 or null) as downvote FROM mylist"
				+ " inner join item  ON item.id= mylist.item_id  "
				+ " left  join review ON item.id = review.item_id "
				+ " where mylist.user_id = ? GROUP BY mylist.id";

		try {
			PreparedStatement pst = cone.prepareStatement(sql);
			pst.setInt(1, id);
			ResultSet rs = pst.executeQuery();

			while(rs.next()) {
				MyListB m = new MyListB();
				m.setId(rs.getInt("id"));
				m.setItem_id(rs.getInt("mylist.item_id"));
				m.setUser_id(rs.getInt("mylist.user_id"));
				m.setItem_name(rs.getString("item_name"));
				m.setUpvote(rs.getInt("upvote"));
				m.setDownvote(rs.getInt("downvote"));
				m.setItem_price(rs.getInt("item_price"));
				m.setItem_price_down(rs.getInt("item_price_down"));
				mylist.add(m);
			}
			if(mylist.isEmpty()) {
				return null;
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

		return mylist;

	}

	public static void deleteByID(int id) {
		Connection cone = DBManager.getConnection();

		String sql= "DELETE FROM mylist WHERE id= ?";

		try {
			PreparedStatement pst = cone.prepareStatement(sql);
			pst.setInt(1, id);
			pst.executeUpdate();



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

	}

	public static boolean addItem(String id, int id2) {
		Connection cone = DBManager.getConnection();

		//重複チェック用
		String sql= "select * from mylist WHERE item_id = ? AND user_id = ?";

		String sql2= "INSERT INTO mylist (item_id , user_id ) values(? , ?)";

		try {
			PreparedStatement pst = cone.prepareStatement(sql);
			pst.setString(1, id);
			pst.setInt(2, id2);
			ResultSet rs = pst.executeQuery();

			if(!rs.next()) {
				pst = cone.prepareStatement(sql2);
				pst.setString(1, id);
				pst.setInt(2, id2);
				pst.executeUpdate();
					System.out.println("登録成功！");
					return true;

			}
			System.out.println("マイリスト：重複！");
			return false;

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
		return false;
	}
}

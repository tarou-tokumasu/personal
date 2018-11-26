package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import beans.ReviewBeans;
import db.DBManager;

public class ReviewDAO {

	public static void addReview(int vote, String item_id, int user_id, String review) {

		Connection cone = DBManager.getConnection();

		String sql= "INSERT INTO review ("
				+ "vote ,"
				+ "review,"
				+ "item_id,"
				+ "user_id,"
				+ "re_date,"
				+ "re_vote) "
				+ " VALUES ("
				+ "? ,? ,? ,? ,? ,0)";


		try {
			PreparedStatement pst = cone.prepareStatement(sql);
			pst.setInt(1, vote);
			pst.setString(2, review);
			pst.setString(3, item_id);
			pst.setInt(4, user_id);
			pst.setTimestamp(5, new Timestamp(System.currentTimeMillis()));

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

	public static ReviewBeans checkRev(int id, String idd) {
		ReviewBeans rev = new ReviewBeans();

		Connection cone = DBManager.getConnection();

		String sql ="select * from review WHERE user_id= ? AND item_id= ? ";


		try {
			PreparedStatement pst = cone.prepareStatement(sql);
			pst.setInt(1, id);
			pst.setString(2, idd);
			ResultSet rs = pst.executeQuery();

			if(rs.next()) {
				rev.setVote(rs.getInt("vote"));
				rev.setReview(rs.getString("review"));
				System.out.println(rs.getString("review"));
				System.out.println(rs.getInt("vote"));
				return rev;
			}
			else {
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


		return rev;
	}

	public static void updateReview(int vote, String item_id, int user_id, String review) {
		Connection cone = DBManager.getConnection();

		String sql= "UPDATE review SET "
				+ "vote =? ,"
				+ "review =?,"
				+ "re_date = ?"
				+ " WHERE user_id=? AND item_id=?";


		try {
			PreparedStatement pst = cone.prepareStatement(sql);
			pst.setInt(1, vote);
			pst.setString(2, review);
			pst.setString(5, item_id);
			pst.setInt(4, user_id);
			pst.setTimestamp(3, new Timestamp(System.currentTimeMillis()));

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

	public static List<ReviewBeans> getReviewByID(String idd) {
		ArrayList<ReviewBeans> revs = new ArrayList<ReviewBeans>();

		Connection cone = DBManager.getConnection();

		String sql ="select * from review inner join user on user.id = review.user_id WHERE item_id= ? ";


		try {
			PreparedStatement pst = cone.prepareStatement(sql);
			pst.setString(1, idd);
			ResultSet rs = pst.executeQuery();

			while(rs.next()) {
				//一応全部ひらっておく
				ReviewBeans rev = new ReviewBeans();
				rev.setVote(rs.getInt("vote"));
				rev.setReview(rs.getString("review"));
				rev.setRe_date(rs.getTimestamp("re_date"));
				rev.setItem_id(rs.getInt("item_id"));
				rev.setRe_vote(rs.getInt("re_vote"));
				rev.setUser_id(rs.getInt("user_id"));
				rev.setId(rs.getInt("id"));
				rev.setReviewer(rs.getString("user_name"));
				revs.add(rev);
			}

			return revs;

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


		return revs;
	}
}

package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import beans.UserBeans;
import db.DBManager;

public class UserDAO {

	   public UserBeans findLoginID(String loginId, String password) {
	        Connection conn = null;
	        try {
	            // データベースへ接続
	            conn = DBManager.getConnection();

	            // SELECT文を準備
	            String sql = "SELECT * FROM user WHERE login_id = ? and password = ?";

	             // 実行
	            PreparedStatement pStmt = conn.prepareStatement(sql);
	            pStmt.setString(1, loginId);
	            pStmt.setString(2, password);
	            ResultSet rs = pStmt.executeQuery();

	             //結果が一件の時の儀式
	            if (!rs.next()) {
	                return null;
	            }

	            int idData = rs.getInt("id");
	            String loginIdData = rs.getString("login_id");
	            String nameData = rs.getString("user_name");
	            return new UserBeans(idData,loginIdData, nameData);

	        } catch (SQLException e) {

	            e.printStackTrace();
	            return null;
	        } finally {
	            // データベース切断
	            if (conn != null) {
	                try {
	                    conn.close();
	                } catch (SQLException e) {
	                    e.printStackTrace();
	                    return null;
	                }
	            }
	        }
	    }

}

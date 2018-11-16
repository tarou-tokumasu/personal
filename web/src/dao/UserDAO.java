package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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



	   public boolean checkID(String login_id) {
		   Connection cone = null;

		   try {
			   cone =DBManager.getConnection();

			   //select
			   String sql = "SELECT login_id from user where login_id = ? ";

			   PreparedStatement pst = cone.prepareStatement(sql);
			   pst.setString(1, login_id);
			   ResultSet rs = pst.executeQuery();

			   if(!rs.next()) {
				   //nextできない＝見つからない事は
				   System.out.println("一致なし");
				   return true;
			   }

			   else
			   {
				   System.out.println("重複してる");
				   return false;
			   }


		   }
		   catch(SQLException e){
			e.printStackTrace();
           return false;
		   }
		  finally {
			  try {
				cone.close();
			} catch (SQLException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
				return false;
			}
			  }


	   }



	public boolean checkPass(String password, String kakunin) {
		if(password.equals(kakunin)) {
		System.out.println("パス一致");
		return true;
		}
		else {
		System.out.println("パス不一致");
		return false;
		}
	}



	public void addUser(String login_ID, String user_name, String password, String birth_date, String address , String string) {


		Connection cone = null;



		cone = DBManager.getConnection();
		String sql = "INSERT INTO user (login_id, user_name , password, address,birth_date, create_date , update_date , point)"
				+ "VALUES (? , ? , ? , ? , ? , ? , ? , 0)";

		try {
			PreparedStatement pst = cone.prepareStatement(sql);
			pst.setString(1, login_ID);
			pst.setString(2, user_name);
			pst.setString(3, password);
			pst.setString(4, address);
			pst.setString(5, birth_date);
			pst.setString(6,  string);
			pst.setString(7, string);

			pst.executeUpdate();
			System.out.println("新規ID登録完了");


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



	public List<UserBeans> getALLUser() {
		Connection cone = null;

		cone = DBManager.getConnection();
		ArrayList<UserBeans> userList = new ArrayList<UserBeans>();

		String sql = "SELECT * FROM user";
		try {
			Statement st = cone.createStatement();
			ResultSet rs = st.executeQuery(sql);

			while(rs.next()) {
                int id = rs.getInt("id");
                String loginId = rs.getString("login_id");
                String name = rs.getString("user_name");
                String address = rs.getString("address");
                Date birthDate = rs.getDate("birth_date");
                String password = rs.getString("password");
                Date createDate = rs.getDate("create_date");
                Date updateDate = rs.getDate("update_date");
                int point = rs.getInt("point");


                UserBeans user = new UserBeans(id, loginId, name,address, birthDate, password, createDate, updateDate,point);

                userList.add(user);

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

		return userList;

	}



	public UserBeans searchID(String id) {
		Connection cone = null;

		cone = DBManager.getConnection();
		int idd = Integer.parseInt(id);


		String sql = "SElECT * FROM user WHERE id=?";

		try {
			PreparedStatement pst = cone.prepareStatement(sql);
			pst.setInt(1, idd);

			ResultSet rs = pst.executeQuery();

			if(!rs.next()) { return null;}

			int idm = rs.getInt("id");
            String loginId = rs.getString("login_id");
            String name = rs.getString("user_name");
            String address = rs.getString("address");
            Date birthDate = rs.getDate("birth_date");
            String password = rs.getString("password");
            Date createDate = rs.getTimestamp("create_date");
            Date updateDate = rs.getTimestamp("update_date");
            int point = rs.getInt("point");

           return  new UserBeans(idm, loginId, name,address, birthDate, password, createDate, updateDate,point);

		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		return null;
	}






	public void UpdatePassword(String login_id , String password) {
		Connection cone = null;

		cone = DBManager.getConnection();

		String sql = "UPDATE user SET password=? WHERE login_id = ?";

		try {
			PreparedStatement pst = cone.prepareStatement(sql);
			pst.setString(1, password);
			pst.setString(2, login_id);
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



	public void UpdateUser(String login_id ,String user_name, String birth_date, String address, String format) {
		Connection cone = null;

		cone = DBManager.getConnection();

		String sql = "UPDATE user Set user_name=? , birth_date=? ,  address=? , update_date=? WHERE login_id=?";


		try {
			PreparedStatement pst = cone.prepareStatement(sql);
			pst.setString(1, user_name);
			pst.setString(2,birth_date);
			pst.setString(3,address);
			pst.setString(4, format);
			pst.setString(5, login_id);
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



	public void deleteUser(String login_ID) {
		Connection cone = null;

		cone = DBManager.getConnection();

		String sql="DELETE FROM user WHERE login_id = ?";

		try {
			PreparedStatement pst = cone.prepareStatement(sql);
			pst.setString(1, login_ID);
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


	}

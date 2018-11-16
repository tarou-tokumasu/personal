package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import beans.CateBeans;
import beans.ItemBeans;
import beans.MakerBeans;
import db.DBManager;

public class ItemDAO {


	//全商品取得
	public List<ItemBeans> getAllItem(){

		ArrayList<ItemBeans> itemList = new ArrayList<ItemBeans>();

		Connection cone = null;
		cone = DBManager.getConnection();

		String sql= "SELECT * FROM item "
				+ " inner join maker ON item.item_maker = maker.id "
				+ " inner join cate ON  item.item_cate = cate.id;";

		try {
			Statement st = cone.createStatement();
			ResultSet rs =st.executeQuery(sql);

			while(rs.next()) {

				int id = rs.getInt("id");
                String item_name = rs.getString("item_name");
                String item_cate = rs.getString("cate_name");
                String item_maker = rs.getString("maker_name");
                int item_price = rs.getInt("item_price");
                String item_pic = rs.getString("item_pic");
                Date item_date = rs.getDate("item_date");
                int item_price_down = rs.getInt("item_price_down");
                int cate_id =rs.getInt("item_cate");
                int maker_id=rs.getInt("item_maker");


                ItemBeans item = new ItemBeans(id, item_name ,cate_id, item_cate ,maker_id, item_maker , item_price , item_pic , item_date , item_price_down);

                itemList.add(item);
                System.out.println("アイテムデータ取得中");

			}
			System.out.println("出力");
			return itemList;

		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}finally {try {
			cone.close();
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}}





		return itemList;
	}

	public ItemBeans searchID(String idd) {
		Connection cone = null;
		cone = DBManager.getConnection();

		String sql= "SELECT * FROM item inner join maker ON item.item_maker = maker.id inner join cate ON  item.item_cate = cate.id  WHERE item.id = ? ";

		try {
			PreparedStatement pst = cone.prepareStatement(sql);
			pst.setString(1, idd);
			ResultSet rs = pst.executeQuery();

			if(rs.next()) {

				int id = rs.getInt("id");
                String item_name = rs.getString("item_name");
                String item_cate = rs.getString("cate_name");
                String item_maker = rs.getString("maker_name");
                int item_price = rs.getInt("item_price");
                String item_pic = rs.getString("item_pic");
                Date item_date = rs.getDate("item_date");
                int item_price_down = rs.getInt("item_price_down");
                int cate_id =rs.getInt("item_cate");
                int maker_id=rs.getInt("item_maker");
                //IDは要らん気がするけどELのifでサーブレットからセットされた文字列同士の比較が上手くいかないので用意


                return new ItemBeans(id, item_name , cate_id,item_cate ,maker_id, item_maker , item_price , item_pic , item_date , item_price_down);


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

return null;



	}

	public List<CateBeans> getAllCate() {
		Connection cone = null;
		cone = DBManager.getConnection();

		String sql= "SELECT * FROM cate ";

		ArrayList<CateBeans> cateList = new ArrayList<CateBeans>();

		try {
			Statement st = cone.createStatement();
			ResultSet rs =st.executeQuery(sql);

			while(rs.next()) {

				int id = rs.getInt("id");
                String cate_name = rs.getString("cate_name");
                CateBeans cate = new CateBeans(id,cate_name);
                cateList.add(cate);
			}

			return cateList;

		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}finally {try {
			cone.close();
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}}

		return cateList;
	}

	public List<MakerBeans> getAllMaker() {
		Connection cone = null;
		cone = DBManager.getConnection();

		String sql= "SELECT * FROM maker ";

		ArrayList<MakerBeans> makerList = new ArrayList<MakerBeans>();

		try {
			Statement st = cone.createStatement();
			ResultSet rs =st.executeQuery(sql);

			while(rs.next()) {

				int id = rs.getInt("id");
                String maker_name = rs.getString("maker_name");
                MakerBeans maker = new MakerBeans(id,maker_name);

                makerList.add(maker);
			}

			return makerList;

		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}finally {try {
			cone.close();
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}}

		return makerList;
	}

	public boolean chackName(String item_name) {
		Connection cone = null;

		   try {
			   cone =DBManager.getConnection();

			   //select
			   String sql = "SELECT * from item where item_name = ? ";

			   PreparedStatement pst = cone.prepareStatement(sql);
			   pst.setString(1, item_name);
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

	public void addItem(String item_name, String item_cate, String item_maker, String item_price,
			String item_price_down, String name, String format) {
		Connection cone = null;
		cone= DBManager.getConnection();

		String sql= "INSERT INTO item (item_name , item_cate , item_maker , item_price , item_pic , item_date , item_price_down) "
				+ " VALUES(?,?,?,?,?,?,?)";

		try {
			PreparedStatement pst = cone.prepareStatement(sql);
			pst.setString(1, item_name);
			pst.setString(2,item_cate);
			pst.setString(3,item_maker);
			pst.setString(4, item_price);
			pst.setString(5,name);
			pst.setString(6, format);
			pst.setString(7,item_price_down);
			pst.executeUpdate();


		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}finally
		{try {
			cone.close();
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}}

	}

	public void deleteItem(String item_id) {
		Connection cone = null;

		cone = DBManager.getConnection();

		String sql="DELETE FROM item  WHERE item_name = ?";

		try {
			PreparedStatement pst = cone.prepareStatement(sql);
			pst.setString(1, item_id);
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

	public void updateItem(String item_id, String item_cate, String item_maker, String item_price,
			String item_price_down) {
		Connection cone = null;



		cone = DBManager.getConnection();
		String sql = "UPDATE  item SET item_cate =? , item_maker= ? , item_price = ? , item_price_down = ? "
				+ " WHERE id = ? ";

		try {
			PreparedStatement pst = cone.prepareStatement(sql);
			pst.setString(1, item_cate);
			pst.setString(2, item_maker);
			pst.setString(3, item_price);
			pst.setString(4, item_price_down);
			pst.setString(5, item_id);

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

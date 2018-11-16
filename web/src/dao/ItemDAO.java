package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import beans.ItemBeans;
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


                ItemBeans item = new ItemBeans(id, item_name , item_cate , item_maker , item_price , item_pic , item_date , item_price_down);

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

		String sql= "SELECT * FROM item "
				+ " inner join maker ON item.item_maker = maker.id "
				+ " inner join cate ON  item.item_cate = cate.id;";

		try {
			Statement st = cone.createStatement();
			ResultSet rs =st.executeQuery(sql);

			if(rs.next()) {

				int id = rs.getInt("id");
                String item_name = rs.getString("item_name");
                String item_cate = rs.getString("cate_name");
                String item_maker = rs.getString("maker_name");
                int item_price = rs.getInt("item_price");
                String item_pic = rs.getString("item_pic");
                Date item_date = rs.getDate("item_date");
                int item_price_down = rs.getInt("item_price_down");

                return new ItemBeans(id, item_name , item_cate , item_maker , item_price , item_pic , item_date , item_price_down);


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


}

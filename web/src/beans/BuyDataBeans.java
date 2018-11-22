package beans;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BuyDataBeans {


	//id　買った人のID 配送方法とその値段　いつ買ったか　総額いくら？　配送名は？（値引き後の値段）
	private int id;
	private int user_id;
	private int total_price;
	private int deli_id;
	private Date buy_date;

	private String deli_name;
	private int deli_price;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public Date getBuy_date() {
		return buy_date;
	}
	public void setBuy_date(Date buy_date) {
		this.buy_date = buy_date;
	}

	public String getFormatDate() {
		SimpleDateFormat f = new SimpleDateFormat("yyyy年MM月dd日HH時mm分");
		return f.format(buy_date);
	}
	public int getDeli_id() {
		return deli_id;
	}
	public void setDeli_id(int deli_id) {
		this.deli_id = deli_id;
	}
	public String getDeli_name() {
		return deli_name;
	}
	public void setDeli_name(String deli_name) {
		this.deli_name = deli_name;
	}
	public int getTotal_price() {
		return total_price;
	}
	public void setTotal_price(int total_price) {
		this.total_price = total_price;
	}

	//カンマ付けて返す
	public String getitem_pricec () {
		NumberFormat nf = NumberFormat.getCurrencyInstance();
		return  nf.format(total_price);
	}

	public String getformatDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日HH時mm分");
		return sdf.format(buy_date);
}
	public int getDeli_price() {
		return deli_price;
	}
	public void setDeli_price(int deli_price) {
		this.deli_price = deli_price;
	}
}

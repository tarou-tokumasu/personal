package beans;

import java.text.NumberFormat;

public class BuyDetailBeans {

	//誰が何の商品をいくらで買ったか（価格は変動するため）
	private int id;
	private int item_id;
	private int buy_id;
	private int last_price;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getItem_id() {
		return item_id;
	}
	public void setItem_id(int item_id) {
		this.item_id = item_id;
	}
	public int getLast_price() {
		return last_price;
	}
	public void setLast_price(int last_price) {
		this.last_price = last_price;
	}

	//値段にカンマ付けて返す
	public String getitem_pricec () {
		NumberFormat nf = NumberFormat.getCurrencyInstance();
		return  nf.format(last_price);
	}
	public int getBuy_id() {
		return buy_id;
	}
	public void setBuy_id(int buy_id) {
		this.buy_id = buy_id;
	}


}

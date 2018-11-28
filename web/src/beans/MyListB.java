package beans;

import java.text.NumberFormat;

public class MyListB {

	private int id;
	private int user_id;
	private int item_id;
	private int item_price;
	private int item_price_down;
	private int upvote;
	private int downvote;
	private String item_name;


	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public int getItem_id() {
		return item_id;
	}
	public void setItem_id(int item_id) {
		this.item_id = item_id;
	}
	public int getItem_price() {
		return item_price;
	}
	public void setItem_price(int item_price) {
		this.item_price = item_price;
	}
	public int getItem_price_down() {
		return item_price_down;
	}
	public void setItem_price_down(int item_price_down) {
		this.item_price_down = item_price_down;
	}
	public int getUpvote() {
		return upvote;
	}
	public void setUpvote(int upvote) {
		this.upvote = upvote;
	}
	public int getDownvote() {
		return downvote;
	}
	public void setDownvote(int downvote) {
		this.downvote = downvote;
	}
	public String getItem_name() {
		return item_name;
	}
	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}

	//カンマ付けて返す
	public String getitem_pricec () {
		NumberFormat nf = NumberFormat.getCurrencyInstance();
		return  nf.format(item_price);
	}

	//割引後の値段を返す
	public String getitem_pricew () {
		int item_pricew = item_price * ( 100 - item_price_down ) / 100 ;
		NumberFormat nf = NumberFormat.getCurrencyInstance();
		return  nf.format(item_pricew);
	}

	//付けずに純粋に割引後の値段を返す
	public int getitem_pricez() {
		return item_price * ( 100 - item_price_down ) / 100 ;

	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}


}

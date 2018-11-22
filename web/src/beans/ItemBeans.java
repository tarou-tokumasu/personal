package beans;

import java.text.NumberFormat;
import java.util.Date;

public class ItemBeans {


	private int id;
	private String item_name;
	private int cate_id;
	private int maker_id;
	private String item_cate;
	private String item_maker;
	private int item_price;
	private String item_pic;
	private Date item_date;
	private int item_price_down;


	public ItemBeans(int id,String item_name ,  int cate_id , String item_cate2,  int maker_id ,String item_maker2, int item_price, String item_pic, Date item_date, int item_price_down) {
		this.id=id;
		this.cate_id = cate_id;
		this.maker_id = maker_id;
		this.item_name=item_name;
		this.item_cate=item_cate2;
		this.item_maker=item_maker2;
		this.item_price=item_price;
		this.item_pic=item_pic;
		this.item_date=item_date;
		this.item_price_down=item_price_down;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getItem_name() {
		return item_name;
	}


	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}


	public String  getItem_cate() {
		return item_cate;
	}


	public void setItem_cate(String item_cate) {
		this.item_cate = item_cate;
	}


	public String getItem_maker() {
		return item_maker;
	}


	public void setItem_maker(String item_maker) {
		this.item_maker = item_maker;
	}


	public int getItem_price() {
		return item_price;
	}


	public void setItem_price(int item_price) {
		this.item_price = item_price;
	}


	public String getItem_pic() {
		return item_pic;
	}


	public void setItem_pic(String item_pic) {
		this.item_pic = item_pic;
	}


	public Date getItem_date() {
		return item_date;
	}


	public void setItem_date(Date item_date) {
		this.item_date = item_date;
	}


	public int getItem_price_down() {
		return item_price_down;
	}


	public void setItem_price_down(int item_price_down) {
		this.item_price_down = item_price_down;
	}


	public int getCate_id() {
		return cate_id;
	}


	public void setCate_id(int cate_id) {
		this.cate_id = cate_id;
	}


	public int getMaker_id() {
		return maker_id;
	}


	public void setMaker_id(int maker_id) {
		this.maker_id = maker_id;
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
}

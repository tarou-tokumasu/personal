package beans;

import java.text.NumberFormat;

public class SalesB {

	private String date;
	private int sales;

	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getSales() {
		return sales;
	}
	public void setSales(int sales) {
		this.sales = sales;
	}

	//カンマ付けて返す
	public String getsalesc () {
		NumberFormat nf = NumberFormat.getCurrencyInstance();
		return  nf.format(sales);
	}
}

package beans;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ReviewBeans {

	private int id ;
	private int item_id; //どの商品か
	private int user_id; //誰が書いたか
	private String review; //本文
	private int vote; //1で高評価　-1で低評価
	private Date re_date;//いつ書いたか
	private int re_vote; //レビュー自体の評価

	private String reviewer ;//レビューしたユーザー名


	public ReviewBeans() {
		// TODO 自動生成されたコンストラクター・スタブ
	}



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



	public int getUser_id() {
		return user_id;
	}



	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}



	public String getReview() {
		return review;
	}



	public void setReview(String review) {
		this.review = review;
	}



	public int getVote() {
		return vote;
	}



	public void setVote(int vote) {
		this.vote = vote;
	}



	public Date getRe_date() {
		return re_date;
	}



	public void setRe_date(Date re_date) {
		this.re_date = re_date;
	}



	public int getRe_vote() {
		return re_vote;
	}



	public void setRe_vote(int re_vote) {
		this.re_vote = re_vote;
	}



	public String getReviewer() {
		return reviewer;
	}



	public void setReviewer(String reviewer) {
		this.reviewer = reviewer;
	}

	//日時返還
	public String getformaDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日HH時mm分");
		return sdf.format(re_date);
	}

	//+と-つける
	public String getplus() {
		String str= null;
			str = (re_vote >=0) ?
				"+" + re_vote:
				"" + re_vote;

		return str;
	}
}

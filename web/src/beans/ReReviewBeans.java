package beans;

public class ReReviewBeans {

	private int user_id ; //誰が書いたか
	private int review_id; //どのレビューに書いたか
	private int vote; //下した評価↑1 ↓-1

	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public int getReview_id() {
		return review_id;
	}
	public void setReview_id(int review_id) {
		this.review_id = review_id;
	}
	public int getVote() {
		return vote;
	}
	public void setVote(int vote) {
		this.vote = vote;
	}



}

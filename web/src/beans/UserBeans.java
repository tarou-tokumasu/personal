package beans;

import java.util.Date;

public class UserBeans {

	private int id;
	private String login_id;
	private String user_name;
	private String address;
	private Date birth_date;
	private String password;
	private Date create_date;
	private Date update_date;
	private int point;


	//ログインセッション用
	public UserBeans(int id , String login_id , String user_name) {
		this.id = id;
		this.login_id=login_id;
		this.user_name = user_name;
	}
	//全部詰め
	public UserBeans(int id , String login_id , String user_name , String address , Date birth_date, String password, Date create_date , Date update_date,int point) {
		this.id = id;
		this.login_id=login_id;
		this.user_name = user_name;
		this.address=address;
		this.birth_date = birth_date;
		this.password = password;
		this.create_date=create_date;
		this.update_date=update_date;
		this.point=point;
	}

	public UserBeans() {
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLogin_id() {
		return login_id;
	}
	public void setLogin_id(String login_id) {
		this.login_id = login_id;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Date getBirth_date() {
		return birth_date;
	}
	public void setBirth_date(Date birth_date) {
		this.birth_date = birth_date;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Date getCreate_date() {
		return create_date;
	}
	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}
	public Date getUpdate_date() {
		return update_date;
	}
	public void setUpdate_date(Date update_date) {
		this.update_date = update_date;
	}
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}

}

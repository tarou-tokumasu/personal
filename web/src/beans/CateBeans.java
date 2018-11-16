package beans;

public class CateBeans {
	private int id;
	private String cate_name;


	public CateBeans(int id, String cate_name) {
		this.id=id;
		this.cate_name=cate_name;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getCate_name() {
		return cate_name;
	}


	public void setCate_name(String cate_name) {
		this.cate_name = cate_name;
	}

}

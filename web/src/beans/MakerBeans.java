package beans;

public class MakerBeans {
	private int id;
	private String maker_name;

	public MakerBeans(int id, String maker_name) {

		this.id=id;
		this.maker_name=maker_name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMaker_name() {
		return maker_name;
	}

	public void setMaker_name(String maker_name) {
		this.maker_name = maker_name;
	}

}

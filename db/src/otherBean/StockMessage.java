package otherBean;

public class StockMessage {
	private String productName;
	private String shelfName;
	private int qty;
	private String category;
	private String brand;
	public StockMessage(){}
	public String getProductName() {
		return this.productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getShelfName() {
		return this.shelfName;
	}
	public void setShelfName(String shelfName){
		this.shelfName = shelfName;
	}
	public int getQty() {
		return this.qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}
	public String getCategory() {
		return this.category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getBrand() {
		return this.brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}
}

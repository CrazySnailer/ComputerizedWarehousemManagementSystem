package bean;

// Generated 2013-6-8 23:00:05 by Hibernate Tools 3.4.0.CR1

/**
 * Goodsdeliver generated by hbm2java
 */
public class Goodsdeliver implements java.io.Serializable {

	private Integer id;
	private Deliver deliver;
	private String stockOutId;
	private int qty;

	public Goodsdeliver() {
	}

	public Goodsdeliver(Deliver deliver, String stockOutId, int qty) {
		this.deliver = deliver;
		this.stockOutId = stockOutId;
		this.qty = qty;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Deliver getDeliver() {
		return this.deliver;
	}

	public void setDeliver(Deliver deliver) {
		this.deliver = deliver;
	}

	public String getStockOutId() {
		return this.stockOutId;
	}

	public void setStockOutId(String stockOutId) {
		this.stockOutId = stockOutId;
	}

	public int getQty() {
		return this.qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

}

package action;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import dao.StockInDao;
import dao.StockOutDao;

import sessionFactory.HibernateSessionFactory;
import bean.Goodsstockin;
import bean.Goodsstockout;
import bean.Stockin;
import bean.Stockout;

public class StockOutAction extends ActionSupport implements ModelDriven<Stockout>{
	private String stockoutId;
	private Stockout stockout = new Stockout();
	private String customer1;
	private List<Stockout> allStockout;
	private List<String> productName;
	private List<String> shelfName;
	private List<String> qty;
	public void setStockoutId(String stockoutId){
		this.stockoutId = stockoutId;
	}
	public String getStockoutId(){
		return this.stockoutId;
	}
	public void setCustomer1(String customer1){
		this.customer1 = customer1;
	}
	public String getCustomer1(){
		return this.customer1;
	}
	public void setAllStockout(List<Stockout> allStockout){
		this.allStockout = allStockout;
	}
	public List<Stockout> getAllStockout(){
		return this.allStockout;
	}
	public void setProductName(List<String> productName){
		this.productName = productName;
	}
	public List<String> getProductName(){
		return this.productName;
	}
	public void setShelfName(List<String> shelfName){
		this.shelfName = shelfName;
	}
	
	public List<String> getShelfName(){
		return this.shelfName;
	}
	public void setQty(List<String> qty){
		this.qty = qty;
	}
	public List<String> getQty(){
		return this.qty;
	}
	public Stockout getModel() {
		return this.stockout;
	}
	public String execute(){
		List<Goodsstockout> allGoodsStockout = new ArrayList<Goodsstockout>();
		Iterator<String> qtyIt = qty.iterator();
		Iterator<String> productIt = productName.iterator();
		Iterator<String> shelfIt = shelfName.iterator();
		while(productIt.hasNext()){
			Goodsstockout goodsStockout = new Goodsstockout();
			goodsStockout.setProductName(productIt.next());
			goodsStockout.setShelfName(shelfIt.next());
			goodsStockout.setQty(Integer.parseInt(qtyIt.next()));
			goodsStockout.setStockout(stockout);
			allGoodsStockout.add(goodsStockout);
		}
		StockOutDao stockoutDao = new StockOutDao();
		stockoutDao.addStockOut(stockout, allGoodsStockout);
		return SUCCESS;
	}
	
	//查找所有出库信息
	public String allFindStockout(){
		StockOutDao stockoutDao = new StockOutDao();
		allStockout = stockoutDao.findStockout();
		return "allFind";
	}
	
	public String findCustomer(){
		HttpServletRequest request = ServletActionContext.getRequest();
		System.out.println(stockoutId);
		StockOutDao stockoutDao = new StockOutDao();
		customer1 = stockoutDao.getCustomer(stockoutId);
		return "customer";
	}
}

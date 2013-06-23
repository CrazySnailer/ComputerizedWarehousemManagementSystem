package action;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import bean.Goodsstockin;
import bean.Stockin;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import dao.GoodsDao;
import dao.StockInDao;

public class StockInAction extends ActionSupport implements ModelDriven<Stockin>{
	
	private Stockin stockin = new Stockin();
	private List<String> productName;
	private List<String> shelfName;
	private List<String> qty; 
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
	public String execute(){
		List<Goodsstockin> allGoodsStockin = new ArrayList<Goodsstockin>();
		Iterator<String> qtyIt = qty.iterator();
		Iterator<String> productIt = productName.iterator();
		Iterator<String> shelfIt = shelfName.iterator();
		while(productIt.hasNext()){
			Goodsstockin goodsStockin = new Goodsstockin();
			goodsStockin.setProductName(productIt.next());
			goodsStockin.setShelfName(shelfIt.next());
			goodsStockin.setQty(Integer.parseInt(qtyIt.next()));
			goodsStockin.setStockin(stockin);
			allGoodsStockin.add(goodsStockin);
		}
		StockInDao stockInDao = new StockInDao();
		stockInDao.addStockIn(stockin, allGoodsStockin);
		return SUCCESS;
 		
	}

	@Override
	public Stockin getModel() {
		
		return this.stockin;
	}
}

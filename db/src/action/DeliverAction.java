package action;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import bean.Deliver;
import bean.Goodsdeliver;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import dao.DeliverDao;

public class DeliverAction extends ActionSupport implements ModelDriven<Deliver>{
	private Deliver deliver = new Deliver();
	private List<String> stockOutId;
	private List<String> qty;
	public Deliver getModel() {
		return this.deliver;
	}
	public void setQty(List<String> qty){
		this.qty = qty;
	}
	public List<String> getQty(){
		return this.qty;
	}
	public void setStockOutId(List<String> stockOutId){
		this.stockOutId =stockOutId;
	}
	public List<String> getStockOutId(){
		return this.stockOutId;
	}
	
	public String execute(){
		List<Goodsdeliver> allGoodsDeliver = new ArrayList<Goodsdeliver>();
		Iterator<String> qtyIt = qty.iterator();
		Iterator<String> stockOutIdIt = stockOutId.iterator();
		while(qtyIt.hasNext()){
			Goodsdeliver goodsDeliver = new Goodsdeliver();
			goodsDeliver.setStockOutId(stockOutIdIt.next());
			goodsDeliver.setQty(Integer.parseInt(qtyIt.next()));
			goodsDeliver.setDeliver(deliver);
			allGoodsDeliver.add(goodsDeliver);
		}
		DeliverDao deliverDao = new DeliverDao();
		deliverDao.addDeliver(deliver, allGoodsDeliver);
		return SUCCESS;
	}
}

package action;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import bean.Check;
import bean.Goodscheck;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import dao.CheckDao;

public class CheckAction extends ActionSupport implements ModelDriven<Check>{
	private Check check = new Check();
	private List<String> productName;
	private List<String> qty;
	private List<String> checkQty;
	public void setCheckQty(List<String> checkQty){
		this.checkQty = checkQty;
	}
	public List<String> getCheckQty(){
		return this.checkQty;
	}
	public void setProductName(List<String> productName){
		this.productName = productName;
	}
	public List<String> getProductName(){
		return this.productName;
	}
	public void setQty(List<String> qty){
		this.qty = qty;
	}
	public List<String> getQty(){
		return this.qty;
	}
	public Check getModel() {	
		return this.check;
	}
	public String execute(){
		List<Goodscheck> allGoodsCheck = new ArrayList<Goodscheck>();
		Iterator<String> qtyIt = qty.iterator();
		Iterator<String> productIt = productName.iterator();
		Iterator<String> checkQtyIt = checkQty.iterator();
		while(qtyIt.hasNext()){
			Goodscheck goodsCheck = new Goodscheck();
			goodsCheck.setCheckQty(Integer.parseInt(checkQtyIt.next()));
			goodsCheck.setProductName(productIt.next());
			goodsCheck.setQty(Integer.parseInt(qtyIt.next()));
			goodsCheck.setCheck(check);
			allGoodsCheck.add(goodsCheck);	
		}
		CheckDao checkDao = new CheckDao();
		checkDao.addCheck(check, allGoodsCheck);
		return SUCCESS;
	}
}

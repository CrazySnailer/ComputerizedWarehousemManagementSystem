package action;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import dao.GoodsStockinDao;

public class StockQtyAction extends ActionSupport{
	private int qty;
	public void setQty(int qty){
		this.qty = qty;
	}
	public int getQty(){
		return this.qty;
	}
	public String execute(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String productName = null;
		String shelfName = null;
		try {
			productName = new String(request.getParameter("productName").getBytes("ISO-8859-1"), "UTF-8");
			shelfName= new String(request.getParameter("shelfName").getBytes("ISO-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		GoodsStockinDao goodsStockinDao = new GoodsStockinDao();
		qty = goodsStockinDao.getQty(productName, shelfName);
		System.out.println(qty);
		return SUCCESS;
	}
	//根据商品名称取得所有货品的数量
	public String sumQty(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String productName = null;
		try {
			productName = new String(request.getParameter("productName").getBytes("ISO-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		System.out.println(productName);
		GoodsStockinDao goodsStockinDao = new GoodsStockinDao();
		qty = goodsStockinDao.getSumQty(productName);
//		System.out.println(qty);
		return "sumQty";
	}
}

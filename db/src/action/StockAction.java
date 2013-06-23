package action;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import otherBean.StockMessage;

import bean.Goods;
import bean.Goodsstockin;
import bean.Goodsstockout;
import bean.Stockout;

import com.opensymphony.xwork2.ActionSupport;

import dao.GoodsDao;
import dao.StockDao;
import dao.StockOutDao;

public class StockAction extends ActionSupport{
	public List<StockMessage> allStockMessage;
	private List<Goodsstockin> allGoodsstockin;
	private String product;
	private String shelf;
	private int totalPage;  //总页数
	private int pageNumber;  //当前页面
	private int pageSize;      //页面大小
	public void setPageSize(int pageSize){
		this.pageSize = pageSize;
	}
	public int getPageSize(){
		return this.pageSize;
	}
	public void setPageNumber(int pageNumber){
		this.pageNumber = pageNumber;
	}
	public int getPageNumber(){
		return this.pageNumber;
	}
	public void setTotalPage(int totalPage){
		this.totalPage = totalPage;
	}
	public int getTotalPage(){
		return this.totalPage;
	}
	public void setProduct(String product){
		this.product = product;
	}
	public String getProduct(){
		return this.product;
	}
	public void setShelf(String shelf){
		this.shelf = shelf;
	}
	public String getShelf(){
		return this.shelf;
	}
	public void setAllGoodsstockin(List<Goodsstockin> allGoodsstockin){
		this.allGoodsstockin = allGoodsstockin;
	}
	public List<Goodsstockin> getAllGoodsstockin(){
		return this.allGoodsstockin;
	}
	public void setAllStockMessage(List<StockMessage> allStockMessage){
		this.allStockMessage = allStockMessage;
	}
	public List<StockMessage> getAllStockMessage(){
		return this.allStockMessage;
	}
	public String execute(){
		HttpServletRequest request = ServletActionContext.getRequest();
		StockDao stockDao = new StockDao();
		String pageNumberStr =request.getParameter("pageNumber");
		String productName =request.getParameter("productName") ;
		String shelfName =request.getParameter("shelfName");
//		try {
//			shelfName = new String(request.getParameter("shelfName").getBytes("ISO-8859-1"),"UTF-8");
//			productName = new String(request.getParameter("shelfName").getBytes("ISO-8859-1"),"UTF-8");
//		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
//		}
		
		if(productName != null && shelfName != null)
		{
			try {
				productName = new String(productName.getBytes("ISO-8859-1"),"UTF-8");
				shelfName = new String(shelfName.getBytes("ISO-8859-1"),"UTF-8");
			
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		if(pageNumberStr == null || "".equals(pageNumberStr.trim()) ||"1".equals(pageNumberStr.trim()) ){
			pageNumberStr ="1";
		}	
		if(productName != null){
			product = productName;
		}
		if(shelfName != null){
			shelf = shelfName;
		}
		System.out.println(product);
		request.setAttribute("shelf", shelf);
		request.setAttribute("product", product);
		pageSize = 2;
		pageNumber = Integer.parseInt(pageNumberStr);
		totalPage =stockDao.getAmountGoodsstocin(product, shelf);
		if(totalPage % pageSize == 0){
			totalPage = totalPage/pageSize;
		}
		else{
			totalPage = totalPage/pageSize+1;
		}
		
		allGoodsstockin=stockDao.getGoodsstocin(product, shelf, pageNumber, pageSize);
		GoodsDao goodsDao = new GoodsDao();
		Iterator<Goodsstockin> it = allGoodsstockin.iterator();
		allStockMessage = new  ArrayList<StockMessage>();
		while(it.hasNext()){
			int outQty;
			Goods goods = new Goods();
			Goodsstockin goodsStockin = new Goodsstockin();
			goodsStockin = it.next();
			outQty = stockDao.findGoods(goodsStockin.getProductName());
			goods = goodsDao.findGoods(goodsStockin.getProductName());
			StockMessage stockMessage = new StockMessage();
			stockMessage.setProductName(goods.getProductName());
			stockMessage.setCategory(goods.getCategory());
			stockMessage.setBrand(goods.getBrand());
			stockMessage.setShelfName(goodsStockin.getShelfName());
			stockMessage.setQty(goodsStockin.getQty()-outQty);
			allStockMessage.add(stockMessage);
		}
		return SUCCESS;
	}		

}

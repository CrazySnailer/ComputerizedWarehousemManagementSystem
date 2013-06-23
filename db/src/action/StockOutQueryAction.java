package action;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import bean.Customer;
import bean.Goodsstockin;
import bean.Goodsstockout;
import bean.Stockin;
import bean.Stockout;
import bean.Customer;

import com.opensymphony.xwork2.ActionSupport;

import dao.StockInDao;
import dao.StockOutDao;

public class StockOutQueryAction extends ActionSupport{
	private List<Stockout> allStockout;
	private String startDate;
	private String endDate;
	private String customer;
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
	public void setStartDate(String startDate){
		this.startDate = startDate;
	}
	public String getStartDate(){
		return this.startDate;
	}
	public void setEndDate(String endDate){
		this.endDate = endDate;
	}
	public String getEndDate(){
		return this.endDate;
	}
	public void setCustomer(String customer){
		this.customer = customer;
	}
	public String getCustomer(){
		return this.customer;
	}
	public void setAllStockout(List<Stockout> allStockout){
		this.allStockout = allStockout;
	}
	public List<Stockout> getAllStockout(){
		return this.allStockout;
	}
	public String execute(){
		HttpServletRequest request = ServletActionContext.getRequest();
		StockOutDao stockoutDao = new StockOutDao();
		String customerName = null;
		String pageNumberStr = request.getParameter("pageNumber");
		String name = request.getParameter("customerName");
		String start = request.getParameter("start");
		String end = request.getParameter("end");
		if(pageNumberStr == null || "".equals(pageNumberStr.trim()) ||"1".equals(pageNumberStr.trim()) ){
			pageNumberStr ="1";
		}
		System.out.println(startDate);
		System.out.println(endDate);
		System.out.println(customer);
		customerName = customer;
		String startTime = startDate;
		String endTime = endDate;
		System.out.println(name+"woshi");
		if(name!=null){
			customerName = name;
		}
		if(start!=null){
			startTime = start;
		}
		if(end != null){
			endTime = end;
		}
		request.setAttribute("name", customerName);
		request.setAttribute("start", startTime);
		request.setAttribute("end", endTime);
		pageSize = 1;
		pageNumber = Integer.parseInt(pageNumberStr);
		totalPage =stockoutDao.queryStockoutAmount(startTime, endTime, customerName);
		if(totalPage % pageSize == 0){
			totalPage = totalPage/pageSize;
		}
		else{
			totalPage = totalPage/pageSize+1;
		}
		
		allStockout=stockoutDao.queryStockout(pageNumber,pageSize,startTime, endTime, customerName);
		Iterator<Stockout> it = allStockout.iterator();
		while(it.hasNext()){
			Set<Goodsstockout> set1 = it.next().getGoodsstockouts();
			Iterator<Goodsstockout> it1 = set1.iterator();
			while(it1.hasNext()){
				System.out.println(it1.next().getShelfName());
			
			}
		}
		return SUCCESS;
	}
}

package action;

import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import bean.Goodsstockin;
import bean.Stockin;
import bean.Vendor;

import com.opensymphony.xwork2.ActionSupport;

import dao.StockInDao;
import dao.VendorDao;

public class StockInQueryAction extends ActionSupport{
	private List<Stockin> allStockin;
	private String startDate;
	private String endDate;
	private String vendor;
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
	public void setVendor(String vendor){
		this.vendor = vendor;
	}
	public String getVendor(){
		return this.vendor;
	}
	public void setAllStockin(List<Stockin> allStockin){
		this.allStockin = allStockin;
	}
	public List<Stockin> getAllStockin(){
		return this.allStockin;
	}
	public String execute(){
		HttpServletRequest request = ServletActionContext.getRequest();
		StockInDao stockinDao = new StockInDao();
		String vendorName = null;
		String pageNumberStr = request.getParameter("pageNumber");
		String name = request.getParameter("vendorName");
		String start = request.getParameter("start");
		String end = request.getParameter("end");
		if(pageNumberStr == null || "".equals(pageNumberStr.trim()) ||"1".equals(pageNumberStr.trim()) ){
			pageNumberStr ="1";
		}
		System.out.println(name);
		System.out.println(start);
		System.out.println(end);
		vendorName = vendor;
		String startTime = startDate;
		String endTime = endDate;
		System.out.println(vendorName);
		//		request.setCharacterEncoding("UTF-8");
//			String name = new String(request.getParameter("vendorName").getBytes("ISO-8859-1"),"UTF-8");
//			String start = new String(request.getParameter("vendorName").getBytes("ISO-8859-1"),"UTF-8");
//			String end = new String(request.getParameter("vendorName").getBytes("ISO-8859-1"),"UTF-8");
//			System.out.println("cheshi"+name);
		if(name!=null){
			vendorName = name;
		}
		if(start!=null){
			startTime = start;
		}
		if(end != null){
			endTime = end;
		}
		request.setAttribute("name", vendorName);
		request.setAttribute("start", startTime);
		request.setAttribute("end", endTime);
		pageSize = 1;
		pageNumber = Integer.parseInt(pageNumberStr);
		System.out.println(vendorName);
		totalPage =stockinDao.queryStockinAmount(startTime, endTime, vendorName);
		if(totalPage % pageSize == 0){
			totalPage = totalPage/pageSize;
		}
		else{
			totalPage = totalPage/pageSize+1;
		}
		
		allStockin=stockinDao.queryStockin(pageNumber,pageSize,startTime, endTime, vendorName);
		Iterator<Stockin> it = allStockin.iterator();
		while(it.hasNext()){
			Set<Goodsstockin> set1 = it.next().getGoodsstockins();
			Iterator<Goodsstockin> it1 = set1.iterator();
			while(it1.hasNext()){
				System.out.println(it1.next().getShelfName());
			
			}
		}
		return SUCCESS;
	}
}

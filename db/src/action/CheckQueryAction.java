package action;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import bean.Goodscheck;
import bean.Check;

import com.opensymphony.xwork2.ActionSupport;

import dao.CheckDao;

public class CheckQueryAction extends ActionSupport{
	private List<Check> allCheck;
	private String startDate;
	private String endDate;
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
	public void setShelf(String shelf){
		this.shelf = shelf;
	}
	public String getShelf(){
		return this.shelf;
	}
	public void setAllCheck(List<Check> allCheck){
		this.allCheck = allCheck;
	}
	public List<Check> getAllCheck(){
		return this.allCheck;
	}
	public String execute(){
		HttpServletRequest request = ServletActionContext.getRequest();
		CheckDao CheckDao = new CheckDao();
		String shelfName = null;
		String pageNumberStr = request.getParameter("pageNumber");
		String name = request.getParameter("shelfName");
		String start = request.getParameter("start");
		String end = request.getParameter("end");
		if(pageNumberStr == null || "".equals(pageNumberStr.trim()) ||"1".equals(pageNumberStr.trim()) ){
			pageNumberStr ="1";
		}
		System.out.println(name);
		System.out.println(start);
		System.out.println(end);
		shelfName = shelf;
		String startTime = startDate;
		String endTime = endDate;
		System.out.println(shelfName);
		if(name!=null){
			shelfName = name;
		}
		if(start!=null){
			startTime = start;
		}
		if(end != null){
			endTime = end;
		}
		request.setAttribute("name", shelfName);
		request.setAttribute("start", startTime);
		request.setAttribute("end", endTime);
		pageSize = 1;
		pageNumber = Integer.parseInt(pageNumberStr);
		System.out.println(shelfName);
		totalPage =CheckDao.queryCheckAmount(startTime, endTime, shelfName);
		if(totalPage % pageSize == 0){
			totalPage = totalPage/pageSize;
		}
		else{
			totalPage = totalPage/pageSize+1;
		}
		
		allCheck=CheckDao.queryCheck(pageNumber,pageSize,startTime, endTime, shelfName);
		Iterator<Check> it = allCheck.iterator();
		while(it.hasNext()){
			Set<Goodscheck> set1 = it.next().getGoodschecks();
			Iterator<Goodscheck> it1 = set1.iterator();
			while(it1.hasNext()){
				System.out.println(it1.next().getProductName());
			
			}
		}
		return SUCCESS;
	}	
}

package action;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import bean.Deliver;
import bean.Goodsdeliver;
import dao.DeliverDao;

public class DeliverQueryAction extends ActionSupport{
	private List<Deliver> allDeliver;
	private String startDate;
	private String endDate;
	private String city;
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
	public void setCity(String city){
		this.city = city;
	}
	public String getCity(){
		return this.city;
	}
	public void setAllDeliver(List<Deliver> allDeliver){
		this.allDeliver = allDeliver;
	}
	public List<Deliver> getAllDeliver(){
		return this.allDeliver;
	}
	public String execute(){
		HttpServletRequest request = ServletActionContext.getRequest();
		DeliverDao DeliverDao = new DeliverDao();
		String cityName = null;
		String pageNumberStr = request.getParameter("pageNumber");
		String name = request.getParameter("cityName");
		String start = request.getParameter("start");
		String end = request.getParameter("end");
		if(pageNumberStr == null || "".equals(pageNumberStr.trim()) ||"1".equals(pageNumberStr.trim()) ){
			pageNumberStr ="1";
		}
		System.out.println(startDate);
		System.out.println(endDate);
		System.out.println(city);
		cityName = city;
		String startTime = startDate;
		String endTime = endDate;
		System.out.println(name+"woshi");
		if(name!=null){
			cityName = name;
		}
		if(start!=null){
			startTime = start;
		}
		if(end != null){
			endTime = end;
		}
		request.setAttribute("name", cityName);
		request.setAttribute("start", startTime);
		request.setAttribute("end", endTime);
		pageSize = 1;
		pageNumber = Integer.parseInt(pageNumberStr);
		totalPage =DeliverDao.queryDeliverAmount(startTime, endTime, cityName);
		if(totalPage % pageSize == 0){
			totalPage = totalPage/pageSize;
		}
		else{
			totalPage = totalPage/pageSize+1;
		}
		
		allDeliver=DeliverDao.queryDeliver(pageNumber,pageSize,startTime, endTime, cityName);
		Iterator<Deliver> it = allDeliver.iterator();
		while(it.hasNext()){
			Set<Goodsdeliver> set1 = it.next().getGoodsdelivers();
			Iterator<Goodsdeliver> it1 = set1.iterator();
			while(it1.hasNext()){
				System.out.println(it1.next().getDeliver());
			
			}
		}
		return SUCCESS;
	}
}

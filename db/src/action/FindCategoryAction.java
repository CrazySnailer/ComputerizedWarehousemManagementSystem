package action;

import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;


import org.apache.struts2.ServletActionContext;

import bean.Category;
import bean.Vendor;

import com.opensymphony.xwork2.ActionSupport;

import dao.GoodsDao;
import dao.VendorDao;

public class FindCategoryAction extends ActionSupport{
	private List<Category> allCategory;
	private String categoryName;
	private Category category1;
	private String category;
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
	public void setCategory(String category){
		this.category = category;
	}
	public String setCategory(){
		return this.category;
	}
	public void setAllCategory(List<Category> allCategory){
		this.allCategory = allCategory;
	}
	public List<Category> getAllCategory(){
		return this.allCategory;
	}
	public void setCategoryName(String categoryName){
		this.categoryName = categoryName;
	}
	public String getCategoryName(){
		return this.categoryName;
	}
	public void setCategory1(Category category1){
		this.category1 = category1;
	}
	public Category getCategory1(){
		return this.category1;
	}
	public String execute(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String pageNumberStr = request.getParameter("pageNumber");
		if(pageNumberStr == null || "".equals(pageNumberStr.trim()) ||"1".equals(pageNumberStr.trim()) ){
			pageNumberStr ="1";
		}
		System.out.println(categoryName);
		try {
			String name = new String(request.getParameter("categoryName").getBytes("ISO-8859-1"),"UTF-8");
			
			if(name.indexOf("?") == -1){  //没有vendorName参数传递时，此时name=="?"
				categoryName = name;
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}	
		request.setAttribute("name", categoryName);
		pageSize = 2;
		pageNumber = Integer.parseInt(pageNumberStr);
		GoodsDao goodsDao = new GoodsDao();
		System.out.println(categoryName);
		totalPage = goodsDao.productAmount(categoryName);
		if(totalPage % pageSize == 0){
			totalPage = totalPage/pageSize;
		}
		else{
			totalPage = totalPage/pageSize+1;
		}
		
		allCategory = goodsDao.findAllCategory(pageNumber, pageSize, categoryName);
		return SUCCESS;
	}
	
	public String findAll(){
		GoodsDao goodsDao = new GoodsDao();
		allCategory = goodsDao.allCategory();
		return "findAll";
	}
	
	public String showCategory(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String categoryName = null;
		try {
			categoryName = new String(request.getParameter("cate").getBytes("ISO-8859-1"),"UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		GoodsDao goodsDao = new GoodsDao();
		category1 = goodsDao.findCategory(categoryName);
		return "update";
	}
}

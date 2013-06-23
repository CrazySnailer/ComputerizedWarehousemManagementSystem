package action;

import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import bean.Brand;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import dao.GoodsDao;

public class BrandAction extends ActionSupport implements ModelDriven<Brand> {
	private List<Brand> allBrand;
	private Brand brand1;
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
	public void setAllBrand(List<Brand> allBrand){
		this.allBrand = allBrand;
	}
	public List<Brand> getAllBrand(){
		return this.allBrand;
	}
	public void setBrand1(Brand brand1){
		this.brand1 = brand1;
	}
	public Brand getBrand1(){
		return this.brand1;
	}
	private Brand brand = new Brand();
	
	@Override
	public Brand getModel() {
		return this.brand;
	}
	public String addBrand(){
		HttpServletRequest request = ServletActionContext.getRequest();
		GoodsDao goodsDao = new GoodsDao();
		boolean flag = goodsDao.judgeBrandName(brand.getBrandName());
		if(flag){
			goodsDao.saveBrand(brand);
			request.setAttribute("result", "增加成功");
		}else{
			request.setAttribute("result", "已经有该商品分类了，添加失败!");
		}
		
		return "add";
	}
	
	public String findBrand(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String pageNumberStr = request.getParameter("pageNumber");
		if(pageNumberStr == null || "".equals(pageNumberStr.trim()) ||"1".equals(pageNumberStr.trim()) ){
			pageNumberStr ="1";
		}
		String brandName = null;
		brandName = brand.getBrandName();
		try {
			String name = new String(request.getParameter("brandName").getBytes("ISO-8859-1"),"UTF-8");
			
			if(name.indexOf("?") == -1){  //没有vendorName参数传递时，此时name=="?"
				brandName = name;
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}	
		request.setAttribute("name", brandName);
		pageSize = 2;
		pageNumber = Integer.parseInt(pageNumberStr);
		GoodsDao goodsDao = new GoodsDao();
		System.out.println(brandName);
		totalPage = goodsDao.brandAmount(brandName);
		if(totalPage % pageSize == 0){
			totalPage = totalPage/pageSize;
		}
		else{
			totalPage = totalPage/pageSize+1;
		}
		
		allBrand = goodsDao.findAllBrand(pageNumber, pageSize, brandName);
		return "find";
	}
	
	public String execute(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String brandName = null;
		try {
			brandName = new String(request.getParameter("brandName").getBytes("ISO-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		System.out.println(brandName);
		GoodsDao goodsDao = new GoodsDao();
		brand1 = goodsDao.findBrand(brandName);
		return SUCCESS;
	}
	
	public String updateBrand(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String brandId = null;
		try {
			brandId = new String(request.getParameter("brandId").getBytes("ISO-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		int id = Integer.parseInt(brandId);
		request.setAttribute("result", "成功修改！");
		brand.setId(id);
		GoodsDao goodsDao = new GoodsDao();
		goodsDao.updateBrand(brand);
		return "update";
	}
	
	public String deleteBrand(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String brandId = null;
		try {
			brandId = new String(request.getParameter("brandId").getBytes("ISO-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		int id = Integer.parseInt(brandId);
		GoodsDao goodsDao = new GoodsDao();
		goodsDao.deleteBrand(id);
		return "delete";
	}
	
	public String allBrand(){
		GoodsDao goodsDao = new GoodsDao();
		allBrand = goodsDao.findAllBrand();
//		Iterator<Brand> it = allBrand.iterator();
//		System.out.println(it.next().getBrandName());
		return "allBrand";
	}
	
}

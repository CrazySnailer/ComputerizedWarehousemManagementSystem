package action;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;


import bean.Vendor;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import dao.VendorDao;

public class VendorAction extends ActionSupport implements ModelDriven<Vendor>{
	private Vendor vendor = new Vendor();
	private Vendor vendor1;
	private List<Vendor> allVendor;
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
	public void setVendor1(Vendor vendor1){
		this.vendor1 = vendor1;
	}
	public Vendor getVendor1(){
		return this.vendor1;
	}
	public Vendor getModel() {
		return this.vendor;
	}
	public void setAllVendor(List<Vendor> allVendor){
		this.allVendor = allVendor;
	}
	public List<Vendor> getAllVendor(){
		return this.allVendor;
	}
	//添加供应商
	public String addVendor(){
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("result", "成功保存商品数据！");
		VendorDao vendorDao = new VendorDao();
		vendorDao.addVendor(vendor);
		return "add";
	}
	//分页查询供应商
	public String findVendor(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String vendorName = null;
		String pageNumberStr = request.getParameter("pageNumber");
		if(pageNumberStr == null || "".equals(pageNumberStr.trim()) ||"1".equals(pageNumberStr.trim()) ){
			pageNumberStr ="1";
		}
		vendorName = vendor.getVendorName();
		System.out.println(vendorName);
		try {
			String name = new String(request.getParameter("vendorName").getBytes("ISO-8859-1"),"UTF-8");
			System.out.println("cheshi"+name);
			
			if(name.indexOf("?") == -1){  //没有vendorName参数传递时，此时name=="?"
				vendorName = name;
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	
		
		request.setAttribute("name", vendorName);
		pageSize = 2;
		pageNumber = Integer.parseInt(pageNumberStr);
		VendorDao vendorDao = new VendorDao();
		System.out.println(vendorName);
		totalPage = vendorDao.vendorAmount(vendorName);
		if(totalPage % pageSize == 0){
			totalPage = totalPage/pageSize;
		}
		else{
			totalPage = totalPage/pageSize+1;
		}
		allVendor = vendorDao.findVendor(pageNumber, pageSize, vendorName);
		return "find";
	}
	//显示供应商的信息
	public String showVendor(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String vendorName = null;
		try {
			vendorName = new String(request.getParameter("vendName").getBytes("ISO-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		VendorDao vendorDao = new VendorDao();
		vendor1 = vendorDao.findVendor(vendorName);
		return "show";
	}
	//更新供应商信息
	public String updateVendor(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String vendorId = null;
		try {
			vendorId = new String(request.getParameter("vendorId").getBytes("ISO-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		int id = Integer.parseInt(vendorId);
		request.setAttribute("result", "成功修改！");
		VendorDao vendorDao = new VendorDao();
		vendor.setId(id);
		vendorDao.updateVendor(vendor);
		return "update";
	}
	//删除供应商信息
	public String deleteVendor(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String vendorId = null;
		try {
			vendorId = new String(request.getParameter("vendorId").getBytes("ISO-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		int id = Integer.parseInt(vendorId);
		VendorDao vendorDao = new VendorDao();
		vendorDao.deleteVendor(id);
		return "delete";
	}
	
	//查找所有供应商
	public String findAllVendor(){
		VendorDao vendorDao = new VendorDao();
		allVendor = vendorDao.allFindVendor();
		return "allFind";
	}
}

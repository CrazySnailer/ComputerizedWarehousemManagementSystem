package action;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import bean.Customer;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import dao.CustomerDao;


public class CustomerAction extends ActionSupport implements ModelDriven<Customer>{
	private Customer customer = new Customer();
	private Customer customer1;
	private List<Customer> allCustomer;
	public Customer getModel() {
		
		return this.customer;
	}
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
	public void setCustomer1(Customer customer1){
		this.customer1 = customer1;
	}
	public Customer getCustomer1(){
		return this.customer1;
	}
	public void setAllCustomer(List<Customer> allCustomer){
		this.allCustomer = allCustomer;
	}
	public List<Customer> getAllCustomer(){
		return this.allCustomer;
	}
	//添加供应商
	public String addCustomer(){
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("result", "成功保存商品数据！");
		CustomerDao customerDao = new CustomerDao();
		customerDao.addCustomer(customer);
		return "add";
	}
	//分页查询供应商
	public String findCustomer(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String customerName = null;
		String pageNumberStr = request.getParameter("pageNumber");
		if(pageNumberStr == null || "".equals(pageNumberStr.trim()) ||"1".equals(pageNumberStr.trim()) ){
			pageNumberStr ="1";
		}
		customerName = customer.getCustomerName();
		System.out.println(customerName);
		try {
			String name = new String(request.getParameter("customerName").getBytes("ISO-8859-1"),"UTF-8");	
			if(name.indexOf("?") == -1){  //没有customerName参数传递时，此时name=="?"
				customerName = name;
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		request.setAttribute("name", customerName);
		pageSize = 2;
		pageNumber = Integer.parseInt(pageNumberStr);
		CustomerDao customerDao = new CustomerDao();
		System.out.println(customerName);
		totalPage = customerDao.customerAmount(customerName);
		if(totalPage % pageSize == 0){
			totalPage = totalPage/pageSize;
		}
		else{
			totalPage = totalPage/pageSize+1;
		}
		allCustomer = customerDao.allCustomer(pageNumber, pageSize, customerName);
		return "find";
	}
	//显示供应商的信息
	public String showCustomer(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String customerName = null;
		try {
			customerName = new String(request.getParameter("custName").getBytes("ISO-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		CustomerDao customerDao = new CustomerDao();
		customer1 = customerDao.findCustomer(customerName);
		return "show";
	}
	//更新客户信息
	public String updateCustomer(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String customerId = null;
		try {
			customerId = new String(request.getParameter("customerId").getBytes("ISO-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		int id = Integer.parseInt(customerId);
		request.setAttribute("result", "成功修改！");
		customer.setId(id);
		CustomerDao customerDao = new CustomerDao();
		customerDao.updateCustomer(customer);
		return "update";
	}
	//删除客户信息
	public String deleteCustomer(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String customerId = null;
		try {
			customerId = new String(request.getParameter("customerId").getBytes("ISO-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		int id = Integer.parseInt(customerId);
		CustomerDao customerDao = new CustomerDao();
		customerDao.deleteCustomer(id);
		return "delete";
	}
	//查找所有客户信息
	public String allFindCustomer(){
		CustomerDao customerDao = new CustomerDao();
		allCustomer = customerDao.allFindCustomer();
		return "allFind";
	}
	//查找所有客户信息
		public String allFindCustomer1(){
			CustomerDao customerDao = new CustomerDao();
			allCustomer = customerDao.allFindCustomer();
			return "allFind1";
		}
	
}

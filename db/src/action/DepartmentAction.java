package action;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import bean.Department;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import dao.DepartmentDao;

public class DepartmentAction extends ActionSupport implements ModelDriven<Department>{
	private Department department = new Department();
	private Department department1;
	private List<Department> allDepartment;
	public Department getModel() {
		
		return this.department;
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
	public void setDepartment1(Department department1){
		this.department1 = department1;
	}
	public Department getDepartment1(){
		return this.department1;
	}
	public void setAllDepartment(List<Department> allDepartment){
		this.allDepartment = allDepartment;
	}
	public List<Department> getAllDepartment(){
		return this.allDepartment;
	}
	//添加供应商
	public String addDepartment(){
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("result", "成功保存商品数据！");
		DepartmentDao departmentDao = new DepartmentDao();
		departmentDao.addDepartment(department);
		return "add";
	}
	//分页查询供应商
	public String findDepartment(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String departmentName = null;
		String pageNumberStr = request.getParameter("pageNumber");
		if(pageNumberStr == null || "".equals(pageNumberStr.trim()) ||"1".equals(pageNumberStr.trim()) ){
			pageNumberStr ="1";
		}
		departmentName = department.getDeptName();
		System.out.println(departmentName);
		try {
			String name = request.getParameter("departmentName");
			if(name != null){
				name = new String(name.getBytes("ISO-8859-1"),"UTF-8");	
				if(name.indexOf("?") == -1){  //没有DepartmentName参数传递时，此时name=="?"
					departmentName = name;
				}
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		request.setAttribute("name", departmentName);
		pageSize = 2;
		pageNumber = Integer.parseInt(pageNumberStr);
		DepartmentDao departmentDao = new DepartmentDao();
		System.out.println(departmentName);
		totalPage = departmentDao.departmentAmount(departmentName);
		if(totalPage % pageSize == 0){
			totalPage = totalPage/pageSize;
		}
		else{
			totalPage = totalPage/pageSize+1;
		}
		allDepartment = departmentDao.allDepartment(pageNumber, pageSize, departmentName);
		return "find";
	}
	//显示供应商的信息
	public String showDepartment(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String departmentName = null;
		try {
			departmentName = new String(request.getParameter("deptName").getBytes("ISO-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		System.out.println(departmentName);
		DepartmentDao departmentDao = new DepartmentDao();
		department1 = departmentDao.findDepartment(departmentName);
		return "show";
	}
	//更新客户信息
	public String updateDepartment(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String departmentId = null;
		try {
			departmentId = new String(request.getParameter("departmentId").getBytes("ISO-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		int id = Integer.parseInt(departmentId);
		request.setAttribute("result", "成功修改！");
		department.setId(id);
		DepartmentDao departmentDao = new DepartmentDao();
		departmentDao.updateDepartment(department);
		return "update";
	}
	//删除客户信息
	public String deleteDepartment(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String departmentId = null;
		try {
			departmentId = new String(request.getParameter("departmentId").getBytes("ISO-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		int id = Integer.parseInt(departmentId);
		DepartmentDao departmentDao = new DepartmentDao();
		departmentDao.deleteDepartment(id);
		return "delete";
	}
	//查找所有客户信息
	public String allFindDepartment(){
		DepartmentDao departmentDao = new DepartmentDao();
		allDepartment = departmentDao.allFindDepartment();
		return "allFind";
	}
	//查找所有客户信息
		public String allFindDepartment1(){
			DepartmentDao departmentDao = new DepartmentDao();
			allDepartment = departmentDao.allFindDepartment();
			return "allFind1";
		}
}

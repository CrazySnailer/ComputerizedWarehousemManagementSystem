package action;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import bean.Resources;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import dao.ResourcesDao;

public class ResourceAction extends ActionSupport implements ModelDriven<Resources>{
	private Resources resources = new Resources();
	public Resources getModel() {
		return this.resources;
	}
	private Resources resources1;
	private List<Resources> allResources;
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
	public void setResources1(Resources resources1){
		this.resources1 = resources1;
	}
	public Resources getResources1(){
		return this.resources1;
	}
	public void setAllResources(List<Resources> allResources){
		this.allResources = allResources;
	}
	public List<Resources> getAllResources(){
		return this.allResources;
	}
	//添加供应商
	public String addResources(){
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("result", "成功保存商品数据！");
		ResourcesDao resourcesDao = new ResourcesDao();
		resourcesDao.addResources(resources);
		return "add";
	}
	//分页查询供应商
	public String findResources(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String resourcesName = null;
		String pageNumberStr = request.getParameter("pageNumber");
		if(pageNumberStr == null || "".equals(pageNumberStr.trim()) ||"1".equals(pageNumberStr.trim()) ){
			pageNumberStr ="1";
		}
		resourcesName = resources.getResourceName();
		System.out.println(resourcesName);
		try {
			String name = request.getParameter("resourcesName");
			if(name != null){
				name = new String(name.getBytes("ISO-8859-1"),"UTF-8");	
				if(name.indexOf("?") == -1){  //没有ResourcesName参数传递时，此时name=="?"
					resourcesName = name;
				}
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		request.setAttribute("name", resourcesName);
		pageSize = 2;
		pageNumber = Integer.parseInt(pageNumberStr);
		ResourcesDao resourcesDao = new ResourcesDao();
		System.out.println(resourcesName);
		totalPage = resourcesDao.resourcesAmount(resourcesName);
		if(totalPage % pageSize == 0){
			totalPage = totalPage/pageSize;
		}
		else{
			totalPage = totalPage/pageSize+1;
		}
		allResources = resourcesDao.allResources(pageNumber, pageSize, resourcesName);
		return "find";
	}
	//显示供应商的信息
	public String showResources(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String resourcesName = null;
		try {
			resourcesName = new String(request.getParameter("resName").getBytes("ISO-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		System.out.println(resourcesName);
		ResourcesDao resourcesDao = new ResourcesDao();
		resources1 = resourcesDao.findResources(resourcesName);
		return "show";
	}
	//更新客户信息
	public String updateResources(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String resourcesId = null;
		try {
			resourcesId = new String(request.getParameter("resourcesId").getBytes("ISO-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		int id = Integer.parseInt(resourcesId);
		request.setAttribute("result", "成功修改！");
		resources.setId(id);
		ResourcesDao resourcesDao = new ResourcesDao();
		resourcesDao.updateResources(resources);
		return "update";
	}
	//删除客户信息
	public String deleteResources(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String resourcesId = null;
		try {
			resourcesId = new String(request.getParameter("resourcesId").getBytes("ISO-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		int id = Integer.parseInt(resourcesId);
		ResourcesDao resourcesDao = new ResourcesDao();
		resourcesDao.deleteResources(id);
		return "delete";
	}
	//查找所有客户信息
	public String allFindResources(){
		ResourcesDao resourcesDao = new ResourcesDao();
		allResources = resourcesDao.allFindResources();
		return "allFind";
	}
	//查找所有客户信息
		public String allFindResources1(){
			ResourcesDao ResourcesDao = new ResourcesDao();
			allResources = ResourcesDao.allFindResources();
			return "allFind1";
		}
}

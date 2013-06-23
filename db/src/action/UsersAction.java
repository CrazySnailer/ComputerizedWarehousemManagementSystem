package action;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import bean.Users;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import dao.UsersDao;

public class UsersAction extends ActionSupport implements ModelDriven<Users>{
	private Users users = new Users();
	public Users getModel() {
		return this.users;
	}
	private Users users1;
	private List<Users> allUsers;
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
	public void setUsers1(Users users1){
		this.users1 = users1;
	}
	public Users getUsers1(){
		return this.users1;
	}
	public void setAllUsers(List<Users> allUsers){
		this.allUsers = allUsers;
	}
	public List<Users> getAllUsers(){
		return this.allUsers;
	}
	//添加供应商
	public String addUsers(){
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("result", "成功保存商品数据！");
		UsersDao UsersDao = new UsersDao();
		System.out.println(users.getUsername()+"mingzi");
		UsersDao.addUsers(users);
		return "add";
	}
	//分页查询供应商
	public String findUsers(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String usersName = null;
		String pageNumberStr = request.getParameter("pageNumber");
		if(pageNumberStr == null || "".equals(pageNumberStr.trim()) ||"1".equals(pageNumberStr.trim()) ){
			pageNumberStr ="1";
		}
		usersName = users.getUsername();
		System.out.println(usersName);
		try {
			String name = request.getParameter("usersName");
			if(name != null){
				name = new String(name.getBytes("ISO-8859-1"),"UTF-8");	
				if(name.indexOf("?") == -1){  //没有usersName参数传递时，此时name=="?"
					usersName = name;
				}
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		request.setAttribute("name", usersName);
		pageSize = 2;
		pageNumber = Integer.parseInt(pageNumberStr);
		UsersDao usersDao = new UsersDao();
		System.out.println(usersName);
		totalPage = usersDao.usersAmount(usersName);
		if(totalPage % pageSize == 0){
			totalPage = totalPage/pageSize;
		}
		else{
			totalPage = totalPage/pageSize+1;
		}
		allUsers = usersDao.allUsers(pageNumber, pageSize, usersName);
		return "find";
	}
	//显示供应商的信息
	public String showUsers(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String usersName = null;
		String pageMes = null; //保留上一个页面的搜索信息
		try {
			usersName = new String(request.getParameter("userName").getBytes("ISO-8859-1"), "UTF-8");
			pageMes= new String(request.getParameter("pageMes").getBytes("ISO-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		request.setAttribute("pageMes", pageMes);
		System.out.println(usersName+"username");
		UsersDao usersDao = new UsersDao();
		users1 = usersDao.findUsers(usersName);
		if(pageMes.equals("''"))
		{
			return "update";
		}
		return "show";
	}
	//更新客户信息
	public String updateUsers(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String usersId = null;
		try {
			usersId = new String(request.getParameter("usersId").getBytes("ISO-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		int id = Integer.parseInt(usersId);
		request.setAttribute("result", "成功修改！");
		users.setId(id);
		UsersDao usersDao = new UsersDao();
		usersDao.updateUsers(users);
		return "update";
	}
	//删除客户信息
	public String deleteUsers(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String usersId = null;
		try {
			usersId = new String(request.getParameter("usersId").getBytes("ISO-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		int id = Integer.parseInt(usersId);
		UsersDao usersDao = new UsersDao();
		usersDao.deleteUsers(id);
		return "delete";
	}
	//查找所有客户信息
	public String allFindUsers(){
		UsersDao usersDao = new UsersDao();
		allUsers = usersDao.allFindUsers();
		return "allFind";
	}
	//查找所有客户信息
		public String allFindUsers1(){
			UsersDao usersDao = new UsersDao();
			allUsers = usersDao.allFindUsers();
			return "allFind1";
		}

}

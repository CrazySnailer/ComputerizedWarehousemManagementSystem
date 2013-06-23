package action;

import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import dao.UsersDao;

public class UserGrantsAction extends ActionSupport{
	private List<String> grantsManage;
	public void setGrantsManage(List<String> grantsManage){
		this.grantsManage = grantsManage;
	}
	public List<String> getGrantsMange(){
		return this.grantsManage;
	}
	public String execute(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String usersId = null;
		try {
			usersId = new String(request.getParameter("usersId").getBytes("ISO-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		int id = Integer.parseInt(usersId);
		Iterator<String> it = grantsManage.iterator();
		StringBuffer sb = new StringBuffer();
		while(it.hasNext()){
			sb.append(it.next()+" ");
		}
		String grants = sb.toString().trim();
		System.out.println(grants);
		UsersDao usersDao = new UsersDao();
		usersDao.insetGrants(id, grants);
		return SUCCESS;
	}
}

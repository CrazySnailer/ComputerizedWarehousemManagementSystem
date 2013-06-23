package action;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import dao.LoginDao;

public class LoginAction extends ActionSupport{
	private String username;
	private String password;
	public void setUsername(String username){
		this.username = username;
	}
	public String getUsername(){
		return this.username;
	}
	public void setPassword(String password){
		this.password = password;
	}
	public String getPassword(){
		return this.password;
	}
	public String execute(){
		HttpServletRequest request = ServletActionContext.getRequest();
		request.getSession().setAttribute("sm", null);
	    request.getSession().setAttribute("sq", null);
	    request.getSession().setAttribute("bim", null);
	    request.getSession().setAttribute("um", null);
	    LoginDao logindao = new LoginDao();
	    boolean flag = logindao.login(username, password);
	    if(flag){
	    	request.getSession().setAttribute("username", username);
	    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    	Date loginTime = new Date();
	    	LoginDao loginDao = new LoginDao();
	    	loginDao.updateUser(username, loginTime);
	    	String grants = loginDao.getGrants(username);
	    	if(grants == null)
	    	{
	    		return SUCCESS;
	    	}
	    	String[] grant = grants.split(" ");
	    	for(int i = 0 ; i < grant.length ; i++)
	    	{
	    		if(grant[i].equals("1")){
	    			request.getSession().setAttribute("sm", "ok");
	    		}
	    		if(grant[i].equals("2")){
	    			request.getSession().setAttribute("sq", "ok");
	    		}
	    		if(grant[i].equals("3")){
	    			request.getSession().setAttribute("bim", "ok");
	    		}
	    		if(grant[i].equals("4")){
	    			request.getSession().setAttribute("um", "ok");
	    		}
	    	}
	    	return SUCCESS;
	    }else{
	    	request.setAttribute("desc", "用户名或密码不正确");
	    	return INPUT;
	    }
	}
}

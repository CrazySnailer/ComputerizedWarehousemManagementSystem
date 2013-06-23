package action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import dao.GoodsDao;

public class DeleteCategoryAction extends ActionSupport{
	public String execute(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String intStr = request.getParameter("cateId");
		System.out.println("shanchu:"+intStr);
		int id = Integer.parseInt(intStr);
		GoodsDao goodsDao = new GoodsDao();
		goodsDao.deleteCategory(id);
		return SUCCESS;
	}
}

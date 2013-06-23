package action;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import bean.Category;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.sun.org.apache.bcel.internal.generic.GOTO_W;

import dao.GoodsDao;

public class UpdateCategoryAction extends ActionSupport implements ModelDriven<Category>{

	private Category category = new Category();
	public String execute(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String categoryId = null;
		try {
			categoryId = new String(request.getParameter("categoryId").getBytes("ISO-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		int id = Integer.parseInt(categoryId);
		request.setAttribute("result", "成功修改商品分类!");
		GoodsDao goodsDao = new GoodsDao();
		category.setId(id);
		goodsDao.updateCategory(category);
		
		return SUCCESS;
	}
	public Category getModel() {
		return this.category;
	}

}

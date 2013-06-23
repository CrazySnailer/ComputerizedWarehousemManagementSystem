package action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import bean.Category;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import dao.GoodsDao;

public class AddCategoryAction extends ActionSupport implements ModelDriven<Category>{
	private Category category = new Category();
	public String execute(){
		HttpServletRequest request = ServletActionContext.getRequest();
		GoodsDao goodsDao = new GoodsDao();
		boolean flag = goodsDao.judgeCategoryName(category.getCategoryName());
		if(flag){
			goodsDao.saveCategory(category);
			request.setAttribute("result", "增加成功");
		}else{
			request.setAttribute("result", "已经有该商品分类了，添加失败!");
		}
		
		
		
		return SUCCESS;
	}
	public Category getModel() {
		
		return this.category;
	}

}

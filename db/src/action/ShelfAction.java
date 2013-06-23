package action;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import bean.Shelf;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import dao.ShelfDao;


public class ShelfAction extends ActionSupport implements ModelDriven<Shelf>{
	private Shelf shelf = new Shelf();
	private Shelf shelf1;
	private List<Shelf> allShelf;
	
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
	public void setShelf1(Shelf shelf1){
		this.shelf1 = shelf1;
	}
	public Shelf getShelf1(){
		return this.shelf1;
	}
	public void setAllShelf(List<Shelf> allShelf){
		this.allShelf = allShelf;
	}
	public List<Shelf> getAllShelf(){
		return this.allShelf;
	}
	public Shelf getModel() {
		return this.shelf;
	}
	//添加供应商
	public String addShelf(){
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("result", "成功保存商品数据！");
		ShelfDao shelfDao = new ShelfDao();
		shelfDao.addShelf(shelf);
		return "add";
	}
	//分页查询供应商
	public String findShelf(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String shelfName = null;
		String pageNumberStr = request.getParameter("pageNumber");
		if(pageNumberStr == null || "".equals(pageNumberStr.trim()) ||"1".equals(pageNumberStr.trim()) ){
			pageNumberStr ="1";
		}
		shelfName = shelf.getShelfName();
		System.out.println(shelfName);
		try {
			String name = new String(request.getParameter("shelfName").getBytes("ISO-8859-1"),"UTF-8");	
			if(name.indexOf("?") == -1){  //没有ShelfName参数传递时，此时name=="?"
				shelfName = name;
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		request.setAttribute("name", shelfName);
		pageSize = 2;
		pageNumber = Integer.parseInt(pageNumberStr);
		ShelfDao shelfDao = new ShelfDao();
		System.out.println(shelfName);
		totalPage = shelfDao.shelfAmount(shelfName);
		if(totalPage % pageSize == 0){
			totalPage = totalPage/pageSize;
		}
		else{
			totalPage = totalPage/pageSize+1;
		}
		allShelf = shelfDao.allShelf(pageNumber, pageSize, shelfName);
		return "find";
	}
	//显示供应商的信息
	public String showShelf(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String shelfName = null;
		try {
			shelfName = new String(request.getParameter("shelfName").getBytes("ISO-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		ShelfDao shelfDao = new ShelfDao();
		shelf1 = shelfDao.findShelf(shelfName);
		return "show";
	}
	//更新供应商信息
	public String updateShelf(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String shelfId = null;
		try {
			shelfId = new String(request.getParameter("shelfId").getBytes("ISO-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		int id = Integer.parseInt(shelfId);
		request.setAttribute("result", "成功修改！");
		shelf.setId(id);
		ShelfDao shelfDao = new ShelfDao();
		shelfDao.updateShelf(shelf);
		return "update";
	}
	//删除供应商信息
	public String deleteShelf(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String shelfId = null;
		try {
			shelfId = new String(request.getParameter("shelfId").getBytes("ISO-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		int id = Integer.parseInt(shelfId);
		ShelfDao shelfDao = new ShelfDao();
		shelfDao.deleteShelf(id);
		return "delete";
	}
	
	//查找所有货架
	public String allFindShelf(){
		ShelfDao shelfDao = new ShelfDao();
		allShelf = shelfDao.allFindShelf();
		return "allFind";
	}
	//查找所有货架
	public String allFindShelf1(){
		ShelfDao shelfDao = new ShelfDao();
		allShelf = shelfDao.allFindShelf();
		return "allFind1";
	}
}

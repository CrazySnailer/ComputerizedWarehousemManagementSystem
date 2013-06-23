package action;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import bean.Goods;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import dao.GoodsDao;

public class ProductAction extends ActionSupport implements ModelDriven<Goods>{
	private Goods goods = new Goods();
	private Goods goods1;
	private List<Goods> allProduct;
	private int totalPage;  //总页数
	private int pageNumber;  //当前页面
	private int pageSize;      //页面大小
	public void setGoods1(Goods goods1){
		this.goods1 = goods1;
	}
	public Goods getGoods1(){
		return this.goods1;
	}
	public void setPageSize(){
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
	public void setAllProduct(List<Goods> allProduct){
		this.allProduct = allProduct;
	}
	public List<Goods> getAllProduct(){
		return this.allProduct;
	}
	public Goods getModel() {
		return this.goods;
	}
	public String addProduct(){
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("result", "成功保存商品数据！");
		GoodsDao goodsDao = new GoodsDao();
		goodsDao.saveProduct(goods);
		return "add";
	}
	//根据搜索内容模糊查找所有商品信息，并分页显示
	public String findProduct(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String productName = null;
		String pageNumberStr = request.getParameter("pageNumber");
		if(pageNumberStr == null || "".equals(pageNumberStr.trim())){
			pageNumberStr ="1";
		}
		productName = goods.getProductName();
		try {
			String name = new String(request.getParameter("productName").getBytes("ISO-8859-1"),"UTF-8");
			if(name.indexOf("?") == -1){  //没有productName参数传递时，此时name=="?"
				productName = name;
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	
		
		request.setAttribute("name", productName);
		pageSize = 2;
		pageNumber = Integer.parseInt(pageNumberStr);
		GoodsDao goodsDao = new GoodsDao();
		totalPage = goodsDao.productAmount(productName);
		if(totalPage % pageSize == 0){
			totalPage = totalPage/pageSize;
		}
		else{
			totalPage = totalPage/pageSize+1;
		}
		allProduct = goodsDao.findProduct(pageNumber, pageSize, productName);
		return "find";
	}
	//详细显示商品信息
	public String showProduct(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String productName = null;
		String pageMes = null; //保留上一个页面的搜索信息
		try {
			productName = new String(request.getParameter("proName").getBytes("ISO-8859-1"), "UTF-8");
			pageMes= new String(request.getParameter("pageMes").getBytes("ISO-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		request.setAttribute("pageMes", pageMes);
		GoodsDao goodsDao = new GoodsDao();
		goods1 = goodsDao.findGoods(productName);
		System.out.println(pageMes);
		if(pageMes.equals("''"))
		{
			return "update";
		}
		return "show";
	}
	//更新商品信息
	public String updateProduct(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String productId = null;
		try {
			productId = new String(request.getParameter("productId").getBytes("ISO-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		int id = Integer.parseInt(productId);
		request.setAttribute("result", "成功修改！");
		goods.setId(id);
		GoodsDao goodsDao = new GoodsDao();
		goodsDao.updateGoods(goods);
		return "update";
	}
	//删除商品信息
	public String deleteProduct(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String productId = null;
		try {
			productId = new String(request.getParameter("productId").getBytes("ISO-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		int id = Integer.parseInt(productId);
		GoodsDao goodsDao = new GoodsDao();
		goodsDao.deleteProduct(id);
		return "delete";
	}
	//查找所有商品
	public String allFindProduct(){
		GoodsDao goodsDao = new GoodsDao();
		allProduct = goodsDao.allFindProduct();
		return "allFind";
	}
	
	//查找所有商品
	public String allFindProduct1(){
		GoodsDao goodsDao = new GoodsDao();
		allProduct = goodsDao.allFindProduct();
		return "allFind1";
	}
}

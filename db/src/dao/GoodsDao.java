package dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import sessionFactory.HibernateSessionFactory;

import bean.Brand;
import bean.Category;
import bean.Goods;
import bean.Stockin;
import bean.Vendor;

public class GoodsDao {
	private Session session;
	private Query query;
	private Transaction ta;
	/*
	 * 以下是针对商品分类管理的一些操作
	 */
	//向数据库存入商品类别信息
	public void saveCategory(Category category){
		session = HibernateSessionFactory.getSession();
		ta = session.beginTransaction();
		session.save(category);
		ta.commit();
		session.close();
	}
	//判断是否商品分类的信息重名
	public boolean judgeCategoryName(String categoryName){
		boolean flag = true;
		session = HibernateSessionFactory.getSession();
		String hql = "from Category as category where category.categoryName = ?";
		query = session.createQuery(hql);
		query.setString(0, categoryName);
		Iterator<Category> it = query.list().iterator();
		if(it.hasNext()){
			flag = false;
		}
		session.close();
		return flag;
	}
	//根据商品分类名查找分类的总数
		public int categoryAmount(String categoryName){
			int count;
			Number amount;
			session = HibernateSessionFactory.getSession();
			String hql = "select count(*) from Category as category where category.categoryName like '%"+categoryName+"%'";
			query = session.createQuery(hql);
			amount = (Number) query.uniqueResult();
			System.out.println(query.uniqueResult());
			count = amount.intValue();
			return count;
		}
		//分页查询商品分类信息
		public List<Category> findAllCategory(int pageNumber ,int pageSize , String categoryName){
			session = HibernateSessionFactory.getSession();
			List<Category> allCategory = new ArrayList<Category>();
			String hql = "from Category as category where category.categoryName like ?";
			String param ="%"+categoryName+"%";
			query = session.createQuery(hql);
			query.setString(0, param);
			query.setFirstResult((pageNumber -1)*pageSize);
			query.setMaxResults(pageSize);
			allCategory = query.list();
			session.close();
			return allCategory;
		}
	//根据分类名称，查询该分类下的信息
	public Category findCategory(String categoryName){
		Category category = null;
		session = HibernateSessionFactory.getSession();
		String hql = "from Category as category where category.categoryName = ?";
		query = session.createQuery(hql);
		query.setString(0, categoryName);
		Iterator<Category> it = query.list().iterator();
		if(it.hasNext()){
			category = it.next();
		}
		return category;
	}
	//查询所有的分类信息
	public List<Category> allCategory(){
		session = HibernateSessionFactory.getSession();
		List<Category> allCategory = new ArrayList<Category>();
		String hql = "from Category";
		query = session.createQuery(hql);
		allCategory = query.list();
		session.close();
		return allCategory;
	}
	//修改商品分类表
	public void updateCategory(Category category1){
		session = HibernateSessionFactory.getSession();
//		Category category2 = findCategory(category1.getCategoryName());
		Category category = (Category)session.get(Category.class, category1.getId());
		
		category.setCategoryName(category1.getCategoryName());
		category.setParentcategory(category1.getParentcategory());
		category.setStatus(category1.getStatus());
		category.setTlevel(category1.getTlevel());
		ta = session.beginTransaction();
		session.update(category);
		ta.commit();
		session.close();
		
	}
	//删除商品分类表
	public void deleteCategory(int id){
		session = HibernateSessionFactory.getSession();
		Category category = (Category)session.get(Category.class, id);
		ta = session.beginTransaction();
		session.delete(category);
		ta.commit();
		session.close();
	}
	/*
	 * 以下是针对品牌分类的操作
	 */
	//增加品牌分类
	public void saveBrand(Brand brand){
		session = HibernateSessionFactory.getSession();
		ta = session.beginTransaction();
		session.save(brand);
		ta.commit();
		session.close();
	}
	//判断品牌是否重名
	public boolean judgeBrandName(String brandName){
		boolean flag = true;
		session = HibernateSessionFactory.getSession();
		String hql = "from Brand as brand where brand.brandName = ?";
		query = session.createQuery(hql);
		query.setString(0, brandName);
		Iterator<Brand> it = query.list().iterator();
		if(it.hasNext()){
			flag = false;
		}
		return flag;
	}
	//根据品牌分类名称，查找该品牌相关信息
	public Brand findBrand(String brandName){
		Brand brand = null;
		session = HibernateSessionFactory.getSession();
		String hql = "from Brand as brand where brand.brandName = ?";
		query = session.createQuery(hql);
		query.setString(0, brandName);
		Iterator<Brand> it = query.list().iterator();
		if(it.hasNext()){
			brand = it.next();
		}
		session.close();
		return brand;
	}
	//根据商品分类名查找分类的总数
	public int brandAmount(String brandName){
		int count;
		Number amount;
		session = HibernateSessionFactory.getSession();
		String hql = "select count(*) from Brand as brand where brand.brandName like '%"+brandName+"%'";
		query = session.createQuery(hql);
		amount = (Number) query.uniqueResult();
		System.out.println(query.uniqueResult());
		count = amount.intValue();
		return count;
	}
	//分页查询商品分类信息
	public List<Brand> findAllBrand(int pageNumber ,int pageSize , String brandName){
		session = HibernateSessionFactory.getSession();
		List<Brand> allBrand = new ArrayList<Brand>();
		String hql = "from Brand as brand where brand.brandName like ?";
		String param ="%"+brandName+"%";
		query = session.createQuery(hql);
		query.setString(0, param);
		query.setFirstResult((pageNumber -1)*pageSize);
		query.setMaxResults(pageSize);
		allBrand = query.list();
		session.close();
		return allBrand;
	}
	//查找所有品牌分类信息
	public List<Brand> findAllBrand(){
		List<Brand> allBrand = new ArrayList<Brand>();
		session = HibernateSessionFactory.getSession();
		String hql = "from Brand";
		query = session.createQuery(hql);
		allBrand = query.list();
		session.close();
		return allBrand;
	}
	//修改品牌分类里面的信息
	public void updateBrand(Brand brand2){
		session = HibernateSessionFactory.getSession();
		Brand brand = (Brand)session.get(Brand.class, brand2.getId());
		brand.setBrandName(brand2.getBrandName());
		brand.setComments(brand2.getComments());
		ta = session.beginTransaction();
		session.update(brand);
		ta.commit();
		session.close();
		
	}
	//删除一条品牌分类信息
	public void deleteBrand(int id){
		session = HibernateSessionFactory.getSession();
		Brand brand = (Brand)session.get(Brand.class, id);
		ta = session.beginTransaction();
		session.delete(brand);
		ta.commit();
		session.close();
	}
	/*
	 *以下的是相对于商品的操作 
	 *
	 */
	//增加商品信息的操作
	public void saveProduct(Goods goods){
		session = HibernateSessionFactory.getSession();
		ta = session.beginTransaction();
		session.save(goods);
		ta.commit();
		session.close();
	}
	//根据商品名称模糊、分页查询
	public List<Goods> findProduct(int pageNumber ,int pageSize , String goodsName){
		session = HibernateSessionFactory.getSession();
		List<Goods> allProduct = new ArrayList<Goods>();
		String hql = "from Goods as goods where goods.productName like '%"+goodsName+"%'";
		query = session.createQuery(hql);
		query.setFirstResult((pageNumber -1)*pageSize);
		query.setMaxResults(pageSize);
		allProduct = query.list();
		session.close();
		return allProduct;
	}
	//获取商品总数
	public int productAmount(String goodsName){
		int count;
		Number amount;
		session = HibernateSessionFactory.getSession();
		String hql = "select count(*) from Goods as goods where goods.productName like '%"+goodsName+"%'";
		query = session.createQuery(hql);
		amount = (Number) query.uniqueResult();
		System.out.println(query.uniqueResult());
		count = amount.intValue();
		return count;
	}
	//根据商品名称获取商品信息
	public Goods findGoods(String productName){
		Goods product = new  Goods();
		session = HibernateSessionFactory.getSession();
		String hql = "from Goods as goods where goods.productName = ?";
		query = session.createQuery(hql);
		query.setString(0, productName);
		Iterator<Goods> it = query.list().iterator();
		if(it.hasNext()){
			product = it.next();
		}
		session.close();
		return product;
	}
	//更新商品信息
	public void updateGoods(Goods goods){
		session = HibernateSessionFactory.getSession();
		Goods product = (Goods)session.get(Goods.class, goods.getId());
		product.setProductName(goods.getProductName());
		product.setBarcode(goods.getBarcode());
		product.setCategory(goods.getCategory());
		product.setBrand(goods.getBrand());
		product.setSpecification(goods.getSpecification());
		product.setThreshold(goods.getThreshold());
		product.setStatus(goods.getStatus());
		product.setCreateTime(goods.getCreateTime());
		product.setDescription(goods.getDescription());
		ta = session.beginTransaction();
		session.update(product);
		ta.commit();
		session.close();
	}
	
	//删除商品信息
	public void deleteProduct(int id){
		session = HibernateSessionFactory.getSession();
		Goods goods = (Goods)session.get(Goods.class, id);
		ta = session.beginTransaction();
		session.delete(goods);
		ta.commit();
		session.close();
	}
	//查找所有商品
	public List<Goods> allFindProduct(){
		session = HibernateSessionFactory.getSession();
		List<Goods> allProduct = new ArrayList<Goods>();
		String hql = "from Goods";
		query = session.createQuery(hql);
		allProduct = query.list();
		session.close();
		return allProduct;
	}
}

package dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import bean.Shelf;

import sessionFactory.HibernateSessionFactory;

public class ShelfDao {
	private Session session;
	private Query query;
	private Transaction ta;
	//添加供应商
	public void addShelf(Shelf shelf)
	{
		session = HibernateSessionFactory.getSession();
		ta = session.beginTransaction();
		session.save(shelf);
		ta.commit();
		session.close();
	}
	
	//根据供应商名查找供应商的总数
	public int shelfAmount(String shelfName){
		int count;
		Number amount;
		session = HibernateSessionFactory.getSession();
		String hql = "select count(*) from Shelf as shelf where shelf.shelfName like '%"+shelfName+"%'";
		query = session.createQuery(hql);
		amount = (Number) query.uniqueResult();
		System.out.println(query.uniqueResult());
		count = amount.intValue();
		return count;
	}
	//分页查询供应商
	public List<Shelf> allShelf(int pageNumber ,int pageSize , String shelfName){
		session = HibernateSessionFactory.getSession();
		List<Shelf> allShelf = new ArrayList<Shelf>();
		String hql = "from Shelf as shelf where shelf.shelfName like ?";
		String param ="%"+shelfName+"%";
		query = session.createQuery(hql);
		query.setString(0, param);
		query.setFirstResult((pageNumber -1)*pageSize);
		query.setMaxResults(pageSize);
		allShelf = query.list();
		session.close();
		return allShelf;
	}
	
	//根据商品名称获取商品信息
	public Shelf findShelf(String shelfName){
		Shelf shelf = new Shelf();
		session = HibernateSessionFactory.getSession();
		String hql = "from Shelf as shelf where shelf.shelfName = ?";
		query = session.createQuery(hql);
		query.setString(0, shelfName);
		Iterator<Shelf> it = query.list().iterator();
		if(it.hasNext()){
			shelf = it.next();
		}
		session.close();
		return shelf;
	}
	//更新货架信息
	public void updateShelf(Shelf shelf2){
		session = HibernateSessionFactory.getSession();
		Shelf shelf = (Shelf)session.get(Shelf.class,shelf2.getId());
		shelf.setComment(shelf2.getComment());
		shelf.setShelfName(shelf2.getShelfName());
		shelf.setStatus(shelf2.getStatus());
		ta = session.beginTransaction();
		session.update(shelf);
		ta.commit();
		session.close();
	}
	//删除货架信息
	public void deleteShelf(int id){
		session = HibernateSessionFactory.getSession();
		Shelf shelf = (Shelf)session.get(Shelf.class, id);
		ta = session.beginTransaction();
		session.delete(shelf);
		ta.commit();
		session.close();
	}
	//查找所有货架信息
	public List<Shelf> allFindShelf(){
		session = HibernateSessionFactory.getSession();
		List<Shelf> allShelf = new ArrayList<Shelf>();
		String hql = "from Shelf";
		query = session.createQuery(hql);
		allShelf = query.list();
		session.close();
		return allShelf;
	}

}

package dao;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import sessionFactory.HibernateSessionFactory;

public class GoodsStockinDao {
	private Session session;
	private Query query;
	private Transaction ta;
	public int getQty(String productName,String shelfName){
		int qty;
		session = HibernateSessionFactory.getSession();
		String hql = "select goodsStockin.qty from Goodsstockin goodsStockin where goodsStockin.productName = ? and goodsStockin.shelfName=?";
		query = session.createQuery(hql);
		query.setString(0, productName);
		query.setString(1, shelfName);
		qty = (Integer) query.uniqueResult();
		return qty;
		
	}
	
	//根据商品名称得到所有符合条件的数量之和
	public int getSumQty(String productName){
		int qty = 0;
		session = HibernateSessionFactory.getSession();
		String hql = "select sum(goodsStockin.qty) from Goodsstockin goodsStockin where goodsStockin.productName = ?";
		query = session.createQuery(hql);
		query.setString(0, productName);
		List list = query.list();
		Iterator it = list.iterator();
		while(it.hasNext()){
			long a = (Long) it.next();
			qty = new Long(a).intValue();
			
		}
		return qty;
		
	}
}

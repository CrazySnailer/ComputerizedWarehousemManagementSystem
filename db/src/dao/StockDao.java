package dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import sessionFactory.HibernateSessionFactory;

import bean.Goods;
import bean.Goodsstockin;
import bean.Goodsstockout;

public class StockDao {
	private Session session;
	private Query query;
	private Transaction ta;
	//根据商品名称或货架名称查找入库信息
	public List<Goodsstockin> getGoodsstocin(String productName,String shelfName,int pageNumber, int pageSize){
		List<Goodsstockin> allGoodsstockin = new ArrayList<Goodsstockin>();
		session = HibernateSessionFactory.getSession();
		StringBuffer sb = new StringBuffer("from Goodsstockin");
		if(productName.equals("0") && shelfName.equals("0"))
			return null;
		if(!productName.equals("0") && !shelfName.equals("0"))
		{
			sb.append(" where productName=? and shelfName=?");
			query = session.createQuery(sb.toString());
			query.setString(0, productName);
			query.setString(1, shelfName);
		}
		else if(!productName.equals("0"))
		{
			sb.append(" where productName=?");
			query = session.createQuery(sb.toString());
			query.setString(0, productName);
		}else if(!shelfName.equals("0")){
			sb.append(" where shelfName=?");
			query = session.createQuery(sb.toString());
			query.setString(0, shelfName);
		}
		query.setFirstResult((pageNumber -1)*pageSize);
		query.setMaxResults(pageSize);
		allGoodsstockin = query.list();
		return allGoodsstockin;
	}
	
	//根据商品名称或货架名称查找入库信息的总数
	public int getAmountGoodsstocin(String productName,String shelfName){
		int count;
		Number amount;
		session = HibernateSessionFactory.getSession();
		StringBuffer sb = new StringBuffer("select count(*) from Goodsstockin");
		if(productName.equals("0") && shelfName.equals("0"))
			return 0;
		if(!productName.equals("0") && !shelfName.equals("0"))
		{
			sb.append(" where productName=? and shelfName=?");
			System.out.println(sb.toString());
			query = session.createQuery(sb.toString());
			query.setString(0, productName);
			query.setString(1, shelfName);
		}
		else if(!productName.equals("0"))
		{
			sb.append(" where productName=?");
			System.out.println(sb.toString());
			query = session.createQuery(sb.toString());
			query.setString(0, productName);
		}else if(!shelfName.equals("0")){
			sb.append(" where shelfName=?");
			query = session.createQuery(sb.toString());
			query.setString(0, shelfName);
		}
		amount = (Number) query.uniqueResult();
		System.out.println(query.uniqueResult());
		count = amount.intValue();	
		return count;
	}	
	
	//根据商品名称获取出库数量
	public int findGoods(String productName){
		int count = 0;
		Number amount;
		System.out.println(productName);
		session = HibernateSessionFactory.getSession();
		String hql = "select qty from Goodsstockout where productName = ?";
		query = session.createQuery(hql);
		query.setString(0, productName);
		amount = (Number) query.uniqueResult();
		System.out.println(query.uniqueResult()+"dao zhe ge le meiyoune ");
		if(amount == null){
			return 0;
		}
		count = amount.intValue();	
		return count;
	}
}

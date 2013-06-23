package dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import sessionFactory.HibernateSessionFactory;
import bean.Goodsstockout;
import bean.Stockout;


public class StockOutDao {
	private Session session;
	private Query query;
	private Transaction ta;
	//保存出库数据信息
	public void addStockOut(Stockout stockout,List<Goodsstockout> allGoodsStockout){
		session = HibernateSessionFactory.getSession();
		ta = session.beginTransaction();
		session.save(stockout);
		Iterator<Goodsstockout> it = allGoodsStockout.iterator();
		while(it.hasNext()){
			session.save(it.next());
		}
		ta.commit();
		session.close();
	}
	
	//查找所有出库
	public List<Stockout> findStockout(){
		session = HibernateSessionFactory.getSession();
		List<Stockout> allStockout = new ArrayList<Stockout>();
		String hql = "from Stockout";
		query = session.createQuery(hql);
		allStockout = query.list();
		session.close();
		return allStockout;
	}
	//根据时间段和供应商的相关信息查询
	public List<Stockout> queryStockout(int pageNumber, int pageSize, String startDate,String endDate,String customer){
		List<Stockout> allStockout = new ArrayList<Stockout>();
		session = HibernateSessionFactory.getSession();
		StringBuffer sb = new StringBuffer("from Stockout");
		try{
			if(startDate.isEmpty() || endDate.isEmpty()){
				if(!customer.equals("0")){
					sb.append(" where customer = ?");
					query = session.createQuery(sb.toString());
					query.setString(0,customer);
					
				}
			}
			else{
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Date startDate1 = sdf.parse(startDate);
				Date endDate1 = sdf.parse(endDate);
				sb.append(" where stockOutDate between ? and ?");
				if(!customer.equals("0")){
					sb.append(" and customer = ?");
				}
				System.out.println(sb.toString());
				query = session.createQuery(sb.toString());
				query.setDate(0, startDate1);
				query.setDate(1, endDate1);
				if(!customer.equals("0")){
					query.setString(2, customer);
				}
				
			}
			query.setFirstResult((pageNumber -1)*pageSize);
			query.setMaxResults(pageSize);
			allStockout = query.list();
		}catch(Exception e){}
		
		
		return allStockout;
	}
	public int queryStockoutAmount(String startDate,String endDate,String customer){
		int count;
		Number amount;
		session = HibernateSessionFactory.getSession();
		StringBuffer sb = new StringBuffer("select count(*) from Stockout");
		try{
			if(startDate.isEmpty() || endDate.isEmpty()){
				if(!customer.equals("0")){
					sb.append(" where customer = ?");
					query = session.createQuery(sb.toString());
					query.setString(0,customer);
					
				}
			}
			else{
				
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Date startDate1 = sdf.parse(startDate);
				System.out.println(startDate1);
				Date endDate1 = sdf.parse(endDate);
				sb.append(" where stockOutDate between ? and ?");
				if(!customer.equals("0")){
					sb.append(" and customer = ?");
				}
				query = session.createQuery(sb.toString());
				query.setDate(0, startDate1);
				query.setDate(1, endDate1);
				
				if(!customer.equals("0")){
					query.setString(2, customer);
				}
				
			}
			
		}catch(Exception e){}
		amount = (Number) query.uniqueResult();
		System.out.println(query.uniqueResult());
		count = amount.intValue();
		return count;
	}
	
	//根据出库单号获取出库的客户
		public String getCustomer(String stockoutId){
			String customer;
			session = HibernateSessionFactory.getSession();
			String hql = "select customer from Stockout where stockOutId= ?";
			query = session.createQuery(hql);
			query.setString(0, stockoutId);
			customer = (String)query.uniqueResult();
			return customer;
		}
}

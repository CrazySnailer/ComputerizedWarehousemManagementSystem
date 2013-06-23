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
import bean.Deliver;
import bean.Goodsdeliver;
import bean.Deliver;


public class DeliverDao {
	private Session session;
	private Query query;
	private Transaction ta;
	
	public void addDeliver(Deliver deliver,List<Goodsdeliver> allGoodsDeliver){
		session = HibernateSessionFactory.getSession();
		ta = session.beginTransaction();
		session.save(deliver);
		Iterator<Goodsdeliver> it = allGoodsDeliver.iterator();
		while(it.hasNext()){
			session.save(it.next());
		}
		ta.commit();
		session.close();
	}
	
	//根据时间段和供应商的相关信息查询
	public List<Deliver> queryDeliver(int pageNumber, int pageSize, String startDate,String endDate,String city){
		List<Deliver> allDeliver = new ArrayList<Deliver>();
		session = HibernateSessionFactory.getSession();
		StringBuffer sb = new StringBuffer("from Deliver");
		try{
			if(startDate.isEmpty() || endDate.isEmpty()){
				if(!city.equals("0")){
					sb.append(" where city = ?");
					query = session.createQuery(sb.toString());
					query.setString(0,city);
					
				}
			}
			else{
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Date startDate1 = sdf.parse(startDate);
				Date endDate1 = sdf.parse(endDate);
				sb.append(" where deliverDate between ? and ?");
				if(!city.equals("0")){
					sb.append(" and city = ?");
				}
				System.out.println(sb.toString());
				query = session.createQuery(sb.toString());
				query.setDate(0, startDate1);
				query.setDate(1, endDate1);
				if(!city.equals("0")){
					query.setString(2, city);
				}
				
			}
			query.setFirstResult((pageNumber -1)*pageSize);
			query.setMaxResults(pageSize);
			allDeliver = query.list();
		}catch(Exception e){}
		
		
		return allDeliver;
	}
	public int queryDeliverAmount(String startDate,String endDate,String city){
		int count;
		Number amount;
		session = HibernateSessionFactory.getSession();
		StringBuffer sb = new StringBuffer("select count(*) from Deliver");
		try{
			if(startDate.isEmpty() || endDate.isEmpty()){
				if(!city.equals("0")){
					sb.append(" where city = ?");
					query = session.createQuery(sb.toString());
					query.setString(0,city);
					
				}
			}
			else{
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Date startDate1 = sdf.parse(startDate);
				Date endDate1 = sdf.parse(endDate);
				System.out.println(endDate1);
				sb.append(" where deliverDate between ? and ?");
				if(!city.equals("0")){
					sb.append(" and city = ?");
				}
				System.out.println(sb.toString());
				query = session.createQuery(sb.toString());
				query.setDate(0, startDate1);
				query.setDate(1, endDate1);
				if(!city.equals("0")){
					query.setString(2, city);
				}
				
			}
			
		}catch(Exception e){}
		
		amount = (Number) query.uniqueResult();
		System.out.println(query.uniqueResult());
		count = amount.intValue();
		return count;
	}
}

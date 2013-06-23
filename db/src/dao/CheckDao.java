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
import bean.Check;
import bean.Goodscheck;



public class CheckDao {
	private Session session;
	private Query query;
	private Transaction ta;
	
	public void addCheck(Check check,List<Goodscheck> allGoodsCheck){
		session = HibernateSessionFactory.getSession();
		ta = session.beginTransaction();
		session.save(check);
		Iterator<Goodscheck> it = allGoodsCheck.iterator();
		while(it.hasNext()){
			session.save(it.next());
		}
		ta.commit();
		session.close();
	}
	//根据时间段和供应商的相关信息查询
	public List<Check> queryCheck(int pageNumber, int pageSize, String startDate,String endDate,String shelf){
		List<Check> allCheck = new ArrayList<Check>();
		session = HibernateSessionFactory.getSession();
		StringBuffer sb = new StringBuffer("from Check");
		try{
			if(startDate.isEmpty() || endDate.isEmpty()){
				if(!shelf.equals("0")){
					sb.append(" where shelf = ?");
					query = session.createQuery(sb.toString());
					query.setString(0,shelf);
					
				}
			}
			else{
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Date startDate1 = sdf.parse(startDate);
				Date endDate1 = sdf.parse(endDate);
				sb.append(" where checkDate between ? and ?");
				if(!shelf.equals("0")){
					sb.append(" and shelf = ?");
				}
				System.out.println(sb.toString());
				query = session.createQuery(sb.toString());
				query.setDate(0, startDate1);
				query.setDate(1, endDate1);
				if(!shelf.equals("0")){
					query.setString(2, shelf);
				}
				
			}
			query.setFirstResult((pageNumber -1)*pageSize);
			query.setMaxResults(pageSize);
			allCheck = query.list();
		}catch(Exception e){}
		
		
		return allCheck;
	}
	public int queryCheckAmount(String startDate,String endDate,String shelf){
		int count;
		Number amount;
		session = HibernateSessionFactory.getSession();
		StringBuffer sb = new StringBuffer("select count(*) from Check");
		try{
			if(startDate.isEmpty() || endDate.isEmpty()){
				if(!shelf.equals("0")){
					sb.append(" where shelf = ?");
					query = session.createQuery(sb.toString());
					query.setString(0,shelf);
					
				}
			}
			else{
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Date startDate1 = sdf.parse(startDate);
				Date endDate1 = sdf.parse(endDate);
				System.out.println(endDate1);
				sb.append(" where checkDate between ? and ?");
				if(!shelf.equals("0")){
					sb.append(" and shelf = ?");
				}
				System.out.println(sb.toString());
				query = session.createQuery(sb.toString());
				query.setDate(0, startDate1);
				query.setDate(1, endDate1);
				if(!shelf.equals("0")){
					query.setString(2, shelf);
				}
				
			}
			
		}catch(Exception e){}
		
		amount = (Number) query.uniqueResult();
		System.out.println(query.uniqueResult());
		count = amount.intValue();
		return count;
	}
}

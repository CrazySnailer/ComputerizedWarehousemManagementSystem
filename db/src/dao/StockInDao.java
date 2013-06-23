package dao;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;


import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import freemarker.template.utility.DateUtil;





import sessionFactory.HibernateSessionFactory;
import bean.Goodsstockin;
import bean.Stockin;

public class StockInDao {
	private Session session;
	private Query query;
	private Transaction ta;
	//保存入库数据信息
	public void addStockIn(Stockin stockin,List<Goodsstockin> allGoodsStockin){
		session = HibernateSessionFactory.getSession();
		ta = session.beginTransaction();
		session.save(stockin);
		Iterator<Goodsstockin> it = allGoodsStockin.iterator();
		while(it.hasNext()){
			session.save(it.next());
		}
		ta.commit();
		session.close();
	}
	//根据时间段和供应商的相关信息查询
	public List<Stockin> queryStockin(int pageNumber, int pageSize, String startDate,String endDate,String vendor){
		List<Stockin> allStockin = new ArrayList<Stockin>();
		session = HibernateSessionFactory.getSession();
		StringBuffer sb = new StringBuffer("from Stockin");
		try{
			if(startDate.isEmpty() || endDate.isEmpty()){
				if(!vendor.equals("0")){
					sb.append(" where vendor = ?");
					query = session.createQuery(sb.toString());
					query.setString(0,vendor);
					
				}
			}
			else{
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Date startDate1 = sdf.parse(startDate);
				Date endDate1 = sdf.parse(endDate);
				sb.append(" where stockInDate between ? and ?");
				if(!vendor.equals("0")){
					sb.append(" and vendor = ?");
				}
				System.out.println(sb.toString());
				query = session.createQuery(sb.toString());
				query.setDate(0, startDate1);
				query.setDate(1, endDate1);
				if(!vendor.equals("0")){
					query.setString(2, vendor);
				}
				
			}
			query.setFirstResult((pageNumber -1)*pageSize);
			query.setMaxResults(pageSize);
			allStockin = query.list();
		}catch(Exception e){}
		
		
		return allStockin;
	}
	public int queryStockinAmount(String startDate,String endDate,String vendor){
		int count;
		Number amount;
		session = HibernateSessionFactory.getSession();
		StringBuffer sb = new StringBuffer("select count(*) from Stockin");
		try{
			if(startDate.isEmpty() || endDate.isEmpty()){
				if(!vendor.equals("0")){
					sb.append(" where vendor = ?");
					query = session.createQuery(sb.toString());
					query.setString(0,vendor);
					
				}
			}
			else{
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Date startDate1 = sdf.parse(startDate);
				Date endDate1 = sdf.parse(endDate);
				System.out.println(endDate1);
				sb.append(" where stockInDate between ? and ?");
				if(!vendor.equals("0")){
					sb.append(" and vendor = ?");
				}
				System.out.println(sb.toString());
				query = session.createQuery(sb.toString());
				query.setDate(0, startDate1);
				query.setDate(1, endDate1);
				if(!vendor.equals("0")){
					query.setString(2, vendor);
				}
				
			}
			
		}catch(Exception e){}
		
		amount = (Number) query.uniqueResult();
		System.out.println(query.uniqueResult());
		count = amount.intValue();
		return count;
	}
	
	
}

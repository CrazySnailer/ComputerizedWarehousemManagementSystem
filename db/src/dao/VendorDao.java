package dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import sessionFactory.HibernateSessionFactory;


import bean.Vendor;

public class VendorDao {
	private Session session;
	private Query query;
	private Transaction ta;
	//添加供应商
	public void addVendor(Vendor vendor)
	{
		session = HibernateSessionFactory.getSession();
		ta = session.beginTransaction();
		session.save(vendor);
		ta.commit();
		session.close();
	}
	//根据供应商名查找供应商的总数
	public int vendorAmount(String vendorName){
		int count;
		Number amount;
		session = HibernateSessionFactory.getSession();
		String hql = "select count(*) from Vendor as vendor where vendor.vendorName like '%"+vendorName+"%'";
		query = session.createQuery(hql);
		amount = (Number) query.uniqueResult();
		System.out.println(query.uniqueResult());
		count = amount.intValue();
		return count;
	}
	//分页查询供应商
	public List<Vendor> findVendor(int pageNumber ,int pageSize , String vendorName){
		session = HibernateSessionFactory.getSession();
		List<Vendor> allVendor = new ArrayList<Vendor>();
		String hql = "from Vendor as vendor where vendor.vendorName like ?";
		String param ="%"+vendorName+"%";
		query = session.createQuery(hql);
		query.setString(0, param);
		query.setFirstResult((pageNumber -1)*pageSize);
		query.setMaxResults(pageSize);
		allVendor = query.list();
		session.close();
		return allVendor;
	}
	
	//根据商品名称获取商品信息
	public Vendor findVendor(String vendorName){
		Vendor vendor = new Vendor();
		session = HibernateSessionFactory.getSession();
		String hql = "from Vendor as vendor where vendor.vendorName = ?";
		query = session.createQuery(hql);
		query.setString(0, vendorName);
		Iterator<Vendor> it = query.list().iterator();
		if(it.hasNext()){
			vendor = it.next();
		}
		session.close();
		return vendor;
	}
	//更新商品信息
	public void updateVendor(Vendor vendor2){
		session = HibernateSessionFactory.getSession();
		Vendor vendor = (Vendor)session.get(Vendor.class, vendor2.getId());
		vendor.setVendorName(vendor2.getVendorName());
		vendor.setAddress(vendor2.getAddress());
		vendor.setCityId(vendor2.getCityId());
		vendor.setContact(vendor2.getContact());
		vendor.setPostcode(vendor2.getPostcode());
		vendor.setStatus(vendor2.getStatus());
		vendor.setTelephone(vendor2.getTelephone());
		ta = session.beginTransaction();
		session.update(vendor);
		ta.commit();
		session.close();
	}
	//删除商品信息
	public void deleteVendor(int id){
		session = HibernateSessionFactory.getSession();
		Vendor vendor = (Vendor)session.get(Vendor.class, id);
		ta = session.beginTransaction();
		session.delete(vendor);
		ta.commit();
		session.close();
	}
	
	//查找所有供应商消息
	public List<Vendor> allFindVendor(){
		session = HibernateSessionFactory.getSession();
		List<Vendor> allVendor = new ArrayList<Vendor>();
		String hql = "from Vendor";
		query = session.createQuery(hql);
		allVendor = query.list();
		session.close();
		return allVendor;
	}
}

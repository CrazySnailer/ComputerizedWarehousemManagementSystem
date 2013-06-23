package dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import sessionFactory.HibernateSessionFactory;
import bean.Resources;

public class ResourcesDao {
	private Session session;
	private Query query;
	private Transaction ta;
	//添加供应商
	public void addResources(Resources resources)
	{
		session = HibernateSessionFactory.getSession();
		ta = session.beginTransaction();
		session.save(resources);
		ta.commit();
		session.close();
	}
	
	//根据供应商名查找供应商的总数
	public int resourcesAmount(String resourcesName){
		int count;
		Number amount;
		session = HibernateSessionFactory.getSession();
		String hql = "select count(*) from Resources as resources where resources.resourceName like '%"+resourcesName+"%'";
		query = session.createQuery(hql);
		amount = (Number) query.uniqueResult();
		System.out.println(query.uniqueResult());
		count = amount.intValue();
		return count;
	}
	//分页查询供应商
	public List<Resources> allResources(int pageNumber ,int pageSize , String resourcesName){
		session = HibernateSessionFactory.getSession();
		List<Resources> allResources = new ArrayList<Resources>();
		String hql = "from Resources as resources where resources.resourceName like ?";
		String param ="%"+resourcesName+"%";
		query = session.createQuery(hql);
		query.setString(0, param);
		query.setFirstResult((pageNumber -1)*pageSize);
		query.setMaxResults(pageSize);
		allResources = query.list();
		session.close();
		return allResources;
	}
	
	//根据商品名称获取商品信息
	public Resources findResources(String resourcesName){
		Resources resources = new Resources();
		session = HibernateSessionFactory.getSession();
		String hql = "from Resources as resources where resources.resourceName = ?";
		query = session.createQuery(hql);
		query.setString(0, resourcesName);
		Iterator<Resources> it = query.list().iterator();
		if(it.hasNext()){
			resources = it.next();
		}
		session.close();
		return resources;
	}
	//更新客户信息
	public void updateResources(Resources resources2){
		session = HibernateSessionFactory.getSession();
		Resources resources = (Resources)session.get(Resources.class,resources2.getId());
		resources.setComments(resources2.getComments());
		resources.setCreateTime(resources2.getCreateTime());
		resources.setResourceName(resources2.getResourceName());
		resources.setStatus(resources2.getStatus());
		ta = session.beginTransaction();
		session.update(resources);
		ta.commit();
		session.close();
	}
	//删除客户信息
	public void deleteResources(int id){
		session = HibernateSessionFactory.getSession();
		Resources resources = (Resources)session.get(Resources.class, id);
		ta = session.beginTransaction();
		session.delete(resources);
		ta.commit();
		session.close();
	}
	//查找所有客户信息
	public List<Resources> allFindResources(){
		session = HibernateSessionFactory.getSession();
		List<Resources> allResources = new ArrayList<Resources>();
		String hql = "from Resources";
		query = session.createQuery(hql);
		allResources = query.list();
		session.close();
		return allResources;
	}
}

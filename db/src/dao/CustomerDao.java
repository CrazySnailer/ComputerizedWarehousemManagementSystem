package dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import sessionFactory.HibernateSessionFactory;
import bean.Customer;

public class CustomerDao {
	private Session session;
	private Query query;
	private Transaction ta;
	//添加供应商
	public void addCustomer(Customer customer)
	{
		session = HibernateSessionFactory.getSession();
		ta = session.beginTransaction();
		session.save(customer);
		ta.commit();
		session.close();
	}
	
	//根据供应商名查找供应商的总数
	public int customerAmount(String customerName){
		int count;
		Number amount;
		session = HibernateSessionFactory.getSession();
		String hql = "select count(*) from Customer as customer where customer.customerName like '%"+customerName+"%'";
		query = session.createQuery(hql);
		amount = (Number) query.uniqueResult();
		System.out.println(query.uniqueResult());
		count = amount.intValue();
		return count;
	}
	//分页查询供应商
	public List<Customer> allCustomer(int pageNumber ,int pageSize , String customerName){
		session = HibernateSessionFactory.getSession();
		List<Customer> allcustomer = new ArrayList<Customer>();
		String hql = "from Customer as customer where customer.customerName like ?";
		String param ="%"+customerName+"%";
		query = session.createQuery(hql);
		query.setString(0, param);
		query.setFirstResult((pageNumber -1)*pageSize);
		query.setMaxResults(pageSize);
		allcustomer = query.list();
		session.close();
		return allcustomer;
	}
	
	//根据商品名称获取商品信息
	public Customer findCustomer(String customerName){
		Customer customer = new Customer();
		session = HibernateSessionFactory.getSession();
		String hql = "from Customer as customer where customer.customerName = ?";
		query = session.createQuery(hql);
		query.setString(0, customerName);
		Iterator<Customer> it = query.list().iterator();
		if(it.hasNext()){
			customer = it.next();
		}
		session.close();
		return customer;
	}
	//更新客户信息
	public void updateCustomer(Customer customer2){
		session = HibernateSessionFactory.getSession();
		Customer customer = (Customer)session.get(Customer.class,customer2.getId());
		customer.setCustomerName(customer2.getCustomerName());
		customer.setAddress(customer2.getAddress());
		customer.setCity(customer2.getCity());
		customer.setContact(customer2.getContact());
		customer.setPostcode(customer2.getPostcode());
		customer.setStatus(customer2.getStatus());
		customer.setTelphone(customer2.getTelphone());
		ta = session.beginTransaction();
		session.update(customer);
		ta.commit();
		session.close();
	}
	//删除客户信息
	public void deleteCustomer(int id){
		session = HibernateSessionFactory.getSession();
		Customer customer = (Customer)session.get(Customer.class, id);
		ta = session.beginTransaction();
		session.delete(customer);
		ta.commit();
		session.close();
	}
	//查找所有客户信息
	public List<Customer> allFindCustomer(){
		session = HibernateSessionFactory.getSession();
		List<Customer> allcustomer = new ArrayList<Customer>();
		String hql = "from Customer";
		query = session.createQuery(hql);
		allcustomer = query.list();
		session.close();
		return allcustomer;
	}

}

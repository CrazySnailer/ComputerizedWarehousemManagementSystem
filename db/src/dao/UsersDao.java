package dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import sessionFactory.HibernateSessionFactory;
import bean.Users;

public class UsersDao {
	private Session session;
	private Query query;
	private Transaction ta;
	//添加供应商
	public void addUsers(Users users)
	{
		session = HibernateSessionFactory.getSession();
		ta = session.beginTransaction();
		session.save(users);
		ta.commit();
		session.close();
	}
	
	//根据供应商名查找供应商的总数
	public int usersAmount(String userName){
		int count;
		Number amount;
		session = HibernateSessionFactory.getSession();
		String hql = "select count(*) from Users as users where users.username like '%"+userName+"%'";
		query = session.createQuery(hql);
		amount = (Number) query.uniqueResult();
		System.out.println(query.uniqueResult());
		count = amount.intValue();
		return count;
	}
	//分页查询供应商
	public List<Users> allUsers(int pageNumber ,int pageSize , String userName){
		session = HibernateSessionFactory.getSession();
		List<Users> allUsers = new ArrayList<Users>();
		String hql = "from Users as users where users.username like ?";
		String param ="%"+userName+"%";
		query = session.createQuery(hql);
		query.setString(0, param);
		query.setFirstResult((pageNumber -1)*pageSize);
		query.setMaxResults(pageSize);
		allUsers = query.list();
		session.close();
		return allUsers;
	}
	
	//根据用户名查找用户
	public Users findUsers(String userName){
		Users users = new Users();
		session = HibernateSessionFactory.getSession();
		String hql = "from Users as users where users.username = ?";
		query = session.createQuery(hql);
		query.setString(0, userName);
		Iterator<Users> it = query.list().iterator();
		if(it.hasNext()){
			users = it.next();
		}
		session.close();
		return users;
	}
	//更新客户信息
	public void updateUsers(Users users2){
		session = HibernateSessionFactory.getSession();
		Users users = (Users)session.get(Users.class,users2.getId());
		users.setDeptName(users2.getDeptName());
		users.setEmail(users2.getEmail());
		users.setPassword(users2.getPassword());
		users.setUsername(users2.getUsername());
		users.setUtype(users2.getUtype());
		users.setCreateTime(users2.getCreateTime());
		users.setStatus(users2.getStatus());
		ta = session.beginTransaction();
		session.update(users);
		ta.commit();
		session.close();
	}
	//删除客户信息
	public void deleteUsers(int id){
		session = HibernateSessionFactory.getSession();
		Users users = (Users)session.get(Users.class, id);
		ta = session.beginTransaction();
		session.delete(users);
		ta.commit();
		session.close();
	}
	//查找所有客户信息
	public List<Users> allFindUsers(){
		session = HibernateSessionFactory.getSession();
		List<Users> allUsers = new ArrayList<Users>();
		String hql = "from Users";
		query = session.createQuery(hql);
		allUsers = query.list();
		session.close();
		return allUsers;
	}
	
	//给指定的用户插入权限信息
	public void insetGrants(int userId,String grants){
		session = HibernateSessionFactory.getSession();
		Users users = (Users)session.get(Users.class,userId);
		users.setGrantsManage(grants);
		ta = session.beginTransaction();
		session.update(users);
		ta.commit();
		session.close();
	}
}

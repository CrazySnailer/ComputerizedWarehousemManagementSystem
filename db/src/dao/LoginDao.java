package dao;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import bean.Users;

import sessionFactory.HibernateSessionFactory;

public class LoginDao {
	private Session session;
	private Query query;
	private Transaction ta;
	
	public boolean login(String username,String password){
		boolean flag = false;
		session = HibernateSessionFactory.getSession();
		String hql = "from Users where username=? and password=?";
		query = session.createQuery(hql);
		query.setString(0, username);
		query.setString(1, password);
		List list = query.list();
		Iterator it = list.iterator();
		if(it.hasNext()){
			flag = true;
		}
		return flag;
	}
	
	public void updateUser(String username,Date loginTime){
		UsersDao userdao = new UsersDao();
		Users user = new Users();
		user = userdao.findUsers(username);
		session = HibernateSessionFactory.getSession();
		Users user1 = (Users) session.get(Users.class,user.getId());
		user1.setLastLoginTime(loginTime);
		ta = session.beginTransaction();
		session.update(user1);
		ta.commit();
		session.close();
	}
	
	public String getGrants(String username){
		String grants = null;
		session = HibernateSessionFactory.getSession();
		String hql = "select grantsManage from Users where username = ?";
		query = session.createQuery(hql);
		query.setString(0, username);
		grants = (String)query.uniqueResult();
		return grants;
	}
}

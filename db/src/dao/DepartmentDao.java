package dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import sessionFactory.HibernateSessionFactory;
import bean.Department;
import bean.Department;

public class DepartmentDao {
	private Session session;
	private Query query;
	private Transaction ta;
	//添加供应商
	public void addDepartment(Department department)
	{
		session = HibernateSessionFactory.getSession();
		ta = session.beginTransaction();
		session.save(department);
		ta.commit();
		session.close();
	}
	
	//根据供应商名查找供应商的总数
	public int departmentAmount(String departmentName){
		int count;
		Number amount;
		session = HibernateSessionFactory.getSession();
		String hql = "select count(*) from Department as department where department.deptName like '%"+departmentName+"%'";
		query = session.createQuery(hql);
		amount = (Number) query.uniqueResult();
		System.out.println(query.uniqueResult());
		count = amount.intValue();
		return count;
	}
	//分页查询供应商
	public List<Department> allDepartment(int pageNumber ,int pageSize , String departmentName){
		session = HibernateSessionFactory.getSession();
		List<Department> allDepartment = new ArrayList<Department>();
		String hql = "from Department as department where department.deptName like ?";
		String param ="%"+departmentName+"%";
		query = session.createQuery(hql);
		query.setString(0, param);
		query.setFirstResult((pageNumber -1)*pageSize);
		query.setMaxResults(pageSize);
		allDepartment = query.list();
		session.close();
		return allDepartment;
	}
	
	//根据商品名称获取商品信息
	public Department findDepartment(String departmentName){
		Department department = new Department();
		session = HibernateSessionFactory.getSession();
		String hql = "from Department as department where department.deptName = ?";
		query = session.createQuery(hql);
		query.setString(0, departmentName);
		Iterator<Department> it = query.list().iterator();
		if(it.hasNext()){
			department = it.next();
		}
		session.close();
		return department;
	}
	//更新客户信息
	public void updateDepartment(Department department2){
		session = HibernateSessionFactory.getSession();
		Department department = (Department)session.get(Department.class,department2.getId());
		department.setComments(department2.getComments());
		department.setDeptName(department2.getDeptName());
		department.setManager(department2.getManager());
		department.setStatus(department2.getStatus());
		ta = session.beginTransaction();
		session.update(department);
		ta.commit();
		session.close();
	}
	//删除客户信息
	public void deleteDepartment(int id){
		session = HibernateSessionFactory.getSession();
		Department department = (Department)session.get(Department.class, id);
		ta = session.beginTransaction();
		session.delete(department);
		ta.commit();
		session.close();
	}
	//查找所有客户信息
	public List<Department> allFindDepartment(){
		session = HibernateSessionFactory.getSession();
		List<Department> allDepartment = new ArrayList<Department>();
		String hql = "from Department";
		query = session.createQuery(hql);
		allDepartment = query.list();
		session.close();
		return allDepartment;
	}
}

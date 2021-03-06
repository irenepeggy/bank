package implement;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import bank.Account;
import bank.Department;
import bank.Operation;
import bank.Schedule;
import dao.DepartmentDAO;
import util.HibernateUtil;

public class DepartmentImpl implements DepartmentDAO {

	@Override
	public void addDepartment(Department department) throws SQLException {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {

			tx = session.beginTransaction();
			session.save(department);
			tx.commit();

		} catch (HibernateException exception) {
			if (tx != null)
				tx.rollback();
			throw exception;
		} finally {
			session.close();
		}
	}

	@Override
	public void editDepartment(Department department) throws SQLException {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.update(department);
			tx.commit();
		} catch (HibernateException exception) {
			if (tx != null)
				tx.rollback();
			throw exception;
		} finally {
			session.close();
		}
	}

	@Override
	public void deleteDepartment(Department department) throws SQLException {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Schedule sch = department.getSchedule();
			session.delete(department);
			session.delete(sch);
			
			tx.commit();

		} catch (HibernateException exception) {
			if (tx != null)
				tx.rollback();
			throw exception;
		} finally {
			session.close();
		}
	}

	@Override
	public Department getDepartmentById(Integer id) throws SQLException {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		Department department = null;
		try {
			tx = session.beginTransaction();
			department = (Department) session.get(Department.class, id);
			tx.commit();
		} catch (HibernateException exception) {
			if (tx != null)
				tx.rollback();
			throw exception;
		} finally {
			session.close();
		}
		return department;
	}

	
	@Override
	public Collection<Department> getAllDepartments() throws SQLException {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		List<Department> departments = new ArrayList<Department>();
		try {
			tx = session.beginTransaction();
			departments = session.createQuery("SELECT d from Department d", Department.class).list();
			tx.commit();
		} catch (Exception exception) {
			if (tx != null)
				tx.rollback();
			throw exception;
		} finally {
			session.close();
		}
		return departments;
	}

}

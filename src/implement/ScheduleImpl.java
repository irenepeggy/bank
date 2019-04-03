package implement;

import java.sql.SQLException;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import bank.Schedule;
import dao.ScheduleDAO;
import util.HibernateUtil;

public class ScheduleImpl implements ScheduleDAO {

	public Schedule editSchedule(Schedule schedule) throws SQLException{
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.update(schedule);
			tx.commit();
		} catch (HibernateException exception) {
			if (tx != null)
				tx.rollback();
			throw exception;
		} finally {
			session.close();
		}
		return schedule;
	}

	public void createSchedule(Schedule schedule) throws SQLException {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.save(schedule);
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
	public Schedule getScheduleById(Integer id) throws SQLException {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
	    Schedule schedule = null;
		try {
			tx = session.beginTransaction();
			schedule = (Schedule) session.get(Schedule.class, id);
			tx.commit();
		} catch (HibernateException exception) {
			if (tx != null)
				tx.rollback();
			throw exception;
		} finally {
			session.close();
		}
		return schedule;
	}
	
	public void deleteSchedule(Schedule schedule) throws SQLException{
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.delete(schedule);
			tx.commit();
		} catch (HibernateException exception) {
			if (tx != null)
				tx.rollback();
			throw exception;
		} finally {
			session.close();
		}

	}

}

package implement;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import bank.AccountType;
import bank.Client;
import bank.PersonClient;
import dao.PersonClientDAO;
import util.HibernateUtil;

public class PersonClientImpl implements PersonClientDAO {

	public void addPersonClient(PersonClient personClient) throws SQLException {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.save(personClient);
			tx.commit();
		} catch (Exception exception) {
			if (tx != null)
				tx.rollback();
			throw exception;
		} finally {
			session.close();
		}
	}

	public void editPersonClient(PersonClient personClient) throws SQLException {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.update(personClient);
			tx.commit();
		} catch (Exception exception) {
			if (tx != null)
				tx.rollback();
			throw exception;
		} finally {
			session.close();
		}
	}

	public PersonClient getPersonClientById(Integer id) throws SQLException {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		PersonClient personClient = null;
		try {
			tx = session.beginTransaction();
			personClient = (PersonClient) session.get(PersonClient.class, id);
			tx.commit();
		} catch (HibernateException exception) {
			if (tx != null)
				tx.rollback();
			throw exception;
		} finally {
			session.close();
		}
		return personClient;
	}

	public PersonClient getPersonClientByClient(Client c) throws SQLException {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		PersonClient personClient = new PersonClient();
		try {
			tx = session.beginTransaction();

			personClient = (PersonClient) session
					.createQuery("select pc " 
							+ "from PersonClient pc " 
							+ "where pc.client = :c")
					.setParameter("c", c)
					.list().get(0);

			tx.commit();
		} catch (Exception exception) {
			if (tx != null)
				tx.rollback();
			throw exception;
		} finally {
			session.close();
		}
		return personClient;
	}

	
}

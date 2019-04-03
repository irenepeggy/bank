package implement;

import java.sql.SQLException;

import org.hibernate.Session;
import org.hibernate.Transaction;

import bank.Client;
import dao.ClientDAO;
import util.HibernateUtil;

public class ClientImpl implements ClientDAO {

	public void addClient(Client client) throws SQLException {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.save(client);
			tx.commit();
		} catch (Exception exception) {
			if (tx != null)
				tx.rollback();
			throw exception;
		} finally {
			session.close();
		}

	}
	
	public void deleteClient(Client client) throws SQLException {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.delete(client);
			tx.commit();
		} catch (Exception exception) {
			if (tx != null)
				tx.rollback();
			throw exception;
		} finally {
			session.close();
		}
	}

	public Client getClientById (Integer id) throws SQLException {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		Client client = null;
		try {
			System.out.println("getById_begin");
			tx = session.beginTransaction();
			client = session.get(Client.class, id);
			tx.commit();
			System.out.println("getById_end");
		} catch (Exception exception) {
			if (tx != null)
				tx.rollback();
			throw exception;
		} finally {
			session.close();
		}
		return client;
	}

}

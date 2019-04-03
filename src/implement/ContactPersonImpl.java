package implement;

import java.sql.SQLException;

import org.hibernate.Session;
import org.hibernate.Transaction;

import bank.ContactPerson;
import dao.ContactPersonDAO;
import util.HibernateUtil;

public class ContactPersonImpl implements ContactPersonDAO {

	@Override
	public void editContactPerson(ContactPerson contactPerson) throws SQLException {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.update(contactPerson);
			tx.commit();
		} catch (Exception exception) {
			if (tx != null)
				tx.rollback();
			throw exception;
		} finally {
			session.close();
		}

	}

	@Override
	public void addContactPerson(ContactPerson contactPerson) throws SQLException {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.save(contactPerson);
			tx.commit();
		} catch (Exception exception) {
			if (tx != null)
				tx.rollback();
			throw exception;
		} finally {
			session.close();
		}

	}

	@Override
	public void deleteContactPerson(ContactPerson contactPerson) throws SQLException {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.delete(contactPerson);
			tx.commit();
		} catch (Exception exception) {
			if (tx != null)
				tx.rollback();
			throw exception;
		} finally {
			session.close();
		}

	}

}

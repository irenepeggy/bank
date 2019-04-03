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

	public PersonClient getPersonClientByClient(Client client) throws SQLException {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		PersonClient personClient = new PersonClient();
		try {
			tx = session.beginTransaction();

			personClient = (PersonClient) session
					.createQuery("select pc " + "from PersonClient pc " + "where pc.client = client").list().get(0);

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

	public Collection<PersonClient> getPersonsByFilter(Date openDate, Date closeDate, Set<AccountType> accountTypes)
			throws SQLException {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		List<PersonClient> personClients = new ArrayList<PersonClient>();
		Integer emptyFlag = 1;
		String queryClause = new String();
		queryClause += "(:openDate is null or acc.openDate >= :openDate) ";
		queryClause += (!queryClause.isEmpty() ? "and " : " ") + "(:closeDate is null or (acc.closeDate is not null and acc.closeDate <= :closeDate)) ";
		if (accountTypes.isEmpty()) {
			emptyFlag = 0;
		}
		queryClause += (!queryClause.isEmpty() ? "and " : " ")
				+ " (:emptyFlag = 0 or acc.accountType in :accountTypes) ";
		try {
			tx = session.beginTransaction();

			personClients = session
					.createQuery("select pc from PersonClient pc " + "inner join pc.client as c "
							+ "inner join c.accounts as acc " + "where " + queryClause, PersonClient.class)
					.setParameter("openDate", openDate).setParameter("accountTypes", accountTypes)
					.setParameter("closeDate", closeDate)
					.setParameter("emptyFlag", emptyFlag)
					.list();
			tx.commit();
		} catch (HibernateException exception) {
			if (tx != null)
				tx.rollback();
			throw exception;
		} finally {
			session.close();
		}
		return personClients;
	}

}

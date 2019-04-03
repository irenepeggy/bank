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
import bank.EntityClient;
import dao.EntityClientDAO;
import util.HibernateUtil;

public class EntityClientImpl implements EntityClientDAO {

	public void addEntityClient(EntityClient entityClient) throws SQLException {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.save(entityClient);
			tx.commit();
		} catch (Exception exception) {
			if (tx != null)
				tx.rollback();
			throw exception;
		} finally {
			session.close();
		}
	}

	public void editEntityClient(EntityClient entityClient) throws SQLException {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.update(entityClient);
			tx.commit();
		} catch (Exception exception) {
			if (tx != null)
				tx.rollback();
			throw exception;
		} finally {
			session.close();
		}
	}


	
	public EntityClient getEntityClientByClient(Client client) throws SQLException {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		EntityClient entityClient = new EntityClient();
		try {
			tx = session.beginTransaction();

			entityClient = (EntityClient) session.createQuery("select ec " 
					+ "from EntityClient ec " 
					+ "where ec.client = client")
					.list().get(0);

			tx.commit();
		} catch (Exception exception) {
			if (tx != null)
				tx.rollback();
			throw exception;
		} finally {
			session.close();
		}
		return entityClient;
	}
	public EntityClient getEntityClientById(Integer id) throws SQLException {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		EntityClient entityClient = new EntityClient();
		try {
			tx = session.beginTransaction();

			entityClient = session.get(EntityClient.class, id);

			tx.commit();
		} catch (Exception exception) {
			if (tx != null)
				tx.rollback();
			throw exception;
		} finally {
			session.close();
		}
		return entityClient;
	}

	public Collection<EntityClient> getEntitiesByFilter(Date openDate, Date closeDate, Set<AccountType> accountTypes)
			throws SQLException {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		List<EntityClient> entityClients = new ArrayList<EntityClient>();
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

			entityClients = session
					.createQuery("select ec from EntityClient ec " + "inner join ec.client as c "
							+ "inner join c.accounts as acc " + "where " + queryClause, EntityClient.class)
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
		return entityClients;
	}

}

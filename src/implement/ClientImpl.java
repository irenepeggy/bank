package implement;

import java.awt.HeadlessException;
import java.sql.SQLException;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.Transaction;

import bank.Account;
import bank.Client;
import bank.ContactPerson;
import bank.EntityClient;
import bank.Operation;
import bank.PersonClient;
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

			Set<Account> accs = client.getAccounts();
			for (Account acc: accs) {
				Set<Operation> ops = acc.getOperations();
				for (Operation op: ops) {
					session.delete(op);
				}	
				session.delete(acc);
			}
			
			if (client.getType().equals("person")) {
				Set<PersonClient> pcs = client.getPersonClient();
				for (PersonClient pc: pcs) {
					session.delete(pc);
				}
			} else if (client.getType().equals("entity")) {
				Set<EntityClient> ecs = client.getEntityClient();
				for (EntityClient ec: ecs) {
					Set<ContactPerson> cps = ec.getContactPersons();
					for (ContactPerson cp: cps) {
						session.delete(cp);
					}
					session.delete(ec);
				}
			} else throw new HeadlessException("Something rong with client name types.");
			
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
			tx = session.beginTransaction();
			client = session.get(Client.class, id);
			tx.commit();
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

package implement;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import bank.Account;
import dao.AccountDAO;
import util.HibernateUtil;

public class AccountImpl implements AccountDAO{


	@Override
	public void openAccount(Account account) throws SQLException {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.save(account);
			tx.commit();
		} catch (Exception exception) {
			if (tx != null)
				tx.rollback();
			throw exception;
		} finally {
			session.close();
		}	
	}

	
	public void editAccount(Account account) throws SQLException {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.update(account);
			tx.commit();
		} catch (Exception exception) {
			if (tx != null)
				tx.rollback();
			throw exception;
		} finally {
			session.close();
		}		
	}


		
	public Account getAccountById(Integer id) throws SQLException {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		Account account = null;
		try {
			tx = session.beginTransaction();
			account = (Account) session.get(Account.class, id);
			tx.commit();
		} catch (HibernateException exception) {
			if (tx != null)
				tx.rollback();
			throw exception;
		} finally {
			session.close();
		}
		return account;
	}
	

	
	public Collection<Account> getAccountsByOperationProperties(Date earliestDate, 
			Date latestDate, 
			Double maxCancellation, 
			Double minCancellation, 
			Double maxAssessment, 
			Double minAssessment) throws SQLException{
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		List<Account> accounts = new ArrayList<Account>();
		String queryClause = new String();
		if (earliestDate != null)
			queryClause += "oper.time >= :earliestDate ";
		if(latestDate != null)
			queryClause += (!queryClause.isEmpty()?"and ": " ") + "oper.time <= :latestDate ";
		if(maxAssessment != null)
			queryClause += (!queryClause.isEmpty()?"and ": " ") + "oper.sum <= :maxAssessment ";
		if(maxCancellation  != null)
			queryClause += (!queryClause.isEmpty()?"and ": " ") + "oper.sum >= :maxCancellation ";
		if(minAssessment != null)
			queryClause += (!queryClause.isEmpty()?"and ": " ") + "oper.sum <= :minAssessment ";
		if(minCancellation  != null)
			queryClause += (!queryClause.isEmpty()?"and ": " ") + "oper.sum >= :minCancellation ";
		
		try {
			tx = session.beginTransaction();
			
			accounts = session.createQuery("select a from Account a "
					+ "inner join a.operations as oper "
					+ "where " + queryClause, Account.class)
					.setParameter("earliestDate", earliestDate)
					.setParameter("latestDate", latestDate)
					.setParameter("maxAssessment", maxAssessment)
					.setParameter("maxCancellation", maxCancellation)
					.setParameter("minCancellation", minCancellation)
					.setParameter("minAssessment", minAssessment)
					.list();
			tx.commit();
		} catch (HibernateException exception) {
			if (tx != null)
				tx.rollback();
			throw exception;
		} finally {
			session.close();
		}
		return accounts;
	}


}

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
import bank.AccountType;
import dao.AccountDAO;
import util.HibernateUtil;

public class AccountImpl implements AccountDAO{


	@Override
	public void openAccount(Account account, Double sum, Integer period) throws SQLException {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			
			Integer curMaxNum = session.createQuery("select max(acc.accountNum)"
					+ "from Account acc "
					+ "order by acc.accountNum desc", Integer.class)
			.getResultList().get(0);
			
			account.setAccountNum(curMaxNum + 1);			
			account.setOpenDate( new Date());
			account.setStatus("open");
			
			AccountType accType = account.getAccountType();
			if (accType.getName().contains("settlement")) {
				account.setBalance(sum);
			} else if (accType.getName().contains("credit")) {
				if (sum <= accType.getMaxCredit()) {
					account.setDebit(sum);
					account.setCredit(sum);
				} else throw new HibernateException("Too large credit.");

			} else if (accType.getName().contains("deposit")) {
				if (sum >= accType.getMinDepositSum()) {
					account.setDeposit(sum);
				} else throw new HibernateException("Too little deposit");
				if (period <= accType.getMaxPeriod() && period >= accType.getMinPeriod()) {
					account.setPeriod(period);
				} else throw new HibernateException("Period for deposit is out of bounds.");
			} else throw new HibernateException("Selected account type doesn't exist.");
			
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

	
	public void closeAccount(Account account) throws SQLException {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			
			if (account.getCredit() > 0) {
				throw new HibernateException("Credit needs repayment.");
			}
			account.setBalance(0.0);
			account.setDeposit(0.0);
			account.setCloseDate(new Date());
			account.setStatus("closed");
			
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
	

	
	public Collection<Account> getAccountsByFilter(String status,
			Date earliestOperationDate, 
			Date latestOperationDate, 
			Double maxCancellation, 
			Double minCancellation, 
			Double maxAssessment, 
			Double minAssessment,
			Date earliestOpenDate,
			Date latestOpenDate,
			Date earliestCloseDate,
			Date latestCloseDate,
			AccountType accountType) throws SQLException{
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		List<Account> accounts = new ArrayList<Account>();
		String queryClause = new String();
		
		queryClause += "(:status is null or acc.status = :status) ";
		queryClause += (!queryClause.isEmpty()?"and ": " ") + "(:earliestOperationDate is null or oper.time >= :earliestOperationDate) ";
		queryClause += (!queryClause.isEmpty()?"and ": " ") + "(:latestOperationDate is null or oper.time <= :latestOperationDate) ";
		queryClause += (!queryClause.isEmpty()?"and ": " ") + "(:maxAssessment is null or oper.sum < 0 or oper.sum <= :maxAssessment) ";
		queryClause += (!queryClause.isEmpty()?"and ": " ") + "(:maxCancellation is null or oper.sum > 0 or -oper.sum <= :maxCancellation) ";
		queryClause += (!queryClause.isEmpty()?"and ": " ") + "(:minAssessment is null or oper.sum < 0 or oper.sum >= :minAssessment) ";
		queryClause += (!queryClause.isEmpty()?"and ": " ") + "(:minCancellation is null or oper.sum > 0 or -oper.sum >= :minCancellation) ";
		queryClause += (!queryClause.isEmpty()?"and ": " ") + "(:earliestOpenDate is null or acc.openDate >= :earliestOpenDate) ";
		queryClause += (!queryClause.isEmpty()?"and ": " ") + "(:latestOpenDate is null or acc.openDate <= :latestOpenDate) ";
		queryClause += (!queryClause.isEmpty()?"and ": " ") + "(:earliestCloseDate is null or (acc.status = 'closed' and acc.closeDate >= :earliestCloseDate)) ";
		queryClause += (!queryClause.isEmpty()?"and ": " ") + "(:latestCloseDate is null or (acc.status = 'closed' and acc.closeDate <= :latestCloseDate)) ";
		queryClause += (!queryClause.isEmpty()?"and ": " ") + "(:accountType is null or acc.accountType = :accountType) ";
	
		try {
			tx = session.beginTransaction();
			
			accounts = session.createQuery("select acc from Account acc "
					+ "inner join acc.operations as oper "
					+ "where " + queryClause, Account.class)
					.setParameter("status", status)
					.setParameter("earliestOperationDate", earliestOperationDate)
					.setParameter("latestOperationDate", latestOperationDate)
					.setParameter("maxAssessment", maxAssessment)
					.setParameter("maxCancellation", maxCancellation)
					.setParameter("minCancellation", minCancellation)
					.setParameter("minAssessment", minAssessment)
					.setParameter("earliestOpenDate", earliestOpenDate)
					.setParameter("latestOpenDate", latestOpenDate)
					.setParameter("earliestCloseDate", earliestCloseDate)
					.setParameter("latestCloseDate", latestCloseDate)
					.setParameter("accountType", accountType)
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

package implement;

import java.sql.SQLException;
import java.util.Date;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import bank.Account;
import bank.Operation;
import dao.OperationDAO;
import util.HibernateUtil;

public class OperationImpl implements OperationDAO {

	public Operation getOperationById(Integer id) throws SQLException {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		Operation operation = null;
		try {
			tx = session.beginTransaction();
			operation = (Operation) session.get(Operation.class, id);
			tx.commit();
		} catch (HibernateException exception) {
			if (tx != null)
				tx.rollback();
			throw exception;
		} finally {
			session.close();
		}
		return operation;
	}
	public void performAssessment(Operation operation) throws SQLException {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			
			Account acc = operation.getAccount();
			Double sum = operation.getSum();
			if (!acc.getAccountType().getName().contains("settlement")) {
				throw new HibernateException("Invalid AccountType");
			}
			if (sum < 0) {
				throw new HibernateException("For Assessment sum should be positive.");
			}
			if(sum > acc.getAccountType().getMaxAssessment()) {
				throw new HibernateException("Sum constaint violation");
			}
			acc.setBalance(acc.getBalance() + sum);
			operation.setTime(new Date());
			
			session.update(acc);
			session.save(operation);
			tx.commit();
		} catch (HibernateException exception) {
			if (tx != null)
				tx.rollback();
			throw exception;
		} finally {
			session.close();
		}
	}
	public void performCancellation(Operation operation) throws SQLException {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Account acc = operation.getAccount();
			Double sum = operation.getSum();
			if (!acc.getAccountType().getName().contains("settlement")) {
				throw new HibernateException("Invalid AccountType.");
			}
			if (sum > 0) {
				throw new HibernateException("For cancellation sum should be negative.");
			}
			if (acc.getBalance() < -sum) {
				throw new HibernateException("Too little balance.");
			}
			System.out.println(sum.toString() + " " + acc.getAccountType().getMaxCancellation().toString());
			if(-sum > acc.getAccountType().getMaxCancellation()) {
				throw new HibernateException("Sum constaint violation.");
			}
			acc.setBalance(acc.getBalance() + sum);
			operation.setTime(new Date());

			session.update(acc);
			session.save(operation);
			tx.commit();
		} catch (HibernateException exception) {
			if (tx != null)
				tx.rollback();
			throw exception;
		} finally {
			session.close();
		}
	}
	
	public void applyInterestOnDeposit(Account account) throws SQLException{
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
	
			if (!account.getAccountType().getName().contains("deposit")) {
				throw new HibernateException("Invalid AccountType");
			}
			
			Operation op = new Operation();
			op.setAccount(account);
			op.setDepartment(account.getDepartment());
			op.setSum(account.getDeposit() * account.getAccountType().getInterestOnDeposit() / 100);
			op.setTime(new Date());
			
			account.setBalance(account.getBalance() + op.getSum());
			session.update(account);
			op.setTime(new Date());

			session.save(op);
			tx.commit();
		} catch (HibernateException exception) {
			if (tx != null)
				tx.rollback();
			throw exception;
		} finally {
			session.close();
		}
	}
	
	public void applyInterestOnLoan(Account account) throws SQLException{
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
	
			if (!account.getAccountType().getName().contains("credit")) {
				throw new HibernateException("Invalid AccountType");
			}
			
			Operation op = new Operation();
			op.setAccount(account);
			op.setDepartment(account.getDepartment());
			op.setSum(-account.getDebit() * account.getAccountType().getInterestOnLoan() / 100);
			op.setTime(new Date());
			
			account.setCredit(account.getCredit() - op.getSum());
			op.setTime(new Date());

			session.update(account);
			session.save(op);
			tx.commit();
		} catch (HibernateException exception) {
			if (tx != null)
				tx.rollback();
			throw exception;
		} finally {
			session.close();
		}
	}
	
	public void payForCredit(Account account, Double sum) throws SQLException{
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
	
			if (!account.getAccountType().getName().contains("credit")) {
				throw new HibernateException("Invalid AccountType");
			}
			if (account.getCredit() <= sum) {
				sum = account.getCredit();
				account.setStatus("closed");
			}
			Operation op = new Operation();
			op.setAccount(account);
			op.setDepartment(account.getDepartment());
			op.setSum(sum);
			op.setTime(new Date());
			
			account.setCredit(account.getCredit() - op.getSum());
			op.setTime(new Date());

			session.update(account);
			session.save(op);
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

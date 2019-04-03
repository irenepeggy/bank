package implement;

import java.sql.SQLException;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import bank.AccountType;
import dao.AccountTypeDAO;
import util.HibernateUtil;

public class AccountTypeImpl implements AccountTypeDAO {
	
	public AccountType getAccountTypeById(Integer id) throws SQLException{
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		AccountType accountType = null;
		try {
			tx = session.beginTransaction();
			accountType = (AccountType) session.get(AccountType.class, id);
			tx.commit();
		} catch (HibernateException exception) {
			if (tx != null)
				tx.rollback();
			throw exception;
		} finally {
			session.close();
		}
		return accountType;
	}
}

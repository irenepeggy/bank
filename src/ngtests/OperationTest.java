package ngtests;

import java.sql.SQLException;
import java.util.Date;

import org.hibernate.HibernateException;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import bank.Account;
import bank.Operation;
import dao.AccountDAO;
import dao.OperationDAO;
import implement.Factory;

public class OperationTest {

	public OperationDAO opDAO;
	public AccountDAO accDAO;
//	public DepartmentDAO depDAO;
	
	@BeforeTest
	public void beforeTest() {
		accDAO = Factory.getInstance().getAccountDAO();
		opDAO = Factory.getInstance().getOperationDAO();
//		depDAO = Factory.getInstance().getDepartmentDAO();
	}

	@Test (expectedExceptions = HibernateException.class, expectedExceptionsMessageRegExp = "Sum constaint violation")
	public void testPerformAssessment() throws SQLException{
		Account acc = accDAO.getAccountById(3);
		Double oldBalance = acc.getBalance();
		assert(acc.getAccountType().getName().equals("settlement"));
		Operation op = new Operation();
		Double goodSum = acc.getAccountType().getMaxAssessment() - 1;
		
		op.setAccount(acc);
		op.setDepartment(acc.getDepartment());
		op.setSum(goodSum);
		op.setTime(new Date());
		
		opDAO.performAssessment(op);
		assert(acc.getBalance() == oldBalance + goodSum);
	
		Double badSum = acc.getAccountType().getMaxAssessment() + 1;
		op.setSum(badSum);
		op.setId(null);
		
		opDAO.performAssessment(op);
		assert(acc.getBalance() == oldBalance + goodSum);
	}
	
	@Test (expectedExceptions = HibernateException.class, expectedExceptionsMessageRegExp = "Sum constaint violation.")
	public void testPerformCancellation() throws SQLException{
		Account acc = accDAO.getAccountById(3);
		Double oldBalance = acc.getBalance();
		assert(acc.getAccountType().getName().equals("settlement"));
		Operation op = new Operation();
		Double goodSum = -acc.getAccountType().getMaxCancellation() + 1;
		
		op.setAccount(acc);
		op.setDepartment(acc.getDepartment());
		op.setSum(goodSum);
		op.setTime(new Date());
		
		opDAO.performCancellation(op);
		assert(acc.getBalance() == oldBalance + goodSum);
	
		Double badSum = -acc.getAccountType().getMaxCancellation() - 1;
		op.setSum(badSum);
		op.setId(null);
		
		opDAO.performCancellation(op);
		assert(acc.getBalance() == oldBalance + goodSum);
	}
	
	@Test 
	public void testInterectOnDeposit() throws SQLException{
		Account acc = accDAO.getAccountById(2);
		Double oldBalance = acc.getBalance();
		assert(acc.getAccountType().getName().equals("deposit"));
	
		opDAO.applyInterestOnDeposit(acc);
		assert(acc.getBalance() == oldBalance + acc.getDeposit() * acc.getAccountType().getInterestOnDeposit()/100);
	
	}

	@Test 
	public void testInterectOnLoan() throws SQLException{
		Account acc = accDAO.getAccountById(1);
		Double oldCredit = acc.getCredit();
		assert(acc.getAccountType().getName().equals("credit"));
	
		opDAO.applyInterestOnLoan(acc);
		assert(acc.getCredit() == oldCredit + acc.getDebit() * acc.getAccountType().getInterestOnLoan()/100);
	
	}
	
	@Test 
	public void testPayForCredit() throws SQLException{
		Account acc = accDAO.getAccountById(1);
		Double oldCredit = acc.getCredit();
		assert(acc.getAccountType().getName().equals("credit"));
		Double sum = 10000.0;
		
		opDAO.payForCredit(acc, sum);
		System.out.println(accDAO.getAccountById(1).getCredit().toString() + " " + new Double(oldCredit - sum).toString());
		assert(accDAO.getAccountById(1).getCredit() == oldCredit - sum);
		
		acc.setCredit(sum);
		assert(acc.getStatus().equals("open"));
		opDAO.payForCredit(acc, sum);
		assert(acc.getStatus().equals("closed"));
	
	}
}

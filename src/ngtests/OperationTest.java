package ngtests;

import java.sql.SQLException;
import java.util.Date;

import org.hibernate.HibernateException;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import bank.Account;
import bank.Operation;
import dao.AccountDAO;
import dao.AccountTypeDAO;
import dao.ClientDAO;
import dao.DepartmentDAO;
import dao.OperationDAO;
import implement.Factory;

public class OperationTest {

	public OperationDAO opDAO;
	public AccountDAO accDAO;
	public ClientDAO cliDAO;
	public AccountTypeDAO accTypeDAO;
	public DepartmentDAO depDAO;
	
	@BeforeTest
	public void beforeTest() {
		accDAO = Factory.getInstance().getAccountDAO();
		opDAO = Factory.getInstance().getOperationDAO();
		cliDAO = Factory.getInstance().getClientDAO();
		depDAO = Factory.getInstance().getDepartmentDAO();
		accTypeDAO = Factory.getInstance().getAccountTypeDAO();
	}

	@Test (expectedExceptions = HibernateException.class, expectedExceptionsMessageRegExp = "Sum constaint violation")
	public void testPerformAssessment() throws SQLException{
		Integer id1 = 1;
		Account acc = new Account();

		// open settlement account
		acc.setClient(cliDAO.getClientById(id1));
		acc.setAccountType(accTypeDAO.getAccountTypeById(1));
		acc.setDepartment(depDAO.getDepartmentById(1));
		Double sumSettlement = 10000.0;
		accDAO.openAccount(acc, sumSettlement, null);

		Double oldBalance = acc.getBalance();
		Assert.assertEquals(acc.getAccountType().getName(),"settlement");
		Operation op = new Operation();
		Double goodSum = acc.getAccountType().getMaxAssessment() - 1;
		
		op.setAccount(acc);
		op.setDepartment(acc.getDepartment());
		op.setSum(goodSum);
		
		
		opDAO.performAssessment(op);
		Assert.assertEquals(acc.getBalance(), oldBalance + goodSum);
	
		Double badSum = acc.getAccountType().getMaxAssessment() + 1;
		op.setSum(badSum);
		op.setId(null);
		
		opDAO.performAssessment(op);
		Assert.assertEquals(acc.getBalance(), oldBalance + goodSum);
	}
	
	@Test (expectedExceptions = HibernateException.class, expectedExceptionsMessageRegExp = "Sum constaint violation.")
	public void testPerformCancellation() throws SQLException{
		Integer id1 = 1;
		Account acc = new Account();

		// open settlement account
		acc.setClient(cliDAO.getClientById(id1));
		acc.setAccountType(accTypeDAO.getAccountTypeById(1));
		acc.setDepartment(depDAO.getDepartmentById(1));
		Double sumSettlement = 1000000.0;
		accDAO.openAccount(acc, sumSettlement, null);

		Double oldBalance = acc.getBalance();
		Assert.assertEquals(acc.getAccountType().getName(), "settlement");
		Operation op = new Operation();
		Double goodSum = -acc.getAccountType().getMaxCancellation() + 1;
		
		op.setAccount(acc);
		op.setDepartment(acc.getDepartment());
		op.setSum(goodSum);
		op.setTime(new Date());
		
		opDAO.performCancellation(op);
		Assert.assertEquals(acc.getBalance(), oldBalance + goodSum);
	
		Double badSum = -acc.getAccountType().getMaxCancellation() - 1;
		op.setSum(badSum);
		op.setId(null);
		
		opDAO.performCancellation(op);
		Assert.assertEquals(acc.getBalance(), oldBalance + goodSum);
	}
	
	@Test 
	public void testInterectOnDeposit() throws SQLException{
		Integer id1 = 1;
		Account acc = new Account();

		// open deposit account
		acc.setClient(cliDAO.getClientById(id1));
		acc.setAccountType(accTypeDAO.getAccountTypeById(3));
		acc.setDepartment(depDAO.getDepartmentById(1));
		Double sumDeposit = 100000.0;
		Integer period = 12;
		accDAO.openAccount(acc, sumDeposit, period);

		Double oldBalance = acc.getBalance();
		Assert.assertEquals(acc.getAccountType().getName(), "deposit");
	
		opDAO.applyInterestOnDeposit(acc);
		Assert.assertEquals(acc.getBalance(), oldBalance + acc.getDeposit() * acc.getAccountType().getInterestOnDeposit()/100);
	
	}

	@Test 
	public void testInterectOnLoan() throws SQLException{
		Integer id1 = 1;
		Account acc = new Account();

		// open credit account
		acc.setClient(cliDAO.getClientById(id1));
		acc.setAccountType(accTypeDAO.getAccountTypeById(2));
		acc.setDepartment(depDAO.getDepartmentById(1));
		Double sumCredit = 10000.0;
		accDAO.openAccount(acc, sumCredit, null);

		Double oldCredit = acc.getCredit();
		Assert.assertEquals(acc.getAccountType().getName(), "credit");
	
		opDAO.applyInterestOnLoan(acc);
		Assert.assertEquals(acc.getCredit(), oldCredit + acc.getDebit() * acc.getAccountType().getInterestOnLoan()/100);
	
	}
	
	@Test 
	public void testPayForCredit() throws SQLException{
		Integer id1 = 1;
		Account acc = new Account();

		// open credit account
		acc.setClient(cliDAO.getClientById(id1));
		acc.setAccountType(accTypeDAO.getAccountTypeById(2));
		acc.setDepartment(depDAO.getDepartmentById(1));
		Double sumCredit = 10000.0;
		accDAO.openAccount(acc, sumCredit, null);

		Double oldCredit = acc.getCredit();
		Assert.assertEquals(acc.getAccountType().getName(), "credit");
		Double sum = 10000.0;
		
		Assert.assertEquals(acc.getStatus(), "open");
		opDAO.payForCredit(acc, sum);
		Assert.assertEquals(accDAO.getAccountById(acc.getId()).getCredit(), oldCredit - sum);
		Assert.assertEquals(acc.getStatus(), "closed");
	
	}
}

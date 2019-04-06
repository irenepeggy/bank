package ngtests;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.Collection;
import java.util.Date;
import java.util.Set;

import org.hibernate.HibernateException;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import bank.Account;
import bank.AccountType;
import bank.Operation;
import dao.AccountDAO;
import dao.AccountTypeDAO;
import dao.ClientDAO;
import dao.DepartmentDAO;
import dao.OperationDAO;
import implement.Factory;

public class AccountTest {

	public AccountDAO accDAO;
	public AccountTypeDAO accTypeDAO;
	public ClientDAO cliDAO;
	public DepartmentDAO depDAO;
	public OperationDAO opDAO;

	@BeforeTest
	public void beforeTest() {
		accDAO = Factory.getInstance().getAccountDAO();
		accTypeDAO = Factory.getInstance().getAccountTypeDAO();
		cliDAO = Factory.getInstance().getClientDAO();
		depDAO = Factory.getInstance().getDepartmentDAO();
		opDAO = Factory.getInstance().getOperationDAO();
	}

	@Test
	public void testOpenAccount() throws SQLException {
		
		Integer id1 = 1;
		Integer id2 = 2;
		Integer id3 = 7;
		Account newAcc1 = new Account();

		// open settlement account
		newAcc1.setClient(cliDAO.getClientById(id1));
		newAcc1.setAccountType(accTypeDAO.getAccountTypeById(1));
		newAcc1.setDepartment(depDAO.getDepartmentById(1));
		Double sumSettlement = 10000.0;

		accDAO.openAccount(newAcc1, sumSettlement, null);

		Assert.assertEquals(newAcc1.getBalance(), sumSettlement);
		Assert.assertEquals(newAcc1.getStatus(), "open");
		Assert.assertNotNull(newAcc1.getOpenDate());

		Set<Account> accs1 = cliDAO.getClientById(id1).getAccounts();
		boolean contains1 = false;
		for (Account a : accs1) {
			contains1 |= a.getId() == newAcc1.getId();
		}
		Assert.assertTrue(contains1);

		// open credit account
		Account newAcc2 = new Account();
		
		newAcc2.setClient(cliDAO.getClientById(id2));
		newAcc2.setAccountType(accTypeDAO.getAccountTypeById(2));
		newAcc2.setDepartment(depDAO.getDepartmentById(2));
		Double sumCredit = 10000.0;

		accDAO.openAccount(newAcc2, sumCredit, null);

		Assert.assertEquals(newAcc2.getCredit(), sumCredit);
		Assert.assertEquals(newAcc2.getDebit(), sumCredit);
		Assert.assertEquals(newAcc2.getStatus(), "open");
		Assert.assertNotNull(newAcc2.getOpenDate());

		Set<Account> accs2 = cliDAO.getClientById(id2).getAccounts();
		boolean contains2 = false;
		for (Account a : accs2) {
			contains2 |= a.getId() == newAcc2.getId();
		}
		Assert.assertTrue(contains2);

		//open deposit account
		Account newAcc3 = new Account();
		
		newAcc3.setClient(cliDAO.getClientById(id3));
		newAcc3.setAccountType(accTypeDAO.getAccountTypeById(3));
		newAcc3.setDepartment(depDAO.getDepartmentById(3));
		Double sumDeposit = 50000.0;
		Integer period = 12;

		accDAO.openAccount(newAcc3, sumDeposit, period);

		Assert.assertEquals(newAcc3.getDeposit(), sumDeposit);
		Assert.assertEquals(newAcc3.getPeriod(), period);
		Assert.assertEquals(newAcc3.getStatus(), "open");
		Assert.assertNotNull(newAcc3.getOpenDate());

		Set<Account> accs3 = cliDAO.getClientById(id3).getAccounts();
		boolean contains3 = false;
		for (Account a : accs3) {
			contains3 |= a.getId() == newAcc3.getId();
		}
		Assert.assertTrue(contains3);
	}

	@Test(expectedExceptions = HibernateException.class, expectedExceptionsMessageRegExp = "Credit needs repayment.")
	public void testCloseAccount() throws SQLException {
		//positive case for settlement account
		Integer id1 = 6;
		Account newAcc1 = new Account();
		
		newAcc1.setClient(cliDAO.getClientById(id1));
		newAcc1.setAccountType(accTypeDAO.getAccountTypeById(1));
		newAcc1.setDepartment(depDAO.getDepartmentById(1));
		Double sumSettlement = 10000.0;

		accDAO.openAccount(newAcc1, sumSettlement, null);
		accDAO.closeAccount(newAcc1);
		
		Assert.assertEquals(newAcc1.getBalance(), 0.0);
		Assert.assertEquals(newAcc1.getStatus(), "closed");
		Assert.assertNotNull(newAcc1.getCloseDate());
		
		//negative case for credit account
		Integer id2 = 6;
		Account newAcc2 = new Account();
		
		newAcc2.setClient(cliDAO.getClientById(id2));
		newAcc2.setAccountType(accTypeDAO.getAccountTypeById(2));
		newAcc2.setDepartment(depDAO.getDepartmentById(2));
		Double sumCredit = 10000.0;
		
		accDAO.openAccount(newAcc2, sumCredit, null);
		accDAO.closeAccount(newAcc2);

	}
	@Test
	public void testGetAccountByFilter() throws ParseException, SQLException {
		
		String status = null;
		Date earliestOperationDate;
		Date latestOperationDate;
		Double maxCancellation = 1000.0;
		Double minCancellation = 0.0;
		Double maxAssessment = 111.0;
		Double minAssessment = 0.0;
		Date earliestOpenDate = null;
		Date latestOpenDate = null;
		Date earliestCloseDate = null;
		Date latestCloseDate = null;
		AccountType accountType = null;
		
		Integer ids[] = {1, 2, 3};
		
		Account newAcc1 = new Account();
		newAcc1.setClient(cliDAO.getClientById(ids[0]));
		newAcc1.setAccountType(accTypeDAO.getAccountTypeById(1));
		newAcc1.setDepartment(depDAO.getDepartmentById(1));
		Double sumSettlement1 = 10000000.0;
		accDAO.openAccount(newAcc1, sumSettlement1, null);
		
		Account newAcc2 = new Account();
		newAcc2.setClient(cliDAO.getClientById(ids[1]));
		newAcc2.setAccountType(accTypeDAO.getAccountTypeById(1));
		newAcc2.setDepartment(depDAO.getDepartmentById(2));
		Double sumSettlement2 = 10000000.0;
		accDAO.openAccount(newAcc2, sumSettlement2, null);
		
		Account newAcc3 = new Account();
		newAcc3.setClient(cliDAO.getClientById(ids[2]));
		newAcc3.setAccountType(accTypeDAO.getAccountTypeById(1));
		newAcc3.setDepartment(depDAO.getDepartmentById(3));
		Double sumSettlement3 = 10000000.0;
		accDAO.openAccount(newAcc3, sumSettlement3, null);
	
		earliestOperationDate = new Date();
		
		Operation op1 = new Operation();
		op1.setAccount(newAcc1);
		op1.setDepartment(newAcc1.getDepartment());
		op1.setSum(maxAssessment + 1);
		opDAO.performAssessment(op1);

		Operation op2 = new Operation();
		op2.setAccount(newAcc2);
		op2.setDepartment(newAcc2.getDepartment());
		op2.setSum(maxAssessment - 1);
		opDAO.performAssessment(op2);

		Operation op3 = new Operation();
		op3.setAccount(newAcc3);
		op3.setDepartment(newAcc3.getDepartment());
		op3.setSum(-maxCancellation - 1);
		opDAO.performCancellation(op3);

		latestOperationDate = new Date();

		Collection<Account> accs = accDAO.getAccountsByFilter(status,
				earliestOperationDate, 
				latestOperationDate, 
				maxCancellation, 
				minCancellation, 
				maxAssessment, 
				minAssessment,
				earliestOpenDate,
				latestOpenDate,
				earliestCloseDate,
				latestCloseDate,
				accountType);
		
		boolean containsNewAcc2AndNothingMore = false;
		for (Account a: accs) {
			containsNewAcc2AndNothingMore |= a.getId() == newAcc2.getId();
		}
		
		containsNewAcc2AndNothingMore &= accs.size() == 1;
		
		Assert.assertTrue(containsNewAcc2AndNothingMore);
	}

}

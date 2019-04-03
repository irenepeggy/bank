package implement;

import dao.AccountDAO;
import dao.AccountTypeDAO;
import dao.ClientDAO;
import dao.ContactPersonDAO;
import dao.DepartmentDAO;
import dao.EntityClientDAO;
import dao.OperationDAO;
import dao.PersonClientDAO;
import dao.ScheduleDAO;

public class Factory {

	private static DepartmentDAO departmentDAO = null;
	private static ScheduleDAO scheduleDAO = null;
	private static ClientDAO clientDAO = null;
	private static ContactPersonDAO contactPersonDAO = null;
	private static EntityClientDAO entityClientDAO = null;
	private static PersonClientDAO personClientDAO = null;
	private static AccountDAO accountDAO = null;
	private static OperationDAO operationDAO = null;
	private static AccountTypeDAO accountTypeDAO = null;
	
	private static Factory instance = null;

	public static synchronized Factory getInstance() {
		if (instance == null) {
			instance = new Factory();
		}
		return instance;
	}

	public DepartmentDAO getDepartmentDAO() {
		if (departmentDAO == null) {
			departmentDAO = new DepartmentImpl();
		}
		return departmentDAO;
	}

	public ScheduleDAO getScheduleDAO() {
		if (scheduleDAO == null) {
			scheduleDAO = new ScheduleImpl();
		}
		return scheduleDAO;
	}
	
	public ClientDAO getClientDAO() {
		if (clientDAO == null) {
			clientDAO = new ClientImpl();
		}
		return clientDAO;
	}
	
	public PersonClientDAO getPersonClientDAO() {
		if (personClientDAO == null) {
			personClientDAO = new PersonClientImpl();
		}
		return personClientDAO;
	}
	public EntityClientDAO getEntityClientDAO() {
		if (entityClientDAO == null) {
			entityClientDAO = new EntityClientImpl();
		}
		return entityClientDAO;
	}
	public ContactPersonDAO getContactPersonDAO() {
		if (contactPersonDAO == null) {
			contactPersonDAO = new ContactPersonImpl();
		}
		return contactPersonDAO;
	}
	public OperationDAO getOperationDAO() {
		if (operationDAO == null) {
			operationDAO = new OperationImpl();
		}
		return operationDAO;
	}
	public AccountDAO getAccountDAO() {
		if (accountDAO == null) {
			accountDAO = new AccountImpl();
		}
		return accountDAO;
	}
	public AccountTypeDAO getAccountTypeDAO() {
		if (accountTypeDAO == null) {
			accountTypeDAO = new AccountTypeImpl();
		}
		return accountTypeDAO;
	}
	
}

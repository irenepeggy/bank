package dao;

import java.sql.SQLException;
import java.util.Collection;

import bank.Department;

public interface DepartmentDAO {
	
	 public void addDepartment(Department department) throws SQLException;

	 public void editDepartment(Department department) throws SQLException;

	 //it should delete the department, but leave the accounts and the operations 
	 //that referenced
	 //to it in database by setting their .department field to null
	 public void deleteDepartment(Department department) throws SQLException;

	 public Department getDepartmentById(Integer id) throws SQLException;

	 public Collection<Department> getAllDepartments() throws SQLException;

}

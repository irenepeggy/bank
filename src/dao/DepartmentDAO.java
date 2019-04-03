package dao;

import java.sql.SQLException;
import java.util.Collection;

import bank.Department;

public interface DepartmentDAO {
	
	 public void addDepartment(Department department) throws SQLException;

	 public void editDepartment(Department department) throws SQLException;

	 public void deleteDepartment(Department department) throws SQLException;

	 public Department getDepartmentById(Integer id) throws SQLException;

	 public Collection<Department> getAllDepartments() throws SQLException;

}

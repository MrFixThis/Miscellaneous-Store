package co.edu.unbosque.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import co.edu.unbosque.entity.Employee;
import co.edu.unbosque.exception.EmployeeNotFoundException;

/**
 * @author Bryan Baron
 */
public interface EmployeeService {

	/**
	 * Creates a new Employee
	 *
	 * @param employee the creating Employee instance
	 * @return the result of the CRUD's create operation over Employee
	 * @see co.edu.unbosque.repository.EmployeeRepository#save(Employee)
	 */
	ResponseEntity<Employee> createEmployee(
			Employee employee);

	/**
	 * Retrives an id-specified Employee entity
	 *
	 * @param id the id of the Employee entity to retrive
	 * @return the result of the CRUD's retrive operation over Employee
	 * @see co.edu.unbosque.repository.EmployeeRepository#findById(Long)
	 */
	ResponseEntity<Employee> getEmployeeById(Long id)
			throws EmployeeNotFoundException;

	/**
	 * Retrives all the Employee entities
	 *
	 * @return the result of the CRUD's retrive operation over Employee
	 * @see co.edu.unbosque.repository.EmployeeRepository#findAll()
	 */
	ResponseEntity<List<Employee>> getEmployees();

	/**
	 * Updates an id-specified Employee entity
	 *
	 * @param id the id of the Employee entity to update
	 * @param updatedEmployee the Employee instance with the updating information
	 * @return the result of the CRUD's update operation over Employee
	 * @see co.edu.unbosque.repository.EmployeeRepository#save(Employee)
	 */
	ResponseEntity<Employee> updateEmployeeById(Long id,
			Employee updatedEmployee)
			throws EmployeeNotFoundException;

	/**
	 * Deletes an id-specified Employee entity
	 *
	 * @param id the id of the Employee entity to delete
	 * @return the result of the CRUD's delete operation over Employee
	 * @see co.edu.unbosque.repository.EmployeeRepository#deleteById(Long)
	 */
	ResponseEntity<?> deleteEmployeeById(Long id)
			throws EmployeeNotFoundException;
}

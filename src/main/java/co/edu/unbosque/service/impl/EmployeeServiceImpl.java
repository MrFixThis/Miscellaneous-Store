package co.edu.unbosque.service.impl;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import co.edu.unbosque.entity.Employee;
import co.edu.unbosque.exception.EmployeeNotFoundException;
import co.edu.unbosque.repository.EmployeeRepository;
import co.edu.unbosque.service.EmployeeService;
import lombok.AllArgsConstructor;

/**
 * @author Bryan Baron
 */
@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

	private EmployeeRepository empolyeeRepository;

	/**
	 * Creates a new Employee
	 *
	 * @param empolyee the creating empolyee instance
	 * @return the result of the CRUD's create operation over Employee
	 * @see co.edu.unbosque.repository.EmployeeRepository#save(Employee)
	 */
	@Override
	public ResponseEntity<Employee> createEmployee(
			Employee empolyee) {
		final Employee savedEmployee = empolyeeRepository.save(empolyee);
		return ResponseEntity.ok(savedEmployee);
	}

	/**
	 * Retrives an id-specified Employee entity
	 *
	 * @param id the id of the Employee entity to retrive
	 * @return the result of the CRUD's retrive operation over Employee
	 * @see co.edu.unbosque.repository.EmployeeRepository#findById(Long)
	 */
	@Override
	public ResponseEntity<Employee> getEmployeeById(Long id)
		throws EmployeeNotFoundException {
		final Employee empolyee = empolyeeRepository.findById(id)
			.orElseThrow(() -> new EmployeeNotFoundException(
						String.format("empolyee with id %d not found",id)));
		return ResponseEntity.ok(empolyee);
	}

	/**
	 * Retrives all the Employee entities
	 *
	 * @return the result of the CRUD's retrive operation over Employee
	 * @see co.edu.unbosque.repository.EmployeeRepository#findAll()
	 */
	@Override
	public ResponseEntity<List<Employee>> getEmployees() {
		final List<Employee> empolyees = empolyeeRepository.findAll();
		return ResponseEntity.ok(empolyees);
	}

	/**
	 * Updates an id-specified Employee entity
	 *
	 * @param id the id of the Employee entity to update
	 * @param updatedEmployee the Employee instance with the updating information
	 * @return the result of the CRUD's update operation over Employee
	 * @see co.edu.unbosque.repository.EmployeeRepository#save(Employee)
	 */
	@Override
	public ResponseEntity<Employee> updateEmployeeById(
			Long id, Employee updatedEmployee)
			throws EmployeeNotFoundException {
		Employee empolyee = empolyeeRepository.findById(id)
			.orElseThrow(() -> new EmployeeNotFoundException(
						String.format("empolyee with id %d not found",id)));

		empolyee.setFirstName(updatedEmployee.getFirstName());
		empolyee.setMiddleName(updatedEmployee.getMiddleName());
		empolyee.setIdentificationNumber(updatedEmployee
				.getIdentificationNumber());
		empolyee.setIdentificationType(updatedEmployee
				.getIdentificationType());
		empolyee.setDateOfBirth(updatedEmployee.getDateOfBirth());
		empolyee.setDateOfHire(updatedEmployee.getDateOfHire());
		empolyee.setPhoneNumber(updatedEmployee.getPhoneNumber());
		empolyee.setEmailAddress(updatedEmployee.getEmailAddress());
		empolyee.setRole(updatedEmployee.getRole());
		empolyee.setBasicSalary(updatedEmployee.getBasicSalary());

		empolyee = empolyeeRepository.save(empolyee);
		return ResponseEntity.ok(empolyee);
	}

	/**
	 * Deletes an id-specified Employee entity
	 *
	 * @param id the id of the Employee entity to delete
	 * @return the result of the CRUD's delete operation over Employee
	 * @see co.edu.unbosque.repository.EmployeeRepository#deleteById(Long)
	 */
	@Override
	public ResponseEntity<?> deleteEmployeeById(Long id)
		throws EmployeeNotFoundException {
		Employee empolyee = empolyeeRepository.findById(id)
			.orElseThrow(() -> new EmployeeNotFoundException(
						String.format("empolyee with id %d not found",id)));
		empolyeeRepository.delete(empolyee);

		return ResponseEntity.noContent().build();
	}
}

package co.edu.unbosque.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.unbosque.entity.Employee;

/**
 * @author Bryan Baron
 */
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	
}

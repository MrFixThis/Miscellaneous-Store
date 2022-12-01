package co.edu.unbosque.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import co.edu.unbosque.entity.Administrator;
import co.edu.unbosque.exception.EmployeeNotFoundException;

/**
 * @author Bryan Baron
 */
public interface AdministratorService {

	/**
	 * Creates a new Administrator
	 *
	 * @param administrator the creating Administrator instance
	 * @return the result of the CRUD's create operation over Administrator
	 */
	ResponseEntity<Administrator> createAdministrator(
			Administrator administrator);

	/**
	 * Retrives an id-specified Administrator entity
	 *
	 * @param id the id of the Administrator entity to retrive
	 * @return the result of the CRUD's retrive operation over Administrator
	 */
	ResponseEntity<Administrator> getAdministratorById(Long id)
			throws EmployeeNotFoundException;

	/**
	 * Retrives all the Administrator entities
	 *
	 * @return the result of the CRUD's retrive operation over Administrator
	 */
	ResponseEntity<List<Administrator>> getAdministrators();

	/**
	 * Updates an id-specified Administrator entity
	 *
	 * @param id the id of the Administrator entity to update
	 * @param updatedAdministrator the Administrator instance with the updating information
	 * @return the result of the CRUD's update operation over Administrator
	 */
	ResponseEntity<Administrator> updateAdministratorById(Long id,
			Administrator updatedAdministrator)
			throws EmployeeNotFoundException;

	/**
	 * Deletes an id-specified Administrator entity
	 *
	 * @param id the id of the Administrator entity to delete
	 * @return the result of the CRUD's delete operation over Administrator
	 */
	ResponseEntity<?> deleteAdministratorById(Long id)
			throws EmployeeNotFoundException;
}

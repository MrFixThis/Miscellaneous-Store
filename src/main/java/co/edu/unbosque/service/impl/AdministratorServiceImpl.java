package co.edu.unbosque.service.impl;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import co.edu.unbosque.entity.Administrator;
import co.edu.unbosque.exception.EmployeeNotFoundException;
import co.edu.unbosque.repository.AdministratorRepository;
import co.edu.unbosque.service.AdministratorService;
import lombok.AllArgsConstructor;

/**
 * @author Bryan Baron
 */
@Service
@AllArgsConstructor
public class AdministratorServiceImpl implements AdministratorService {

	private AdministratorRepository administratorRepository;

	/**
	 * Creates a new Administrator
	 *
	 * @param administrator the creating administrator instance
	 * @return the result of the CRUD's create operation over Administrator
	 * @see co.edu.unbosque.repository.AdministratorRepository#save(Administrator)
	 */
	@Override
	public ResponseEntity<Administrator> createAdministrator(
			Administrator administrator) {
		final Administrator savedAdministrator =
			administratorRepository.save(administrator);
		return ResponseEntity.ok(savedAdministrator);
	}

	/**
	 * Retrives an id-specified Administrator entity
	 *
	 * @param id the id of the Administrator entity to retrive
	 * @return the result of the CRUD's retrive operation over Administrator
	 * @see co.edu.unbosque.repository.AdministratorRepository#findById(Long)
	 */
	@Override
	public ResponseEntity<Administrator> getAdministratorById(Long id)
		throws EmployeeNotFoundException {
		final Administrator administrator = administratorRepository.findById(id)
			.orElseThrow(() -> new EmployeeNotFoundException(
						String.format("Administrator with id %d not found",id)));
		return ResponseEntity.ok(administrator);
	}

	/**
	 * Retrives all the Administrator entities
	 *
	 * @return the result of the CRUD's retrive operation over Administrator
	 * @see co.edu.unbosque.repository.AdministratorRepository#findAll()
	 */
	@Override
	public ResponseEntity<List<Administrator>> getAdministrators() {
		final List<Administrator> administrators = administratorRepository.findAll();
		return ResponseEntity.ok(administrators);
	}

	/**
	 * Updates an id-specified Administrator entity
	 *
	 * @param id the id of the Administrator entity to update
	 * @param updatedAdministrator the Administrator instance with the updating information
	 * @return the result of the CRUD's update operation over Administrator
	 * @see co.edu.unbosque.repository.AdministratorRepository#save(Administrator)
	 */
	@Override
	public ResponseEntity<Administrator> updateAdministratorById(
			Long id, Administrator updatedAdministrator)
			throws EmployeeNotFoundException {
		Administrator administrator = administratorRepository.findById(id)
			.orElseThrow(() -> new EmployeeNotFoundException(
						String.format("Administrator with id %d not found",id)));

		administrator.setFirstName(updatedAdministrator.getFirstName());
		administrator.setMiddleName(updatedAdministrator.getMiddleName());
		administrator.setIdentificationNumber(updatedAdministrator
				.getIdentificationNumber());
		administrator.setIdentificationType(updatedAdministrator
				.getIdentificationType());
		administrator.setDateOfBirth(updatedAdministrator.getDateOfBirth());
		administrator.setDateOfHire(updatedAdministrator.getDateOfHire());
		administrator.setPhoneNumber(updatedAdministrator.getPhoneNumber());
		administrator.setEmailAddress(updatedAdministrator.getEmailAddress());
		administrator.setResidenceAddress(updatedAdministrator.getResidenceAddress());
		administrator.setRole(updatedAdministrator.getRole());
		administrator.setBasicSalary(updatedAdministrator.getBasicSalary());

		administrator = administratorRepository.save(administrator);
		return ResponseEntity.ok(administrator);
	}

	/**
	 * Deletes an id-specified Administrator entity
	 *
	 * @param id the id of the Administrator entity to delete
	 * @return the result of the CRUD's delete operation over Administrator
	 * @see co.edu.unbosque.repository.AdministratorRepository#deleteById(Long)
	 */
	@Override
	public ResponseEntity<?> deleteAdministratorById(Long id)
		throws EmployeeNotFoundException {
		Administrator administrator = administratorRepository.findById(id)
			.orElseThrow(() -> new EmployeeNotFoundException(
						String.format("Administrator with id %d not found",id)));
		administratorRepository.delete(administrator);

		return ResponseEntity.noContent().build();
	}
}

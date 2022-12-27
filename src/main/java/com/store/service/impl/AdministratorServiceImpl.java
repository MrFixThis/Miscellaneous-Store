package com.store.service.impl;

import java.net.URI;
import java.util.List;

import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.store.exception.EmployeeNotFoundException;
import com.store.model.Administrator;
import com.store.repository.AdministratorRepository;
import com.store.service.AdministratorService;

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
	 */
	@Override
	public ResponseEntity<Administrator> createAdministrator(
			Administrator administrator) {
		final Administrator savedAdministrator =
			administratorRepository.save(administrator);
		return ResponseEntity.created(URI.create(String.format("/api/v1/administrators/%d",
					savedAdministrator.getId()))).allow(HttpMethod.GET).build();
	}

	/**
	 * Retrives an id-specified Administrator entity
	 *
	 * @param id the id of the Administrator entity to retrive
	 * @return the result of the CRUD's retrive operation over Administrator
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
		administrator.setPaternalLastName(updatedAdministrator.getPaternalLastName());
		administrator.setMaternalLastName(updatedAdministrator.getMaternalLastName());
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

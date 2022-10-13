package co.edu.unbosque.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import co.edu.unbosque.entity.BranchOffice;
import co.edu.unbosque.exception.BranchOfficeNotFoundException;

/**
 * @author Bryan Baron
 */
public interface BranchOfficeService {

	/**
	 * Creates a new BranchOffice
	 *
	 * @param branchOffice the creating BranchOffice instance
	 * @return the result of the CRUD's create operation over BranchOffice
	 * @see co.edu.unbosque.repository.BranchOfficeRepository#save(BranchOffice)
	 */
	ResponseEntity<BranchOffice> createBranchOffice(
			BranchOffice branchOffice);

	/**
	 * Retrives an id-specified BranchOffice entity
	 *
	 * @param id the id of the BranchOffice entity to retrive
	 * @return the result of the CRUD's retrive operation over BranchOffice
	 * @see co.edu.unbosque.repository.BranchOfficeRepository#findById(Long)
	 */
	ResponseEntity<BranchOffice> getBranchOfficeById(Long id)
			throws BranchOfficeNotFoundException;

	/**
	 * Retrives all the BranchOffice entities
	 *
	 * @return the result of the CRUD's retrive operation over BranchOffice
	 * @see co.edu.unbosque.repository.BranchOfficeRepository#findAll()
	 */
	ResponseEntity<List<BranchOffice>> getBranchOffices();

	/**
	 * Retrieves all the BranchOffice entities by a Client's entity id.
	 *
	 * @param clientId the id of the Client's entity related to BranchOffice's entities
	 * @return the result of the CRUD's retrive operation over BranchOffice
	 * @see co.edu.unbosque.repository.BranchOfficeRepository#findBranchOfficesByClientsId(Long)
	 */
	ResponseEntity<List<BranchOffice>> getBranchOfficesByClientsId(Long clientId);

	/**
	 * Updates an id-specified BranchOffice entity
	 *
	 * @param id the id of the BranchOffice entity to update
	 * @param updatedBranchOffice the BranchOffice instance with the updating information
	 * @return the result of the CRUD's update operation over BranchOffice
	 * @see co.edu.unbosque.repository.BranchOfficeRepository#save(BranchOffice)
	 */
	ResponseEntity<BranchOffice> updateBranchOfficeById(Long id,
			BranchOffice updatedBranchOffice)
			throws BranchOfficeNotFoundException;

	/**
	 * Deletes an id-specified BranchOffice entity
	 *
	 * @param id the id of the BranchOffice entity to delete
	 * @return the result of the CRUD's delete operation over BranchOffice
	 * @see co.edu.unbosque.repository.BranchOfficeRepository#deleteById(Long)
	 */
	ResponseEntity<?> deleteBranchOfficeById(Long id)
			throws BranchOfficeNotFoundException;
}

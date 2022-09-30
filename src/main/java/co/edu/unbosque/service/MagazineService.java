package co.edu.unbosque.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import co.edu.unbosque.entity.Magazine;
import co.edu.unbosque.exception.MagazineNotFoundException;

/**
 * @author Bryan Baron
 */
public interface MagazineService {
	
	/**
	 * Creates a new Magazine
	 *
	 * @param magazine the creating Magazine instance
	 * @return the result of the CRUD's create operation over Magazine
	 * @see co.edu.unbosque.repository.MagazineRepository#save(Magazine)
	 */
	ResponseEntity<Magazine> createMagazine(Magazine magazine);

	/**
	 * Retrives an isbn-specified Magazine entity
	 *
	 * @param isbn the isbn of the Magazine entity to retrive
	 * @return the result of the CRUD's retrive operation over Magazine
	 * @see co.edu.unbosque.repository.MagazineRepository#findById(String)
	 */
	ResponseEntity<Magazine> getMagazineByIsbn(String ibsn)
			throws MagazineNotFoundException;

	/**
	 * Retrives a name-specified Magazine entity
	 *
	 * @param name the name of the Magazine entity to retrive
	 * @return the result of the CRUD's retrive operation over Magazine
	 * @see co.edu.unbosque.repository.MagazineRepository#findByName(String)
	 */
	ResponseEntity<Magazine> getMagazineByName(String name)
		throws MagazineNotFoundException;

	/**
	 * Retrives all the Magazine entities
	 *
	 * @return the result of the CRUD's retrive operation over Magazine
	 * @see co.edu.unbosque.repository.MagazineRepository#findAll()
	 */
	ResponseEntity<List<Magazine>> getMagazines();

	/**
	 * Updates an isbn-specified Magazine entity
	 *
	 * @param isbn the isbn of the Magazine entity to update
	 * @param updatedMagazine the Magazine instance with the updating information
	 * @return the result of the CRUD's update operation over Magazine
	 * @see co.edu.unbosque.repository.MagazineRepository#save(Magazine)
	 */
	ResponseEntity<Magazine> updateMagazineByIsbn(String isbn,
			Magazine updatedMagazine)
			throws MagazineNotFoundException;

	/**
	 * Deletes an isbn-specified Magazine entity
	 *
	 * @param isbn the isbn of the Magazine entity to delete
	 * @return the result of the CRUD's delete operation over Magazine
	 * @see co.edu.unbosque.repository.MagazineRepository#deleteById(String)
	 */
	ResponseEntity<?> deleteMagazineByIsbn(String isbn)
			throws MagazineNotFoundException;
}

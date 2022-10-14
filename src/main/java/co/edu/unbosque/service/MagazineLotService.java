package co.edu.unbosque.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import co.edu.unbosque.entity.MagazineLot;
import co.edu.unbosque.exception.MagazineLotNotFoundException;

/**
 * @author Bryan Baron
 */
public interface MagazineLotService {
	
	/**
	 * Creates a new MagazineLot
	 *
	 * @param magazineLot the creating MagazineLot instance
	 * @return the result of the CRUD's create operation over MagazineLot
	 * @see co.edu.unbosque.repository.MagazineLotRepository#save(MagazineLot)
	 */
	ResponseEntity<MagazineLot> createMagazineLot(MagazineLot magazineLot);

	/**
	 * Retrives an isbn-specified MagazineLot entity
	 *
	 * @param isbn the isbn of the MagazineLot entity to retrive
	 * @return the result of the CRUD's retrive operation over MagazineLot
	 * @see co.edu.unbosque.repository.MagazineLotRepository#findById(String)
	 */
	ResponseEntity<MagazineLot> getMagazineLotByIsbn(String ibsn)
			throws MagazineLotNotFoundException;

	/**
	 * Retrives a name-specified MagazineLot entity
	 *
	 * @param name the name of the MagazineLot entity to retrive
	 * @return the result of the CRUD's retrive operation over MagazineLot
	 * @see co.edu.unbosque.repository.MagazineLotRepository#findByName(String)
	 */
	ResponseEntity<MagazineLot> getMagazineLotByName(String name)
		throws MagazineLotNotFoundException;

	/**
	 * Retrives all the MagazineLot entities
	 *
	 * @return the result of the CRUD's retrive operation over MagazineLot
	 * @see co.edu.unbosque.repository.MagazineLotRepository#findAll()
	 */
	ResponseEntity<List<MagazineLot>> getMagazineLots();

	/**
	 * Updates an isbn-specified MagazineLot entity
	 *
	 * @param isbn the isbn of the MagazineLot entity to update
	 * @param updatedMagazineLot the MagazineLot instance with the updating information
	 * @return the result of the CRUD's update operation over MagazineLot
	 * @see co.edu.unbosque.repository.MagazineLotRepository#save(MagazineLot)
	 */
	ResponseEntity<MagazineLot> updateMagazineLotByIsbn(String isbn,
			MagazineLot updatedMagazineLot)
			throws MagazineLotNotFoundException;

	/**
	 * Deletes an isbn-specified MagazineLot entity
	 *
	 * @param isbn the isbn of the MagazineLot entity to delete
	 * @return the result of the CRUD's delete operation over MagazineLot
	 * @see co.edu.unbosque.repository.MagazineLotRepository#deleteById(String)
	 */
	ResponseEntity<?> deleteMagazineLotByIsbn(String isbn)
			throws MagazineLotNotFoundException;
}

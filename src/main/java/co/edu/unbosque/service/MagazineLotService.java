package co.edu.unbosque.service;

import java.util.List;
import java.util.UUID;

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
	 */
	ResponseEntity<MagazineLot> createMagazineLot(MagazineLot magazineLot);

	/**
	 * Retrives an isbn-specified MagazineLot entity
	 *
	 * @param isbn the isbn of the MagazineLot entity to retrive
	 * @return the result of the CRUD's retrive operation over MagazineLot
	 */
	ResponseEntity<MagazineLot> getMagazineLotByIsbn(UUID isbn)
			throws MagazineLotNotFoundException;

	/**
	 * Retrives a name-specified MagazineLot entity
	 *
	 * @param name the name of the MagazineLot entity to retrive
	 * @return the result of the CRUD's retrive operation over MagazineLot
	 */
	ResponseEntity<MagazineLot> getMagazineLotByName(String name)
		throws MagazineLotNotFoundException;

	/**
	 * Retrives all the MagazineLot entities
	 *
	 * @return the result of the CRUD's retrive operation over MagazineLot
	 */
	ResponseEntity<List<MagazineLot>> getMagazineLots();

	/**
	 * Updates an isbn-specified MagazineLot entity
	 *
	 * @param isbn the isbn of the MagazineLot entity to update
	 * @param updatedMagazineLot the MagazineLot instance with the updating information
	 * @return the result of the CRUD's update operation over MagazineLot
	 */
	ResponseEntity<MagazineLot> updateMagazineLotByIsbn(UUID isbn,
			MagazineLot updatedMagazineLot)
			throws MagazineLotNotFoundException;

	/**
	 * Deletes an isbn-specified MagazineLot entity
	 *
	 * @param isbn the isbn of the MagazineLot entity to delete
	 * @return the result of the CRUD's delete operation over MagazineLot
	 */
	ResponseEntity<?> deleteMagazineLotByIsbn(UUID isbn)
			throws MagazineLotNotFoundException;
}

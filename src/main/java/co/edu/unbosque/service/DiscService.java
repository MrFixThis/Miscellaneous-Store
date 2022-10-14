package co.edu.unbosque.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import co.edu.unbosque.entity.DiscLot;
import co.edu.unbosque.exception.DiscLotNotFoundException;

/**
 * @author Bryan Baron
 */
public interface DiscService {

	/**
	 * Creates a new DiscLot
	 *
	 * @param discLot the creating DiscLot instance
	 * @return the result of the CRUD's create operation over DiscLot
	 * @see co.edu.unbosque.repository.DiscLotRepository#save(DiscLot)
	 */
	ResponseEntity<DiscLot> createDisc(DiscLot discLot);

	/**
	 * Retrives an id-specified Disc entity
	 *
	 * @param id the id of the Disc entity to retrive
	 * @return the result of the CRUD's retrive operation over Disc
	 * @see co.edu.unbosque.repository.DiscLotRepository#findById(Long)
	 */
	ResponseEntity<DiscLot> getDiscById(Long id)
			throws DiscLotNotFoundException;

	/**
	 * Retrives a name-specified Disc entity
	 *
	 * @param name the name of the Disc entity to retrive
	 * @return the result of the CRUD's retrive operation over Disc
	 * @see co.edu.unbosque.repository.DiscLotRepository#findByName(String)
	 */
	ResponseEntity<DiscLot> getDiscByName(String name)
		throws DiscLotNotFoundException;

	/**
	 * Retrives all the Disc entities
	 *
	 * @return the result of the CRUD's retrive operation over Disc
	 * @see co.edu.unbosque.repository.DiscLotRepository#findAll()
	 */
	ResponseEntity<List<DiscLot>> getDiscs();

	/**
	 * Updates an id-specified Disc entity
	 *
	 * @param id the id of the Disc entity to update
	 * @param updatedDiscLot the Disc instance with the updating information
	 * @return the result of the CRUD's update operation over Disc
	 * @see co.edu.unbosque.repository.DiscLotRepository#save(DiscLot)
	 */
	ResponseEntity<DiscLot> updateDiscById(Long id, DiscLot updatedDiscLot)
			throws DiscLotNotFoundException;

	/**
	 * Deletes an id-specified Disc entity
	 *
	 * @param id the id of the Disc entity to delete
	 * @return the result of the CRUD's delete operation over Disc
	 * @see co.edu.unbosque.repository.DiscLotRepository#deleteById(Long)
	 */
	ResponseEntity<?> deleteDiscById(Long id) throws DiscLotNotFoundException;
}

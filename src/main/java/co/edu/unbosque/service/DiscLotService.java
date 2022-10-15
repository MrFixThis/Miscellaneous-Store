package co.edu.unbosque.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import co.edu.unbosque.entity.DiscLot;
import co.edu.unbosque.exception.DiscLotNotFoundException;

/**
 * @author Bryan Baron
 */
public interface DiscLotService {

	/**
	 * Creates a new DiscLot
	 *
	 * @param discLot the creating DiscLot instance
	 * @return the result of the CRUD's create operation over DiscLot
	 * @see co.edu.unbosque.repository.DiscLotRepository#save(DiscLot)
	 */
	ResponseEntity<DiscLot> createDiscLot(DiscLot discLot);

	/**
	 * Retrives an id-specified DiscLot entity
	 *
	 * @param id the id of the DiscLot entity to retrive
	 * @return the result of the CRUD's retrive operation over DiscLot
	 * @see co.edu.unbosque.repository.DiscLotRepository#findById(Long)
	 */
	ResponseEntity<DiscLot> getDiscLotById(Long id)
			throws DiscLotNotFoundException;

	/**
	 * Retrives a name-specified DiscLot entity
	 *
	 * @param name the name of the DiscLot entity to retrive
	 * @return the result of the CRUD's retrive operation over DiscLot
	 * @see co.edu.unbosque.repository.DiscLotRepository#findByName(String)
	 */
	ResponseEntity<DiscLot> getDiscLotByName(String name)
		throws DiscLotNotFoundException;

	/**
	 * Retrives all the DiscLot entities
	 *
	 * @return the result of the CRUD's retrive operation over DiscLot
	 * @see co.edu.unbosque.repository.DiscLotRepository#findAll()
	 */
	ResponseEntity<List<DiscLot>> getDiscLots();

	/**
	 * Updates an id-specified DiscLot entity
	 *
	 * @param id the id of the DiscLot entity to update
	 * @param updatedDiscLot the DiscLot instance with the updating information
	 * @return the result of the CRUD's update operation over DiscLot
	 * @see co.edu.unbosque.repository.DiscLotRepository#save(DiscLot)
	 */
	ResponseEntity<DiscLot> updateDiscLotById(Long id, DiscLot updatedDiscLot)
			throws DiscLotNotFoundException;

	/**
	 * Deletes an id-specified DiscLot entity
	 *
	 * @param id the id of the DiscLot entity to delete
	 * @return the result of the CRUD's delete operation over DiscLot
	 * @see co.edu.unbosque.repository.DiscLotRepository#deleteById(Long)
	 */
	ResponseEntity<?> deleteDiscLotById(Long id) throws DiscLotNotFoundException;
}

package co.edu.unbosque.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import co.edu.unbosque.entity.Disc;
import co.edu.unbosque.exception.DiscNotFoundException;

/**
 * @author Bryan Baron
 */
public interface DiscService {

	/**
	 * Creates a new Disc
	 *
	 * @param disc the creating Disc instance
	 * @return the result of the CRUD's create operation over Disc
	 * @see co.edu.unbosque.repository.DiscRepository#save(Disc)
	 */
	ResponseEntity<Disc> createDisc(Disc disc);

	/**
	 * Retrives an id-specified Disc entity
	 *
	 * @param id the id of the Disc entity to retrive
	 * @return the result of the CRUD's retrive operation over Disc
	 * @see co.edu.unbosque.repository.DiscRepository#findById(Long)
	 */
	ResponseEntity<Disc> getDiscById(Long id)
			throws DiscNotFoundException;

	/**
	 * Retrives a name-specified Disc entity
	 *
	 * @param name the name of the Disc entity to retrive
	 * @return the result of the CRUD's retrive operation over Disc
	 * @see co.edu.unbosque.repository.DiscRepository#findByName(String)
	 */
	ResponseEntity<Disc> getDiscByName(String name)
		throws DiscNotFoundException;

	/**
	 * Retrives all the Disc entities
	 *
	 * @return the result of the CRUD's retrive operation over Disc
	 * @see co.edu.unbosque.repository.DiscRepository#findAll()
	 */
	ResponseEntity<List<Disc>> getDiscs();

	/**
	 * Updates an id-specified Disc entity
	 *
	 * @param id the id of the Disc entity to update
	 * @param updatedDisc the Disc instance with the updating information
	 * @return the result of the CRUD's update operation over Disc
	 * @see co.edu.unbosque.repository.DiscRepository#save(Disc)
	 */
	ResponseEntity<Disc> updateDiscById(Long id, Disc updatedDisc)
			throws DiscNotFoundException;

	/**
	 * Deletes an id-specified Disc entity
	 *
	 * @param id the id of the Disc entity to delete
	 * @return the result of the CRUD's delete operation over Disc
	 * @see co.edu.unbosque.repository.DiscRepository#deleteById(Long)
	 */
	ResponseEntity<?> deleteDiscById(Long id) throws DiscNotFoundException;
}

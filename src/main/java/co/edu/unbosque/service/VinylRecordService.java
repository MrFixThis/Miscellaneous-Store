package co.edu.unbosque.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import co.edu.unbosque.entity.VinylRecord;
import co.edu.unbosque.exception.VinylRecordNotFoundException;

/**
 * @author Bryan Baron
 */
public interface VinylRecordService {
	
	/**
	 * Creates a new VinylRecord
	 *
	 * @param vinylRecord the creating VinylRecord instance
	 * @return the result of the CRUD's create operation over VinylRecord
	 * @see co.edu.unbosque.repository.VinylRecordRepository#save(VinylRecord)
	 */
	ResponseEntity<VinylRecord> createVinylRecord(VinylRecord vinylRecord);

	/**
	 * Retrives an id-specified VinylRecord entity
	 *
	 * @param id the id of the VinylRecord entity to retrive
	 * @return the result of the CRUD's retrive operation over VinylRecord
	 * @see co.edu.unbosque.repository.VinylRecordRepository#findById(Long)
	 */
	ResponseEntity<VinylRecord> getVinylRecordById(Long id)
			throws VinylRecordNotFoundException;

	/**
	 * Retrives a name-specified VinylRecord entity
	 *
	 * @param name the name of the VinylRecord entity to retrive
	 * @return the result of the CRUD's retrive operation over VinylRecord
	 * @see co.edu.unbosque.repository.VinylRecordRepository#findByRecordProductionName(String)
	 */
	ResponseEntity<VinylRecord> getVinylRecordByName(String name)
		throws VinylRecordNotFoundException;

	/**
	 * Retrives all the VinylRecord entities
	 *
	 * @return the result of the CRUD's retrive operation over VinylRecord
	 * @see co.edu.unbosque.repository.VinylRecordRepository#findAll()
	 */
	ResponseEntity<List<VinylRecord>> getVinylRecords();

	/**
	 * Updates an id-specified VinylRecord entity
	 *
	 * @param id the id of the VinylRecord entity to update
	 * @param updatedVinylRecord the VinylRecord instance with the updating information
	 * @return the result of the CRUD's update operation over VinylRecord
	 * @see co.edu.unbosque.repository.VinylRecordRepository#save(VinylRecord)
	 */
	ResponseEntity<VinylRecord> updateVinylRecordById(Long id,
			VinylRecord updatedVinylRecord)
			throws VinylRecordNotFoundException;

	/**
	 * Deletes an id-specified VinylRecord entity
	 *
	 * @param id the id of the VinylRecord entity to delete
	 * @return the result of the CRUD's delete operation over VinylRecord
	 * @see co.edu.unbosque.repository.VinylRecordRepository#deleteById(Long)
	 */
	ResponseEntity<?> deleteVinylRecordById(Long id)
			throws VinylRecordNotFoundException;
}

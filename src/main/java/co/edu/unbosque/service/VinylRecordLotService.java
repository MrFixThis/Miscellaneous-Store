package co.edu.unbosque.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import co.edu.unbosque.entity.VinylRecordLot;
import co.edu.unbosque.exception.VinylRecordLotNotFoundException;

/**
 * @author Bryan Baron
 */
public interface VinylRecordLotService {
	
	/**
	 * Creates a new VinylRecordLot
	 *
	 * @param vinylRecordLot the creating VinylRecordLot instance
	 * @return the result of the CRUD's create operation over VinylRecordLot
	 * @see co.edu.unbosque.repository.VinylRecordLotRepository#save(VinylRecordLot)
	 */
	ResponseEntity<VinylRecordLot> createVinylRecordLot(VinylRecordLot vinylRecordLot);

	/**
	 * Retrives an id-specified VinylRecordLot entity
	 *
	 * @param id the id of the VinylRecordLot entity to retrive
	 * @return the result of the CRUD's retrive operation over VinylRecordLot
	 * @see co.edu.unbosque.repository.VinylRecordLotRepository#findById(Long)
	 */
	ResponseEntity<VinylRecordLot> getVinylRecordLotById(Long id)
			throws VinylRecordLotNotFoundException;

	/**
	 * Retrives a record production name-specified VinylRecordLot entity
	 *
	 * @param name the name of the VinylRecordLot entity to retrive
	 * @return the result of the CRUD's retrive operation over VinylRecordLot
	 * @see co.edu.unbosque.repository.VinylRecordLotRepository#findByRecordProductionName(String)
	 */
	ResponseEntity<VinylRecordLot> getVinylRecordLotByRecordProductionName(
			String recordProductionName) throws VinylRecordLotNotFoundException;

	/**
	 * Retrives all the VinylRecordLot entities
	 *
	 * @return the result of the CRUD's retrive operation over VinylRecordLot
	 * @see co.edu.unbosque.repository.VinylRecordLotRepository#findAll()
	 */
	ResponseEntity<List<VinylRecordLot>> getVinylRecordLots();

	/**
	 * Updates an id-specified VinylRecordLot entity
	 *
	 * @param id the id of the VinylRecordLot entity to update
	 * @param updatedVinylRecordLot the VinylRecordLot instance with the updating information
	 * @return the result of the CRUD's update operation over VinylRecordLot
	 * @see co.edu.unbosque.repository.VinylRecordLotRepository#save(VinylRecordLot)
	 */
	ResponseEntity<VinylRecordLot> updateVinylRecordLotById(Long id,
			VinylRecordLot updatedVinylRecordLot)
			throws VinylRecordLotNotFoundException;

	/**
	 * Deletes an id-specified VinylRecordLot entity
	 *
	 * @param id the id of the VinylRecordLot entity to delete
	 * @return the result of the CRUD's delete operation over VinylRecordLot
	 * @see co.edu.unbosque.repository.VinylRecordLotRepository#deleteById(Long)
	 */
	ResponseEntity<?> deleteVinylRecordLotById(Long id)
			throws VinylRecordLotNotFoundException;
}

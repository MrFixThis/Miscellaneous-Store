package com.store.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.store.exception.VinylRecordLotNotFoundException;
import com.store.model.VinylRecordLot;

/**
 * @author Bryan Baron
 */
public interface VinylRecordLotService {
	
	/**
	 * Creates a new VinylRecordLot
	 *
	 * @param vinylRecordLot the creating VinylRecordLot instance
	 * @return the result of the CRUD's create operation over VinylRecordLot
	 */
	ResponseEntity<VinylRecordLot> createVinylRecordLot(VinylRecordLot vinylRecordLot);

	/**
	 * Retrives an id-specified VinylRecordLot entity
	 *
	 * @param id the id of the VinylRecordLot entity to retrive
	 * @return the result of the CRUD's retrive operation over VinylRecordLot
	 */
	ResponseEntity<VinylRecordLot> getVinylRecordLotById(Long id)
			throws VinylRecordLotNotFoundException;

	/**
	 * Retrives a record production name-specified VinylRecordLot entity
	 *
	 * @param recordProductionName the production name of the VinylRecordLot entity to retrive
	 * @return the result of the CRUD's retrive operation over VinylRecordLot
	 */
	ResponseEntity<VinylRecordLot> getVinylRecordLotByRecordProductionName(
			String recordProductionName) throws VinylRecordLotNotFoundException;

	/**
	 * Retrives all the VinylRecordLot entities
	 *
	 * @return the result of the CRUD's retrive operation over VinylRecordLot
	 */
	ResponseEntity<List<VinylRecordLot>> getVinylRecordLots();

	/**
	 * Updates an id-specified VinylRecordLot entity
	 *
	 * @param id the id of the VinylRecordLot entity to update
	 * @param updatedVinylRecordLot the VinylRecordLot instance with the updating information
	 * @return the result of the CRUD's update operation over VinylRecordLot
	 */
	ResponseEntity<VinylRecordLot> updateVinylRecordLotById(Long id,
			VinylRecordLot updatedVinylRecordLot)
			throws VinylRecordLotNotFoundException;

	/**
	 * Deletes an id-specified VinylRecordLot entity
	 *
	 * @param id the id of the VinylRecordLot entity to delete
	 * @return the result of the CRUD's delete operation over VinylRecordLot
	 */
	ResponseEntity<?> deleteVinylRecordLotById(Long id)
			throws VinylRecordLotNotFoundException;
}

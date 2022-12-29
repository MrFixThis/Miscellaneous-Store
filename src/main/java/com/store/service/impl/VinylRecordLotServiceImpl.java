package com.store.service.impl;

import java.net.URI;
import java.util.List;

import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.store.exception.VinylRecordLotNotFoundException;
import com.store.model.VinylRecordLot;
import com.store.repository.VinylRecordLotRepository;
import com.store.service.VinylRecordLotService;

import lombok.AllArgsConstructor;

/**
 * @author Bryan Baron
 */
@Service
@AllArgsConstructor
public class VinylRecordLotServiceImpl implements VinylRecordLotService {

	private VinylRecordLotRepository vinylRecordLotRepository;

	/**
	 * Creates a new VinylRecordLot
	 *
	 * @param vinylRecordLot the creating VinylRecordLot instance
	 * @return the result of the CRUD's create operation over VinylRecordLot
	 */
	@Override
	public ResponseEntity<VinylRecordLot> createVinylRecordLot(VinylRecordLot vinylRecordLot) {
		final VinylRecordLot savedVinylRecordLot =
			vinylRecordLotRepository.save(vinylRecordLot);
		return ResponseEntity.created(
				URI.create(String.format("/api/v1/vinyl_record_lots/%d",
					savedVinylRecordLot.getId())))
						.allow(HttpMethod.GET).body(savedVinylRecordLot);
	}

	/**
	 * Retrives an id-specified VinylRecordLot entity
	 *
	 * @param id the id of the VinylRecordLot entity to retrive
	 * @return the result of the CRUD's retrive operation over VinylRecordLot
	 */
	@Override
	public ResponseEntity<VinylRecordLot> getVinylRecordLotById(Long id)
		throws VinylRecordLotNotFoundException {
		final VinylRecordLot vinylRecordLot = vinylRecordLotRepository.findById(id)
			.orElseThrow(() -> new VinylRecordLotNotFoundException(
							String.format("Vinyl record with id %d not found",
								id)
							));
		return ResponseEntity.ok(vinylRecordLot);
	}

	/**
	 * Retrives a record production name-specified VinylRecordLot entity
	 *
	 * @param recordProductionName the production name of the VinylRecordLot entity to retrive
	 * @return the result of the CRUD's retrive operation over VinylRecordLot
	 */
	@Override
	public ResponseEntity<VinylRecordLot> getVinylRecordLotByRecordProductionName(
			String recordProductionName) throws VinylRecordLotNotFoundException {
		final VinylRecordLot vinylRecordLot =
			vinylRecordLotRepository.findByRecordProductionName(recordProductionName)
				.orElseThrow(() -> new VinylRecordLotNotFoundException(
								String.format("vinylRecordLot with name %s not found",
									recordProductionName)
								));
		return ResponseEntity.ok(vinylRecordLot);
	}

	/**
	 * Retrives all the VinylRecordLot entities
	 *
	 * @return the result of the CRUD's retrive operation over VinylRecordLot
	 */
	@Override
	public ResponseEntity<List<VinylRecordLot>> getVinylRecordLots() {
		final List<VinylRecordLot> vinylRecordLots = vinylRecordLotRepository.findAll();
		return ResponseEntity.ok(vinylRecordLots);
	}

	/**
	 * Updates an id-specified VinylRecordLot entity
	 *
	 * @param id the id of the VinylRecordLot entity to update
	 * @param updatedVinylRecord the VinylRecordLot instance with the updating information
	 * @return the result of the CRUD's update operation over VinylRecordLot
	 */
	@Override
	public ResponseEntity<VinylRecordLot> updateVinylRecordLotById(Long id,
			VinylRecordLot updatedVinylRecord)
			throws VinylRecordLotNotFoundException {
		VinylRecordLot vinylRecordLot = vinylRecordLotRepository.findById(id)
			.orElseThrow(() -> new VinylRecordLotNotFoundException(
							String.format("Vinyl record with id %d not found",
								id)
							));

		vinylRecordLot.setRecordProductionName(
				updatedVinylRecord.getRecordProductionName());
		vinylRecordLot.setArtistGroupName(updatedVinylRecord.getArtistGroupName());
		vinylRecordLot.setPublicationDate(updatedVinylRecord.getPublicationDate());
		vinylRecordLot.setMusicalGenre(updatedVinylRecord.getMusicalGenre());
		vinylRecordLot.setPricePerUnit(updatedVinylRecord.getPricePerUnit());
		vinylRecordLot.setAvailableUnits(updatedVinylRecord.getAvailableUnits());

		vinylRecordLot = vinylRecordLotRepository.save(vinylRecordLot);
		return ResponseEntity.ok(vinylRecordLot);
	}

	/**
	 * Deletes an id-specified VinylRecordLot entity
	 *
	 * @param id the id of the VinylRecordLot entity to delete
	 * @return the result of the CRUD's delete operation over VinylRecordLot
	 */
	@Override
	public ResponseEntity<?> deleteVinylRecordLotById(Long id)
		throws VinylRecordLotNotFoundException {
		VinylRecordLot vinylRecordLot = vinylRecordLotRepository.findById(id)
			.orElseThrow(() -> new VinylRecordLotNotFoundException(
							String.format("Vinyl record with id %d not found",
								id)
							));
		vinylRecordLotRepository.delete(vinylRecordLot);

		return ResponseEntity.noContent().build();
	}
}

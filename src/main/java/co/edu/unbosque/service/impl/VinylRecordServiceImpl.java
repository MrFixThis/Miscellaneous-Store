package co.edu.unbosque.service.impl;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import co.edu.unbosque.entity.VinylRecord;
import co.edu.unbosque.exception.VinylRecordNotFoundException;
import co.edu.unbosque.repository.VinylRecordRepository;
import co.edu.unbosque.service.VinylRecordService;
import lombok.AllArgsConstructor;

/**
 * VinylRecordServiceImpl
 */
@Service
@AllArgsConstructor
public class VinylRecordServiceImpl implements VinylRecordService {

	private VinylRecordRepository vinylRecordRepository;

	/**
	 * Creates a new VinylRecord
	 *
	 * @param vinylRecord the creating VinylRecord instance
	 * @return the result of the CRUD's create operation over VinylRecord
	 * @see co.edu.unbosque.repository.VinylRecordRepository#save(VinylRecord)
	 */
	@Override
	public ResponseEntity<VinylRecord> createVinylRecord(VinylRecord vinylRecord) {
		final VinylRecord savedVinylRecord =
			vinylRecordRepository.save(vinylRecord);
		return ResponseEntity.ok(savedVinylRecord);
	}

	/**
	 * Retrives an id-specified VinylRecord entity
	 *
	 * @param id the id of the VinylRecord entity to retrive
	 * @return the result of the CRUD's retrive operation over VinylRecord
	 * @see co.edu.unbosque.repository.VinylRecordRepository#findById(Long)
	 */
	@Override
	public ResponseEntity<VinylRecord> getVinylRecordById(Long id)
		throws VinylRecordNotFoundException {
		final VinylRecord vinylRecord = vinylRecordRepository.findById(id)
			.orElseThrow(() -> new VinylRecordNotFoundException(
							String.format("Vinyl record with id %d not found",
								id)
							));
		return ResponseEntity.ok(vinylRecord);
	}

	/**
	 * Retrives a record production name-specified VinylRecord entity
	 *
	 * @param recordProductionName the record production name of the VinylRecord entity to retrive
	 * @return the result of the CRUD's retrive operation over VinylRecord
	 * @see co.edu.unbosque.repository.VinylRecordRepository#findByRecordProductionName(String)
	 */
	@Override
	public ResponseEntity<VinylRecord> getVinylRecordByRecordProductionName(
			String recordProductionName) throws VinylRecordNotFoundException {
		final VinylRecord vinylRecord =
			vinylRecordRepository.findByRecordProductionName(recordProductionName)
				.orElseThrow(() -> new VinylRecordNotFoundException(
								String.format("vinylRecord with name %s not found",
									recordProductionName)
								));
		return ResponseEntity.ok(vinylRecord);
	}

	/**
	 * Retrives all the VinylRecord entities
	 *
	 * @return the result of the CRUD's retrive operation over VinylRecord
	 * @see co.edu.unbosque.repository.VinylRecordRepository#findAll()
	 */
	@Override
	public ResponseEntity<List<VinylRecord>> getVinylRecords() {
		final List<VinylRecord> vinylRecords = vinylRecordRepository.findAll();
		return ResponseEntity.ok(vinylRecords);
	}

	/**
	 * Updates an id-specified VinylRecord entity
	 *
	 * @param id the id of the VinylRecord entity to update
	 * @param updatedVinylRecord the VinylRecord instance with the updating information
	 * @return the result of the CRUD's update operation over VinylRecord
	 * @see co.edu.unbosque.repository.VinylRecordRepository#save(VinylRecord)
	 */
	@Override
	public ResponseEntity<VinylRecord> updateVinylRecordById(Long id,
			VinylRecord updatedVinylRecord)
			throws VinylRecordNotFoundException {
		VinylRecord vinylRecord = vinylRecordRepository.findById(id)
			.orElseThrow(() -> new VinylRecordNotFoundException(
							String.format("Vinyl record with id %d not found",
								id)
							));

		vinylRecord.setRecordProductionName(
				updatedVinylRecord.getRecordProductionName());
		vinylRecord.setArtistGroupName(updatedVinylRecord.getArtistGroupName());
		vinylRecord.setPublicationDate(updatedVinylRecord.getPublicationDate());
		vinylRecord.setMusicalGenre(updatedVinylRecord.getMusicalGenre());
		vinylRecord.setPrice(updatedVinylRecord.getPrice());
		vinylRecord.setAvailableUnits(updatedVinylRecord.getAvailableUnits());

		vinylRecord = vinylRecordRepository.save(vinylRecord);
		return ResponseEntity.ok(vinylRecord);
	}

	/**
	 * Deletes an id-specified VinylRecord entity
	 *
	 * @param id the id of the VinylRecord entity to delete
	 * @return the result of the CRUD's delete operation over VinylRecord
	 * @see co.edu.unbosque.repository.VinylRecordRepository#deleteById(Long)
	 */
	@Override
	public ResponseEntity<?> deleteVinylRecordById(Long id)
		throws VinylRecordNotFoundException {
		VinylRecord vinylRecord = vinylRecordRepository.findById(id)
			.orElseThrow(() -> new VinylRecordNotFoundException(
							String.format("Vinyl record with id %d not found",
								id)
							));
		vinylRecordRepository.delete(vinylRecord);

		return ResponseEntity.noContent().build();
	}
}

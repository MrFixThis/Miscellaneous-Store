package co.edu.unbosque.service.impl;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import co.edu.unbosque.entity.VinylRecordLot;
import co.edu.unbosque.exception.VinylRecordLotNotFoundException;
import co.edu.unbosque.repository.VinylRecordLotRepository;
import co.edu.unbosque.service.VinylRecordLotService;
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
	 * @see co.edu.unbosque.repository.VinylRecordLotRepository#save(VinylRecordLot)
	 */
	@Override
	public ResponseEntity<VinylRecordLot> createVinylRecordLot(VinylRecordLot vinylRecordLot) {
		final VinylRecordLot savedVinylRecord =
			vinylRecordLotRepository.save(vinylRecordLot);
		return ResponseEntity.ok(savedVinylRecord);
	}

	/**
	 * Retrives an id-specified VinylRecordLot entity
	 *
	 * @param id the id of the VinylRecordLot entity to retrive
	 * @return the result of the CRUD's retrive operation over VinylRecordLot
	 * @see co.edu.unbosque.repository.VinylRecordLotRepository#findById(Long)
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
	 * @param name the name of the VinylRecordLot entity to retrive
	 * @return the result of the CRUD's retrive operation over VinylRecordLot
	 * @see co.edu.unbosque.repository.VinylRecordLotRepository#findByRecordProductionName(String)
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
	 * @see co.edu.unbosque.repository.VinylRecordLotRepository#findAll()
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
	 * @param updatedVinylRecordLot the VinylRecordLot instance with the updating information
	 * @return the result of the CRUD's update operation over VinylRecordLot
	 * @see co.edu.unbosque.repository.VinylRecordLotRepository#save(VinylRecordLot)
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
	 * @see co.edu.unbosque.repository.VinylRecordLotRepository#deleteById(Long)
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

package co.edu.unbosque.service.impl;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import co.edu.unbosque.entity.DiscLot;
import co.edu.unbosque.exception.DiscLotNotFoundException;
import co.edu.unbosque.repository.DiscLotRepository;
import co.edu.unbosque.service.DiscService;
import lombok.AllArgsConstructor;

/**
 * @author Bryan Baron
 */
@Service
@AllArgsConstructor
public class DiscServiceImpl implements DiscService {

	private DiscLotRepository discLotRepository;

	/**
	 * Creates a new Disc
	 *
	 * @param discLot the creating Disc instance
	 * @return the result of the CRUD's create operation over Disc
	 * @see co.edu.unbosque.repository.DiscLotRepository#save(DiscLot)
	 */
	@Override
	public ResponseEntity<DiscLot> createDisc(DiscLot discLot) {
		final DiscLot savedDisc = discLotRepository.save(discLot);
		return ResponseEntity.ok(savedDisc);
	}

	/**
	 * Retrives an id-specified Disc entity
	 *
	 * @param id the id of the Disc entity to retrive
	 * @return the result of the CRUD's retrive operation over Disc
	 * @see co.edu.unbosque.repository.DiscLotRepository#findById(Long)
	 */
	@Override
	public ResponseEntity<DiscLot> getDiscById(Long id)
		throws DiscLotNotFoundException {
		final DiscLot discLot = discLotRepository.findById(id)
			.orElseThrow(() -> new DiscLotNotFoundException(
							String.format("DiscLot with id %d not found", id)
						));
		return ResponseEntity.ok(discLot);
	}

	/**
	 * Retrives a name-specified Disc entity
	 *
	 * @param name the name of the Disc entity to retrive
	 * @return the result of the CRUD's retrive operation over Disc
	 * @see co.edu.unbosque.repository.DiscLotRepository#findByName(String)
	 */
	@Override
	public ResponseEntity<DiscLot> getDiscByName(String name)
		throws DiscLotNotFoundException {
		final DiscLot discLot = discLotRepository.findByName(name)
			.orElseThrow(() -> new DiscLotNotFoundException(
							String.format("DiscLot with name %s not found", name)
						));
		return ResponseEntity.ok(discLot);
	}

	/**
	 * Retrives all the Disc entities
	 *
	 * @return the result of the CRUD's retrive operation over Disc
	 * @see co.edu.unbosque.repository.DiscLotRepository#findAll()
	 */
	@Override
	public ResponseEntity<List<DiscLot>> getDiscs() {
		final List<DiscLot> discLots = discLotRepository.findAll();
		return ResponseEntity.ok(discLots);
	}

	/**
	 * Updates an id-specified Disc entity
	 *
	 * @param id the id of the Disc entity to update
	 * @param updatedDiscLot the Disc instance with the updating information
	 * @return the result of the CRUD's update operation over Disc
	 * @see co.edu.unbosque.repository.DiscLotRepository#save(DiscLot)
	 */
	@Override
	public ResponseEntity<DiscLot> updateDiscById(Long id, DiscLot updatedDiscLot)
			throws DiscLotNotFoundException {
		DiscLot discLot = discLotRepository.findById(id)
			.orElseThrow(() -> new DiscLotNotFoundException(
							String.format("DiscLot with id %d not found", id)
						));

		discLot.setName(updatedDiscLot.getName());
		discLot.setPublicationDate(updatedDiscLot.getPublicationDate());
		discLot.setDiscFormat(updatedDiscLot.getDiscFormat());
		discLot.setExecutionTimeInMinutes(updatedDiscLot.getExecutionTimeInMinutes());
		discLot.setContentLanguage(updatedDiscLot.getContentLanguage());
		discLot.setContentClassification(updatedDiscLot.getContentClassification());
		discLot.setPricePerUnit(updatedDiscLot.getPricePerUnit());
		discLot.setAvailableUnits(updatedDiscLot.getAvailableUnits());

		discLot = discLotRepository.save(discLot);
		return ResponseEntity.ok(discLot);
	}

	/**
	 * Deletes an id-specified Disc entity
	 *
	 * @param id the id of the Disc entity to delete
	 * @return the result of the CRUD's delete operation over Disc
	 * @see co.edu.unbosque.repository.DiscLotRepository#deleteById(Long)
	 */
	@Override
	public ResponseEntity<?> deleteDiscById(Long id)
		throws DiscLotNotFoundException {
		DiscLot discLot = discLotRepository.findById(id)
			.orElseThrow(() -> new DiscLotNotFoundException(
							String.format("DiscLot with id %d not found", id)
						));
		discLotRepository.delete(discLot);

		return ResponseEntity.noContent().build();
	}
}

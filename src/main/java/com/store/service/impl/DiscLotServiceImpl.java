package com.store.service.impl;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.store.exception.DiscLotNotFoundException;
import com.store.model.DiscLot;
import com.store.repository.DiscLotRepository;
import com.store.service.DiscLotService;

import lombok.AllArgsConstructor;

/**
 * @author Bryan Baron
 */
@Service
@AllArgsConstructor
public class DiscLotServiceImpl implements DiscLotService {

	private DiscLotRepository discLotRepository;

	/**
	 * Creates a new DiscLot
	 *
	 * @param discLot the creating DiscLot instance
	 * @return the result of the CRUD's create operation over DiscLot
	 */
	@Override
	public ResponseEntity<DiscLot> createDiscLot(DiscLot discLot) {
		final DiscLot savedDiscLot = discLotRepository.save(discLot);
		return ResponseEntity.ok(savedDiscLot);
	}

	/**
	 * Retrives an id-specified DiscLot entity
	 *
	 * @param id the id of the DiscLot entity to retrive
	 * @return the result of the CRUD's retrive operation over DiscLot
	 */
	@Override
	public ResponseEntity<DiscLot> getDiscLotById(Long id)
		throws DiscLotNotFoundException {
		final DiscLot discLot = discLotRepository.findById(id)
			.orElseThrow(() -> new DiscLotNotFoundException(
							String.format("DiscLot with id %d not found", id)
						));
		return ResponseEntity.ok(discLot);
	}

	/**
	 * Retrives a name-specified DiscLot entity
	 *
	 * @param name the name of the DiscLot entity to retrive
	 * @return the result of the CRUD's retrive operation over DiscLot
	 */
	@Override
	public ResponseEntity<DiscLot> getDiscLotByName(String name)
		throws DiscLotNotFoundException {
		final DiscLot discLot = discLotRepository.findByName(name)
			.orElseThrow(() -> new DiscLotNotFoundException(
							String.format("DiscLot with name %s not found", name)
						));
		return ResponseEntity.ok(discLot);
	}

	/**
	 * Retrives all the DiscLot entities
	 *
	 * @return the result of the CRUD's retrive operation over DiscLot
	 */
	@Override
	public ResponseEntity<List<DiscLot>> getDiscLots() {
		final List<DiscLot> discLots = discLotRepository.findAll();
		return ResponseEntity.ok(discLots);
	}

	/**
	 * Updates an id-specified DiscLot entity
	 *
	 * @param id the id of the DiscLot entity to update
	 * @param updatedDiscLot the DiscLot instance with the updating information
	 * @return the result of the CRUD's update operation over DiscLot
	 */
	@Override
	public ResponseEntity<DiscLot> updateDiscLotById(Long id, DiscLot updatedDiscLot)
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
	 * Deletes an id-specified DiscLot entity
	 *
	 * @param id the id of the DiscLot entity to delete
	 * @return the result of the CRUD's delete operation over DiscLot
	 */
	@Override
	public ResponseEntity<?> deleteDiscLotById(Long id)
		throws DiscLotNotFoundException {
		DiscLot discLot = discLotRepository.findById(id)
			.orElseThrow(() -> new DiscLotNotFoundException(
							String.format("DiscLot with id %d not found", id)
						));
		discLotRepository.delete(discLot);

		return ResponseEntity.noContent().build();
	}
}

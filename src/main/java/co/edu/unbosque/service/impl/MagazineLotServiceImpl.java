package co.edu.unbosque.service.impl;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import co.edu.unbosque.entity.MagazineLot;
import co.edu.unbosque.exception.MagazineLotNotFoundException;
import co.edu.unbosque.repository.MagazineLotRepository;
import co.edu.unbosque.service.MagazineLotService;
import lombok.AllArgsConstructor;

/**
 * @author Bryan Baron
 */
@Service
@AllArgsConstructor
public class MagazineLotServiceImpl implements MagazineLotService {

	private MagazineLotRepository magazineLotRepository;

	/**
	 * Creates a new MagazineLot
	 *
	 * @param magazineLot the creating MagazineLot instance
	 * @return the result of the CRUD's create operation over MagazineLot
	 * @see co.edu.unbosque.repository.MagazineLotRepository#save(MagazineLot)
	 */
	@Override
	public ResponseEntity<MagazineLot> createMagazineLot(MagazineLot magazineLot) {
		final MagazineLot savedMagazineLot = magazineLotRepository.save(magazineLot);
		return ResponseEntity.ok(savedMagazineLot);
	}

	/**
	 * Retrives an isbn-specified MagazineLot entity
	 *
	 * @param isbn the isbn of the MagazineLot entity to retrive
	 * @return the result of the CRUD's retrive operation over MagazineLot
	 * @see co.edu.unbosque.repository.MagazineLotRepository#findById(String)
	 */
	@Override
	public ResponseEntity<MagazineLot> getMagazineLotByIsbn(String isbn)
		throws MagazineLotNotFoundException {
		final MagazineLot magazineLot = magazineLotRepository.findById(isbn)
			.orElseThrow(() -> new MagazineLotNotFoundException(
							String.format("MagazineLot with isbn % not found", isbn)
						));
		return ResponseEntity.ok(magazineLot);
	}

	/**
	 * Retrives a name-specified MagazineLot entity
	 *
	 * @param name the name of the MagazineLot entity to retrive
	 * @return the result of the CRUD's retrive operation over MagazineLot
	 * @see co.edu.unbosque.repository.MagazineLotRepository#findByName(String)
	 */
	@Override
	public ResponseEntity<MagazineLot> getMagazineLotByName(String name)
		throws MagazineLotNotFoundException {
		final MagazineLot magazineLot = magazineLotRepository.findByName(name)
			.orElseThrow(() -> new MagazineLotNotFoundException(
							String.format("magazineLot with name %s not found",
								name)
							));
		return ResponseEntity.ok(magazineLot);
	}

	/**
	 * Retrives all the MagazineLot entities
	 *
	 * @return the result of the CRUD's retrive operation over MagazineLot
	 * @see co.edu.unbosque.repository.MagazineLotRepository#findAll()
	 */
	@Override
	public ResponseEntity<List<MagazineLot>> getMagazineLots() {
		final List<MagazineLot> magazineLots = magazineLotRepository.findAll();
		return ResponseEntity.ok(magazineLots);
	}

	/**
	 * Updates an isbn-specified MagazineLot entity
	 *
	 * @param isbn the isbn of the MagazineLot entity to update
	 * @param updatedMagazineLot the MagazineLot instance with the updating information
	 * @return the result of the CRUD's update operation over MagazineLot
	 * @see co.edu.unbosque.repository.MagazineLotRepository#save(MagazineLot)
	 */
	@Override
	public ResponseEntity<MagazineLot> updateMagazineLotByIsbn(String isbn,
			MagazineLot updatedMagazineLot)
			throws MagazineLotNotFoundException {
		MagazineLot magazineLot = magazineLotRepository.findById(isbn)
			.orElseThrow(() -> new MagazineLotNotFoundException(
							String.format("MagazineLot with isbn % not found",
								isbn)
							));

		magazineLot.setName(updatedMagazineLot.getName());
		magazineLot.setPublisherName(updatedMagazineLot.getPublisherName());
		magazineLot.setPricePerUnit(updatedMagazineLot.getPricePerUnit());
		magazineLot.setAvailableUnits(updatedMagazineLot.getAvailableUnits());

		magazineLot = magazineLotRepository.save(magazineLot);
		return ResponseEntity.ok(magazineLot);
	}

	/**
	 * Deletes an isbn-specified MagazineLot entity
	 *
	 * @param isbn the isbn of the MagazineLot entity to delete
	 * @return the result of the CRUD's delete operation over MagazineLot
	 * @see co.edu.unbosque.repository.MagazineLotRepository#deleteById(String)
	 */
	@Override
	public ResponseEntity<?> deleteMagazineLotByIsbn(String isbn)
		throws MagazineLotNotFoundException {
		MagazineLot magazineLot = magazineLotRepository.findById(isbn)
			.orElseThrow(() -> new MagazineLotNotFoundException(
							String.format("MagazineLot with isbn % not found",
								isbn)
							));
		magazineLotRepository.delete(magazineLot);

		return ResponseEntity.noContent().build();
	}
}

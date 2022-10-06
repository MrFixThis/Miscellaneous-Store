package co.edu.unbosque.service.impl;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import co.edu.unbosque.entity.Magazine;
import co.edu.unbosque.exception.MagazineNotFoundException;
import co.edu.unbosque.repository.MagazineRepository;
import co.edu.unbosque.service.MagazineService;
import lombok.AllArgsConstructor;

/**
 * @author Bryan Baron
 */
@Service
@AllArgsConstructor
public class MagazineServiceImpl implements MagazineService {

	private MagazineRepository magazineRepository;

	/**
	 * Creates a new Magazine
	 *
	 * @param magazine the creating Magazine instance
	 * @return the result of the CRUD's create operation over Magazine
	 * @see co.edu.unbosque.repository.MagazineRepository#save(Magazine)
	 */
	@Override
	public ResponseEntity<Magazine> createMagazine(Magazine magazine) {
		final Magazine savedMagazine = magazineRepository.save(magazine);
		return ResponseEntity.ok(savedMagazine);
	}

	/**
	 * Retrives an isbn-specified Magazine entity
	 *
	 * @param isbn the isbn of the Magazine entity to retrive
	 * @return the result of the CRUD's retrive operation over Magazine
	 * @see co.edu.unbosque.repository.MagazineRepository#findById(String)
	 */
	@Override
	public ResponseEntity<Magazine> getMagazineByIsbn(String isbn)
		throws MagazineNotFoundException {
		final Magazine magazine = magazineRepository.findById(isbn)
			.orElseThrow(() -> new MagazineNotFoundException(
							String.format("Magazine with isbn % not found", isbn)
						));
		return ResponseEntity.ok(magazine);
	}

	/**
	 * Retrives a name-specified Magazine entity
	 *
	 * @param name the name of the Magazine entity to retrive
	 * @return the result of the CRUD's retrive operation over Magazine
	 * @see co.edu.unbosque.repository.MagazineRepository#findByName(String)
	 */
	@Override
	public ResponseEntity<Magazine> getMagazineByName(String name)
		throws MagazineNotFoundException {
		final Magazine magazine = magazineRepository.findByName(name)
			.orElseThrow(() -> new MagazineNotFoundException(
							String.format("magazine with name %s not found",
								name)
							));
		return ResponseEntity.ok(magazine);
	}

	/**
	 * Retrives all the Magazine entities
	 *
	 * @return the result of the CRUD's retrive operation over Magazine
	 * @see co.edu.unbosque.repository.MagazineRepository#findAll()
	 */
	@Override
	public ResponseEntity<List<Magazine>> getMagazines() {
		final List<Magazine> magazines = magazineRepository.findAll();
		return ResponseEntity.ok(magazines);
	}

	/**
	 * Updates an isbn-specified Magazine entity
	 *
	 * @param isbn the isbn of the Magazine entity to update
	 * @param updatedMagazine the Magazine instance with the updating information
	 * @return the result of the CRUD's update operation over Magazine
	 * @see co.edu.unbosque.repository.MagazineRepository#save(Magazine)
	 */
	@Override
	public ResponseEntity<Magazine> updateMagazineByIsbn(String isbn,
			Magazine updatedMagazine)
			throws MagazineNotFoundException {
		Magazine magazine = magazineRepository.findById(isbn)
			.orElseThrow(() -> new MagazineNotFoundException(
							String.format("Magazine with isbn % not found",
								isbn)
							));

		magazine.setName(updatedMagazine.getName());
		magazine.setPublisherName(updatedMagazine.getPublisherName());
		magazine.setPrice(updatedMagazine.getPrice());
		magazine.setAvailableUnits(updatedMagazine.getAvailableUnits());

		magazine = magazineRepository.save(magazine);
		return ResponseEntity.ok(magazine);
	}

	/**
	 * Deletes an isbn-specified Magazine entity
	 *
	 * @param isbn the isbn of the Magazine entity to delete
	 * @return the result of the CRUD's delete operation over Magazine
	 * @see co.edu.unbosque.repository.MagazineRepository#deleteById(String)
	 */
	@Override
	public ResponseEntity<?> deleteMagazineByIsbn(String isbn)
		throws MagazineNotFoundException {
		Magazine magazine = magazineRepository.findById(isbn)
			.orElseThrow(() -> new MagazineNotFoundException(
							String.format("Magazine with isbn % not found",
								isbn)
							));
		magazineRepository.delete(magazine);

		return ResponseEntity.noContent().build();
	}
}

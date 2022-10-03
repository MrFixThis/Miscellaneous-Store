package co.edu.unbosque.service.impl;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import co.edu.unbosque.entity.Disc;
import co.edu.unbosque.exception.DiscNotFoundException;
import co.edu.unbosque.repository.DiscRepository;
import co.edu.unbosque.service.DiscService;
import lombok.AllArgsConstructor;

/**
 * @author Bryan Baron
 */
@Service
@AllArgsConstructor
public class DiscServiceImpl implements DiscService {

	private DiscRepository discRepository;

	/**
	 * Creates a new Disc
	 *
	 * @param disc the creating Disc instance
	 * @return the result of the CRUD's create operation over Disc
	 * @see co.edu.unbosque.repository.DiscRepository#save(Disc)
	 */
	@Override
	public ResponseEntity<Disc> createDisc(Disc disc) {
		final Disc savedDisc = discRepository.save(disc);
		return ResponseEntity.ok(savedDisc);
	}

	/**
	 * Retrives an id-specified Disc entity
	 *
	 * @param id the id of the Disc entity to retrive
	 * @return the result of the CRUD's retrive operation over Disc
	 * @see co.edu.unbosque.repository.DiscRepository#findById(Long)
	 */
	@Override
	public ResponseEntity<Disc> getDiscById(Long id)
		throws DiscNotFoundException {
		final Disc disc = discRepository.findById(id)
			.orElseThrow(() -> new DiscNotFoundException(
							String.format("disc with id %d not found", id)
						));
		return ResponseEntity.ok(disc);
	}

	/**
	 * Retrives a name-specified Disc entity
	 *
	 * @param name the name of the Disc entity to retrive
	 * @return the result of the CRUD's retrive operation over Disc
	 * @see co.edu.unbosque.repository.DiscRepository#findByName(String)
	 */
	@Override
	public ResponseEntity<Disc> getDiscByName(String name)
		throws DiscNotFoundException {
		final Disc disc = discRepository.findByName(name)
			.orElseThrow(() -> new DiscNotFoundException(
							String.format("disc with name %s not found", name)
						));
		return ResponseEntity.ok(disc);
	}

	/**
	 * Retrives all the Disc entities
	 *
	 * @return the result of the CRUD's retrive operation over Disc
	 * @see co.edu.unbosque.repository.DiscRepository#findAll()
	 */
	@Override
	public ResponseEntity<List<Disc>> getDiscs() {
		final List<Disc> discs = discRepository.findAll();
		return ResponseEntity.ok(discs);
	}

	/**
	 * Updates an id-specified Disc entity
	 *
	 * @param id the id of the Disc entity to update
	 * @param updatedDisc the Disc instance with the updating information
	 * @return the result of the CRUD's update operation over Disc
	 * @see co.edu.unbosque.repository.DiscRepository#save(Disc)
	 */
	@Override
	public ResponseEntity<Disc> updateDiscById(Long id, Disc updatedDisc)
			throws DiscNotFoundException {
		Disc disc = discRepository.findById(id)
			.orElseThrow(() -> new DiscNotFoundException(
							String.format("disc with id %d not found", id)
						));

		disc.setName(updatedDisc.getName());
		disc.setPublicationDate(updatedDisc.getPublicationDate());
		disc.setDiscFormat(updatedDisc.getDiscFormat());
		disc.setExecutionTimeInMinutes(updatedDisc.getExecutionTimeInMinutes());
		disc.setContentLanguage(updatedDisc.getContentLanguage());
		disc.setContentClassification(updatedDisc.getContentClassification());
		disc.setPrice(updatedDisc.getPrice());
		disc.setAvailableUnits(updatedDisc.getAvailableUnits());

		disc = discRepository.save(disc);
		return ResponseEntity.ok(disc);
	}

	/**
	 * Deletes an id-specified Disc entity
	 *
	 * @param id the id of the Disc entity to delete
	 * @return the result of the CRUD's delete operation over Disc
	 * @see co.edu.unbosque.repository.DiscRepository#deleteById(Long)
	 */
	@Override
	public ResponseEntity<?> deleteDiscById(Long id)
		throws DiscNotFoundException {
		Disc disc = discRepository.findById(id)
			.orElseThrow(() -> new DiscNotFoundException(
							String.format("disc with id %d not found", id)
						));
		discRepository.delete(disc);

		return ResponseEntity.noContent().build();
	}
}

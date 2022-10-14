package co.edu.unbosque.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import co.edu.unbosque.entity.BookLot;
import co.edu.unbosque.exception.BookLotNotFoundException;
import co.edu.unbosque.repository.BookLotRepository;
import co.edu.unbosque.service.BookLotService;
import lombok.AllArgsConstructor;

/**
 * @author Bryan Baron
 */
@Service
@AllArgsConstructor
public class BookLotServiceImpl implements BookLotService {

	private BookLotRepository bookLotRepository;

	/**
	 * Creates a new BookLot instance
	 *
	 * @param bookLot the creating BookLot lot instance
	 * @return the result of the CRUD's create operation over BookLot
	 * @see co.edu.unbosque.repository.BookLotRepository#save(BookLot)
	 */
	@Override
	public ResponseEntity<BookLot> createBookLot(BookLot bookLot) {
		final BookLot savedBookLot = bookLotRepository.save(bookLot);
		return ResponseEntity.ok(savedBookLot);
	}

	/**
	 * Retrives an isbn-specified BookLot entity
	 *
	 * @param isbn the isbn of the BookLot entity to retrive
	 * @return the result of the CRUD's retrive operation over BookLot
	 * @see co.edu.unbosque.repository.BookLotRepository#findById(UUID)
	 */
	@Override
	public ResponseEntity<BookLot> getBookLotByIsbn(UUID isbn)
		throws BookLotNotFoundException {
		final BookLot bookLot = bookLotRepository.findById(isbn)
			.orElseThrow(() -> new BookLotNotFoundException(
							String.format("BookLot with isbn % not found", isbn)
						));
		return ResponseEntity.ok(bookLot);
	}

	/**
	 * Retrives a name-specified BookLot entity
	 *
	 * @param name the name of the BookLot entity to retrive
	 * @return the result of the CRUD's retrive operation over BookLot
	 * @see co.edu.unbosque.repository.BookLotRepository#findByName(String)
	 */
	@Override
	public ResponseEntity<BookLot> getBookLotByName(String name)
		throws BookLotNotFoundException {
		final BookLot bookLot = bookLotRepository.findByName(name)
			.orElseThrow(() -> new BookLotNotFoundException(
							String.format("BookLot with name %s not found", name)
						));
		return ResponseEntity.ok(bookLot);
	}

	/**
	 * Retrives all the BookLot entities
	 *
	 * @return the result of the CRUD's retrive operation over BookLot
	 * @see co.edu.unbosque.repository.BookLotRepository#findAll()
	 */
	@Override
	public ResponseEntity<List<BookLot>> getBookLots() {
		final List<BookLot> bookLots = bookLotRepository.findAll();
		return ResponseEntity.ok(bookLots);
	}

	/**
	 * Updates an isbn-specified BookLot entity
	 *
	 * @param isbn the isbn of the BookLot entity to update
	 * @param updatedBookLot the BookLot instance with the updating information
	 * @return the result of the CRUD's update operation over BookLot
	 * @see co.edu.unbosque.repository.BookLotRepository#save(BookLot)
	 */
	@Override
	public ResponseEntity<BookLot> updateBookLotByIsbn(UUID isbn, BookLot updatedBookLot)
		throws BookLotNotFoundException {
		BookLot bookLot = bookLotRepository.findById(isbn)
			.orElseThrow(() -> new BookLotNotFoundException(
							String.format("BookLot with isbn % not found", isbn)
						));

		bookLot.setName(updatedBookLot.getName());
		bookLot.setAuthorName(updatedBookLot.getAuthorName());
		bookLot.setPublisherName(updatedBookLot.getPublisherName());
		bookLot.setPagesNumber(updatedBookLot.getPagesNumber());
		bookLot.setPublicationDate(updatedBookLot.getPublicationDate());
		bookLot.setPricePerUnit(updatedBookLot.getPricePerUnit());
		bookLot.setAvailableUnits(updatedBookLot.getAvailableUnits());

		bookLot = bookLotRepository.save(bookLot);
		return ResponseEntity.ok(bookLot);
	}

	/**
	 * Deletes an isbn-specified BookLot entity
	 *
	 * @param isbn the isbn of the BookLot entity to delete
	 * @return the result of the CRUD's delete operation over BookLot
	 * @see co.edu.unbosque.repository.BookLotRepository#deleteById(UUID)
	 */
	@Override
	public ResponseEntity<?> deleteBookLotByIsbn(UUID isbn)
		throws BookLotNotFoundException {
		BookLot bookLot = bookLotRepository.findById(isbn)
			.orElseThrow(() -> new BookLotNotFoundException(
							String.format("BookLot with isbn % not found", isbn)
						));
		bookLotRepository.delete(bookLot);

		return ResponseEntity.noContent().build();
	}
}

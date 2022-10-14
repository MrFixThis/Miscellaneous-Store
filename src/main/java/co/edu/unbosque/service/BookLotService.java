package co.edu.unbosque.service;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;

import co.edu.unbosque.entity.BookLot;
import co.edu.unbosque.exception.BookLotNotFoundException;

/**
 * @author Bryan Baron
 */
public interface BookLotService {

	/**
	 * Creates a new BookLot instance
	 *
	 * @param book the creating BookLot lot instance
	 * @return the result of the CRUD's create operation over BookLot
	 * @see co.edu.unbosque.repository.BookLotRepository#save(BookLot)
	 */
	ResponseEntity<BookLot> createBookLot(BookLot book);

	/**
	 * Retrives an isbn-specified BookLot entity
	 *
	 * @param isbn the isbn of the BookLot entity to retrive
	 * @return the result of the CRUD's retrive operation over BookLot
	 * @see co.edu.unbosque.repository.BookLotRepository#findById(UUID)
	 */
	ResponseEntity<BookLot> getBookLotByIsbn(UUID isbn)
			throws BookLotNotFoundException;

	/**
	 * Retrives a name-specified BookLot entity
	 *
	 * @param name the name of the BookLot entity to retrive
	 * @return the result of the CRUD's retrive operation over BookLot
	 * @see co.edu.unbosque.repository.BookLotRepository#findByName(String)
	 */
	ResponseEntity<BookLot> getBookLotByName(String name)
		throws BookLotNotFoundException;

	/**
	 * Retrives all the BookLot entities
	 *
	 * @return the result of the CRUD's retrive operation over BookLot
	 * @see co.edu.unbosque.repository.BookLotRepository#findAll()
	 */
	ResponseEntity<List<BookLot>> getBookLots();

	/**
	 * Updates an isbn-specified BookLot entity
	 *
	 * @param isbn the isbn of the BookLot entity to update
	 * @param updatedBookLot the BookLot instance with the updating information
	 * @return the result of the CRUD's update operation over BookLot
	 * @see co.edu.unbosque.repository.BookLotRepository#save(BookLot)
	 */
	ResponseEntity<BookLot> updateBookLotByIsbn(UUID isbn, BookLot updatedBookLot)
			throws BookLotNotFoundException;

	/**
	 * Deletes an isbn-specified BookLot entity
	 *
	 * @param isbn the isbn of the BookLot entity to delete
	 * @return the result of the CRUD's delete operation over BookLot
	 * @see co.edu.unbosque.repository.BookLotRepository#deleteById(UUID)
	 */
	ResponseEntity<?> deleteBookLotByIsbn(UUID isbn)
			throws BookLotNotFoundException;
}

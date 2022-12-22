package com.store.service;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;

import com.store.exception.BookLotNotFoundException;
import com.store.model.BookLot;

/**
 * @author Bryan Baron
 */
public interface BookLotService {

	/**
	 * Creates a new BookLot instance
	 *
	 * @param book the creating BookLot lot instance
	 * @return the result of the CRUD's create operation over BookLot
	 */
	ResponseEntity<BookLot> createBookLot(BookLot book);

	/**
	 * Retrives an isbn-specified BookLot entity
	 *
	 * @param isbn the isbn of the BookLot entity to retrive
	 * @return the result of the CRUD's retrive operation over BookLot
	 */
	ResponseEntity<BookLot> getBookLotByIsbn(UUID isbn)
			throws BookLotNotFoundException;

	/**
	 * Retrives a name-specified BookLot entity
	 *
	 * @param name the name of the BookLot entity to retrive
	 * @return the result of the CRUD's retrive operation over BookLot
	 */
	ResponseEntity<BookLot> getBookLotByName(String name)
		throws BookLotNotFoundException;

	/**
	 * Retrives all the BookLot entities
	 *
	 * @return the result of the CRUD's retrive operation over BookLot
	 */
	ResponseEntity<List<BookLot>> getBookLots();

	/**
	 * Updates an isbn-specified BookLot entity
	 *
	 * @param isbn the isbn of the BookLot entity to update
	 * @param updatedBookLot the BookLot instance with the updating information
	 * @return the result of the CRUD's update operation over BookLot
	 */
	ResponseEntity<BookLot> updateBookLotByIsbn(UUID isbn, BookLot updatedBookLot)
			throws BookLotNotFoundException;

	/**
	 * Deletes an isbn-specified BookLot entity
	 *
	 * @param isbn the isbn of the BookLot entity to delete
	 * @return the result of the CRUD's delete operation over BookLot
	 */
	ResponseEntity<?> deleteBookLotByIsbn(UUID isbn)
			throws BookLotNotFoundException;
}

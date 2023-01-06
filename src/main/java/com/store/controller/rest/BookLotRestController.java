package com.store.controller.rest;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.store.model.BookLot;
import com.store.model.Inventory;
import com.store.service.BookLotService;
import com.store.service.InventoryService;

import lombok.AllArgsConstructor;

/**
 * @author Bryan Baron
 */
@RestController
@RequestMapping(path = "/api")
@AllArgsConstructor
public class BookLotRestController {

	private BookLotService bookLotService;
	private InventoryService inventoryService;

	/**
	 * Creates a new BookLot entity.
	 *
	 * @param inventoryId id of the Inventory entity with which the BookLot entity
	 * being created is related to.
	 * @param bookLot request body with the information of the BookLot entity being
	 * created.
	 * @return the response of the POST request.
	 */
	@PostMapping("/v1/book_lots")
	public ResponseEntity<BookLot> createBookLot(
			@RequestParam(name = "inventoryId") Long inventoryId,
			@RequestBody BookLot bookLot) {
		final ResponseEntity<Inventory> bookLotInventory =
			inventoryService.getInventoryById(inventoryId);
		ResponseEntity<BookLot> createdBookLot = null;

		bookLot.setBookLotInventory(bookLotInventory.getBody());
		createdBookLot =  bookLotService.createBookLot(bookLot);
		return createdBookLot;
	}

	/**
	 * Retrieves a BookLot entity specified by isbn.
	 *
	 * @param isbn isbn of the BookLot entity being searched.
	 * @return the response of the GET request.
	 */
	@GetMapping("/v1/book_lots/{isbn}")
	public ResponseEntity<BookLot> getBookLot(@PathVariable(name = "isbn") UUID isbn) {
		final ResponseEntity<BookLot> bookLot = bookLotService.getBookLotByIsbn(isbn);
		return bookLot;
	}

	/**
	 * Retrieves all the existent BookLot entities.
	 *
	 * @return the response of the GET request.
	 */
	@GetMapping("/v1/book_lots")
	public ResponseEntity<List<BookLot>> getBookLots() {
		final ResponseEntity<List<BookLot>> bookLots =
			bookLotService.getBookLots();
		return bookLots;
	}

	/**
	 * Updates a BookLot entity specified by isbn.
	 *
	 * @param isbn isbn of the BookLot entity being updated.
	 * @param bookLot request body with the information of the BookLot entity
	 * being udpated.
	 * @return the response of the PUT request.
	 */
	@PutMapping("/v1/book_lots/{isbn}")
	public ResponseEntity<BookLot> updateBookLot(
			@PathVariable(name = "isbn") UUID isbn,
			@RequestBody BookLot bookLot) {
		final ResponseEntity<BookLot> updatedBookLot =
			bookLotService.updateBookLotByIsbn(isbn, bookLot);
		return updatedBookLot;
	}

	/**
	 * Deletes a BookLot entity specified by isbn.
	 *
	 * @param isbn isbn of the BookLot entity being deleted.
	 * @return the response of the DELETE request.
	 */
	@PostMapping("/v1/book_lots/{isbn}")
	public ResponseEntity<?> deleteBookLot(@PathVariable(name = "isbn") UUID isbn) {
		final ResponseEntity<?> deletedBookLot =
			bookLotService.deleteBookLotByIsbn(isbn);
		return deletedBookLot;
	}
}

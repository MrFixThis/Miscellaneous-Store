package com.store.controller;

import java.sql.Date;
import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.store.model.BookLot;
import com.store.model.Inventory;
import com.store.service.impl.BookLotServiceImpl;
import com.store.service.impl.InventoryServiceImpl;

import lombok.AllArgsConstructor;

/**
 * @author Bryan Baron
 */
@Controller
@AllArgsConstructor
public class BookLotController {

	private BookLotServiceImpl bookLotServiceImpl;
	private InventoryServiceImpl inventoryServiceImpl;

	/**
	 * Creates a new book lot entity
	 *
	 * @param inventoryId id of the inventory where the book lot that is beeing
	 * created will be placed
	 * @param model holder model for context model's attributes.
	 * @return the specified template view.
	 */
	@GetMapping("/book_lots/create/inventory={inventoryId}")
	public String createBookLot(@PathVariable(name = "inventoryId")
			Long inventoryId, Model model) {
		model.addAttribute("action", "post");
		model.addAttribute("inventoryId", inventoryId);
		model.addAttribute("bookLot", null);

		return "bookLotActions";
	}

	/**
	 * Creates a new book lot entity
	 *
	 * @param newBookLot POJO with the information of the book lot
	 * entity that is beeing created.
	 * @param inventoryId id of the inventory where the book lot that is beeing
	 * created will be placed
	 * @param publicationDate date of publication of the units from the disc
	 * lot entity that is beeing created
	 * @return the specified template view.
	 */
	@GetMapping("/book_lots/manage/create/{inventoryId}")
	public String createBookLot(BookLot newBookLot,
			@PathVariable(name = "inventoryId") Long inventoryId,
			@RequestParam(name = "publicationDate") String publicationDate) {

		Inventory bookLotInventory =
			inventoryServiceImpl.getInventoryById(inventoryId).getBody();
		Long branchOfficeId = bookLotInventory.getBranchOffice().getId();

		newBookLot.setPublicationDate(Date.valueOf(publicationDate));
		newBookLot.setBookLotInventory(bookLotInventory);
		bookLotServiceImpl.createBookLot(newBookLot);

		return String.format("redirect:/branch_offices/update/%d",
				branchOfficeId);
				
	}

	/**
	 * Retrieves a book lot entity specified by isbn
	 *
	 * @param isbn isbn of the book lot entity that is beeing searched
	 * @param model holder model for context model's attributes.
	 * @return the specified template view
	 */
	@GetMapping("/book_lots/{isbn}")
	public String showBookLot(@PathVariable(name = "isbn") UUID isbn,
			Model model) {
		BookLot bookLot = bookLotServiceImpl.getBookLotByIsbn(isbn).getBody();

		model.addAttribute("action", "get");
		model.addAttribute("bookLot", bookLot);

		return "bookLotActions";
	}

	/**
	 * Updates an book lot entity specified by isbn
	 *
	 * @param isbn isbn of the book lot entity that is beeing updated
	 * @param model holder model for context model's attributes.
	 * @return the specified template view
	 */
	@GetMapping("/book_lots/update/{isbn}")
	public String updateBookLot(@PathVariable(name = "isbn") UUID isbn,
			Model model) {
		BookLot bookLot = bookLotServiceImpl.getBookLotByIsbn(isbn).getBody();

		model.addAttribute("action", "put");
		model.addAttribute("bookLot", bookLot);

		return "bookLotActions";
	}

	/**
	 * Updates a new book lot entity specified by isbn
	 *
	 * @param updatedBookLot POJO with the information of the book lot
	 * entity that is beeing udpated.
	 * @param isbn isbn of the book lot entity that is beeing updated
	 * @param publicationDate date of publication of the units from the disc
	 * lot entity that is beeing updated
	 * @return the specified template view.
	 */
	@GetMapping("/book_lots/manage/update/{isbn}")
	public String updateBookLot(BookLot updatedBookLot,
			@PathVariable(name = "isbn") UUID isbn,
			@RequestParam(name = "publicationDate") String publicationDate) {

		Long branchOfficeId = bookLotServiceImpl.getBookLotByIsbn(isbn)
			.getBody().getBookLotInventory().getBranchOffice().getId();

		updatedBookLot.setPublicationDate(Date.valueOf(publicationDate));
		bookLotServiceImpl.updateBookLotByIsbn(isbn, updatedBookLot);

		return String.format("redirect:/branch_offices/update/%d",
				branchOfficeId);
	}

	/**
	 * Deletes a book lot entity specified by isbn
	 *
	 * @param isbn isbn of the book lot entity that is beeing deleted
	 * @return the specified template view
	 */
	@GetMapping("/book_lots/manage/delete/{isbn}")
	public String deleteBookLot(@PathVariable(name = "isbn") UUID isbn) {
		Long branchOfficeId =
			bookLotServiceImpl.getBookLotByIsbn(isbn).getBody()
				.getBookLotInventory().getBranchOffice().getId();
		bookLotServiceImpl.deleteBookLotByIsbn(isbn);

		return String.format("redirect:/branch_offices/update/%d",
				branchOfficeId);
	}
}

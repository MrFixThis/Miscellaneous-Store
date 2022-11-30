package co.edu.unbosque.controller;

import java.sql.Date;
import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import co.edu.unbosque.entity.Inventory;
import co.edu.unbosque.entity.BookLot;
import co.edu.unbosque.service.impl.InventoryServiceImpl;
import co.edu.unbosque.service.impl.BookLotServiceImpl;
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
	 *
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
	 *
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
	 *
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
	 *
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
	 *
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
	 *
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

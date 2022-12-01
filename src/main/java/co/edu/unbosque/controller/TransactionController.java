package co.edu.unbosque.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import co.edu.unbosque.entity.BookLot;
import co.edu.unbosque.entity.BranchOffice;
import co.edu.unbosque.entity.Client;
import co.edu.unbosque.entity.DiscLot;
import co.edu.unbosque.entity.MagazineLot;
import co.edu.unbosque.entity.Transaction;
import co.edu.unbosque.entity.VinylRecordLot;
import co.edu.unbosque.service.impl.BookLotServiceImpl;
import co.edu.unbosque.service.impl.BranchOfficeServiceImpl;
import co.edu.unbosque.service.impl.ClientServiceImpl;
import co.edu.unbosque.service.impl.DiscLotServiceImpl;
import co.edu.unbosque.service.impl.MagazineLotServiceImpl;
import co.edu.unbosque.service.impl.TransactionServiceImpl;
import co.edu.unbosque.service.impl.VinylRecordLotServiceImpl;
import lombok.AllArgsConstructor;

/**
 * @author Bryan Baron
 */
@Controller
@AllArgsConstructor
public class TransactionController {

	private TransactionServiceImpl transactionServiceImpl;
	private BranchOfficeServiceImpl branchOfficeServiceImpl;
	private ClientServiceImpl clientServiceImpl;

	private MagazineLotServiceImpl magazineLotServiceImpl;
	private BookLotServiceImpl bookLotServiceImpl;
	private DiscLotServiceImpl discLotServiceImpl;
	private VinylRecordLotServiceImpl vinylRecordLotServiceImpl;

	/**
	 * Creates a new transaction entity
	 *
	 * @param model holder model for context model's attributes.
	 * @param branchOfficeId id of the branch office where the transaction is beeing
	 * registered
	 * @return the specified template view.
	 */
	@GetMapping("/transactions/create/branch_office={branchOfficeId}")
	public String createTransaction(Model model,
			@PathVariable(name = "branchOfficeId") Long branchOfficeId) {

		BranchOffice branchOffice =
			branchOfficeServiceImpl.getBranchOfficeById(branchOfficeId).getBody();

		model.addAttribute("action", "post");
		model.addAttribute("branchOffice", branchOffice);

		return "transactionProducts";
	}

	/**
	 * Creates a new transaction entity
	 *
	 * @param model holder model for context model's attributes.
	 * @param branchOfficeId id of the branch office where the transaction is beeing
	 * registered
	 * @param productType type of the selected product to register the transaction
	 * @param productId id of the selected product to register the transaction
	 * @return the specified template view.
	 */
	@GetMapping("/transactions/create/branch_office={branchOfficeId}/product_type={productType}&product_id={productId}")
	public String createTransaction(Model model,
			@PathVariable(name = "branchOfficeId") Long branchOfficeId,
			@PathVariable(name = "productType") String productType,
			@PathVariable(name = "productId") String productId) {


		List<Client> clients = clientServiceImpl.getClients().getBody();
		String productName = null;
		Long productPrice = null;
		Long maxQuantity = null;
		Procedure<String> productNameGet = null;
		Procedure<Long> productPriceGet = null;
		Procedure<Long> maxQuantityGet = null;

		// getting the selected product's information
		if(productId.contains("-")) {
			final UUID isbn = UUID.fromString(productId);
			if(productType.equalsIgnoreCase("MAGAZINE")) {
				MagazineLot magazineLot =
					magazineLotServiceImpl.getMagazineLotByIsbn(isbn).getBody();
				productNameGet = magazineLot::getName;
				productPriceGet = magazineLot::getPricePerUnit;
				maxQuantityGet = magazineLot::getAvailableUnits;
			} else {
				BookLot bookLot =
					bookLotServiceImpl.getBookLotByIsbn(isbn).getBody();
				productNameGet = bookLot::getName;
				productPriceGet = bookLot::getPricePerUnit;
				maxQuantityGet = bookLot::getAvailableUnits;
			}
		} else {
			final Long id = Long.valueOf(productId);
			if(productType.equalsIgnoreCase("DISC")) {
				DiscLot discLot = discLotServiceImpl.getDiscLotById(id).getBody();
				productNameGet = discLot::getName;
				productPriceGet = discLot::getPricePerUnit;
				maxQuantityGet = discLot::getAvailableUnits;
			} else {
				VinylRecordLot vinylRecordLot =
					vinylRecordLotServiceImpl.getVinylRecordLotById(id).getBody();
				productNameGet = vinylRecordLot::getRecordProductionName;
				productPriceGet = vinylRecordLot::getPricePerUnit;
				maxQuantityGet = vinylRecordLot::getAvailableUnits;
			}
		}

		productName = productNameGet.apply();
		productPrice = productPriceGet.apply();
		maxQuantity = maxQuantityGet.apply();

		model.addAttribute("action", "post");
		model.addAttribute("branchOfficeId", branchOfficeId);
		model.addAttribute("productName", productName);
		model.addAttribute("productType", productType);
		model.addAttribute("productPrice", productPrice);
		model.addAttribute("maxQuantity", maxQuantity);
		model.addAttribute("clients", clients);

		return "transactionActions";
	}

	/**
	 * Creates a new transaction entity
	 *
	 * @param newTransaction POJO with the information of the transaction
	 * entity that is beeing created.
	 * @param branchOfficeId id of the branch office where the transaction is beeing
	 * registered
	 * @param productId id of the selected product to register the transaction
	 * @param clientId id of the client that is the owner of the transaction
	 * that is beeing created
	 * @return the specified template view.
	 */
	@GetMapping("/transactions/manage/create/{branchOfficeId}/{productId}")
	public String createTransaction(Transaction newTransaction,
			@PathVariable(name = "branchOfficeId") Long branchOfficeId,
			@PathVariable(name = "productId") String productId,
			@RequestParam(name = "clientId") Long clientId) {

		BranchOffice branchOffice =
			branchOfficeServiceImpl.getBranchOfficeById(branchOfficeId).getBody();
		Client client = clientServiceImpl.getClientById(clientId).getBody();

		// increasing the current client's number of purchases
		client.setPurchasesNumber(client.getPurchasesNumber() + 1);
		clientServiceImpl.updateClientById(clientId, client);

		// creation of the new transaction
		newTransaction.setClientName(String.format("%s %s", client.getFirstName(),
					client.getPaternalLastName()));
		newTransaction.setClientIdentificationNumber(client.getIdentificationNumber());
		newTransaction.setClientIdentificationType(client.getIdentificationNumber());
		newTransaction.setTransactionCost(newTransaction.getProductQuantity() *
				newTransaction.getProductPrice());
		newTransaction.setBranchOffice(branchOffice);
		transactionServiceImpl.createTransaction(newTransaction);

		// decrementing the available units of the product purchased
		long finalUnits = 0L;
		final String productType = newTransaction.getProductType().toString();
		final long productQuantity = newTransaction.getProductQuantity();
		if(productId.contains("-")) {
			final UUID isbn = UUID.fromString(productId);
			if(productType.equalsIgnoreCase("Magazine")) {
				MagazineLot magazineLot =
					magazineLotServiceImpl.getMagazineLotByIsbn(isbn).getBody();

				finalUnits = magazineLot.getAvailableUnits() - productQuantity;
				if(finalUnits != 0) {
					magazineLot.setAvailableUnits(finalUnits);
					magazineLotServiceImpl.updateMagazineLotByIsbn(isbn, magazineLot);
				} else
					magazineLotServiceImpl.deleteMagazineLotByIsbn(isbn);
			} else {
				BookLot bookLot =
					bookLotServiceImpl.getBookLotByIsbn(isbn).getBody();

				finalUnits = bookLot.getAvailableUnits() - productQuantity;
				if(finalUnits != 0) {
					bookLot.setAvailableUnits(finalUnits);
					bookLotServiceImpl.updateBookLotByIsbn(isbn, bookLot);
				} else
					bookLotServiceImpl.deleteBookLotByIsbn(isbn);
			}
		} else {
			final Long id = Long.valueOf(productId);
			if(productType.equalsIgnoreCase("Disc")) {
				DiscLot discLot = discLotServiceImpl.getDiscLotById(id).getBody();

				finalUnits = discLot.getAvailableUnits() - productQuantity;
				if(finalUnits != 0) {
					discLot.setAvailableUnits(finalUnits);
					discLotServiceImpl.updateDiscLotById(id, discLot);
				} else
					discLotServiceImpl.deleteDiscLotById(id);
			} else {
				VinylRecordLot vinylRecordLot =
					vinylRecordLotServiceImpl.getVinylRecordLotById(id).getBody();

				finalUnits = vinylRecordLot.getAvailableUnits() - productQuantity;
				if(finalUnits != 0) {
					vinylRecordLot.setAvailableUnits(finalUnits);
					vinylRecordLotServiceImpl.updateVinylRecordLotById(id, vinylRecordLot);
				} else
					vinylRecordLotServiceImpl.deleteVinylRecordLotById(id);
			}
		}

		// creating a new branch_office_clients entry
		// branchOffice.getClients().add(client);
		// branchOfficeServiceImpl.updateBranchOfficeById(branchOfficeId, branchOffice);

		return String.format("redirect:/transactions/%d", newTransaction.getId());
	} // TODO: Improve this implementation

	/**
	 * Retrieves a transaction entity specified by id
	 *
	 * @param id id of the transaction entity that is beeing searched
	 * @param model holder model for context model's attributes.
	 * @return the specified template view
	 */
	@GetMapping("/transactions/{id}")
	public String showTransaction(@PathVariable(name = "id") Long id,
			Model model) {
		Transaction transaction =
			transactionServiceImpl.getTransactionById(id).getBody();

		model.addAttribute("action", "get");
		model.addAttribute("transaction", transaction);

		return "transactionActions";
	}

	/**
	 * Retrieves all transaction entities related with a branch office
	 *
	 * @param model holder model for context model's attributes.
	 * @param branchOfficeId id of the branch office that owns the transactions
	 * to retrieve
	 * @return the specified template view
	 */
	@GetMapping("/transactions/branch_office={branchOfficeId}")
	public String showTransactions(Model model,
			@PathVariable(name = "branchOfficeId") Long branchOfficeId) {
		BranchOffice  branchOffice =
			branchOfficeServiceImpl.getBranchOfficeById(branchOfficeId).getBody();

		model.addAttribute("action", "get");
		model.addAttribute("branchOffice", branchOffice);

		return "transactions";
	}

	/**
	 * Deletes a transaction entity specified by id
	 *
	 * @param id id of the transaction entity that is beeing deleted
	 * @return the specified template view
	 */
	@PostMapping("/transactions/manage/delete/{id}")
	public String deleteTransaction(@PathVariable(name = "id") Long id) {
		Long branchOfficeId = transactionServiceImpl.getTransactionById(id)
			.getBody().getBranchOffice().getId();
		transactionServiceImpl.deleteTransactionById(id);

		return String.format("redirect:/transactions/branch_office=%d",
				branchOfficeId);
	}

	@FunctionalInterface private interface Procedure<T> { T apply(); };
}

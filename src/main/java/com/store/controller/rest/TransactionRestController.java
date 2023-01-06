package com.store.controller.rest;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.store.model.BranchOffice;
import com.store.model.Client;
import com.store.model.Transaction;
import com.store.service.BranchOfficeService;
import com.store.service.ClientService;
import com.store.service.TransactionService;

import lombok.AllArgsConstructor;

/**
 * @author Bryan Baron
 */
@RestController
@RequestMapping(path = "/api")
@AllArgsConstructor
public class TransactionRestController {

	private TransactionService transactionService;
	private BranchOfficeService branchOfficeService;
	private ClientService clientService;

	/**
	 * Creates a new Transaction entity.
	 *
	 * @param transaction request body with the information of the Transaction
	 * entity being created.
	 * @param branchOfficeId id of the BranchOffice entity related to the
	 * Transaction entity being created.
	 * @param clientId id of the Client entity related to the Transaction
	 * entity being created.
	 * @return the response of the POST request.
	 */
	@PostMapping("/v1/transactions")
	public ResponseEntity<Transaction> createTransaction(
			@RequestBody Transaction transaction,
			@RequestParam(name = "branchOfficeId") Long branchOfficeId,
			@RequestParam(name = "clientId") Long clientId) {
		final Client client = clientService.getClientById(clientId).getBody();
		final BranchOffice branchOffice =
			branchOfficeService.getBranchOfficeById(branchOfficeId).getBody();
		ResponseEntity<Transaction> createdTransaction = null;

		transaction.setBranchOffice(branchOffice);
		client.setPurchasesNumber(client.getPurchasesNumber() + 1);
		clientService.updateClientById(clientId, client);
		createdTransaction = transactionService.createTransaction(transaction);
		return createdTransaction;
	}

	/**
	 * Retrieves a Transaction entity specified by id.
	 *
	 * @param id id of the Transaction entity being searched.
	 * @return the response of the GET request.
	 */
	@GetMapping("/v1/transactions/{id}")
	public ResponseEntity<Transaction> getTransaction(
			@PathVariable(name = "id") Long id) {
		final ResponseEntity<Transaction> transaction =
			transactionService.getTransactionById(id);
		return transaction;
	}

	/**
	 * Retrieves all Transaction entities related to a BranchOffice entity.
	 *
	 * @param branchOfficeId id of the BranchOffice entity related to the
	 * Transaction entities to retrieve.
	 * @return the response of the GET request.
	 */
	@GetMapping("/v1/transactions")
	public ResponseEntity<List<Transaction>> getTransactions(
			@RequestParam(name = "branchOfficeId", required = false) Long branchOfficeId) {
		final ResponseEntity<List<Transaction>>  transactions = branchOfficeId != null
			? transactionService.getTransactionsByBranchOfficeId(branchOfficeId)
			: transactionService.getTransactions();
		return transactions;
	}

	/**
	 * Updates a Transaction entity specified by id.
	 *
	 * @param id id of the Transaction entity being updated.
	 * @param transaction request body with the information of the Transaction
	 * entity being updated.
	 * @return the response of the PUT request.
	 */
	@PutMapping("/v1/transactions/{id}")
	public ResponseEntity<Transaction> updateTransaction(
			@PathVariable(name = "id") Long id,
			@RequestBody Transaction transaction) {
		final ResponseEntity<Transaction> updatedTransaction =
			transactionService.updateTransactionById(id, transaction);
		return updatedTransaction;
	}

	/**
	 * Deletes a Transaction entity specified by id.
	 *
	 * @param id id of the Transaction entity being deleted.
	 * @return the response of the DELETE request.
	 */
	@DeleteMapping("/v1/transactions/{id}")
	public ResponseEntity<?> deleteTransaction(@PathVariable(name = "id") Long id) {
		ResponseEntity<?> deletedTransaction =
			transactionService.deleteTransactionById(id);
		return deletedTransaction;
	}
}

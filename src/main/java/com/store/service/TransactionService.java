package com.store.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.store.exception.TransactionNotFoundException;
import com.store.model.Transaction;

/**
 * @author Bryan Baron
 */
public interface TransactionService {

	/**
	 * Creates a new Transaction
	 *
	 * @param transaction the creating Transaction instance
	 * @return the result of the CRUD's create operation over Transaction
	 */
	ResponseEntity<Transaction> createTransaction(Transaction transaction);

	/**
	 * Retrives an id-specified Transaction entity
	 *
	 * @param id the id of the Transaction entity to retrive
	 * @return the result of the CRUD's retrive operation over Transaction
	 */
	ResponseEntity<Transaction> getTransactionById(Long id)
			throws TransactionNotFoundException;

	/**
	 * Retrives all the Transaction entities
	 *
	 * @return the result of the CRUD's retrive operation over Transaction
	 */
	ResponseEntity<List<Transaction>> getTransactions();

	/**
	 * Retrives all the Transaction entities related to a BranchOffice entity
	 *
	 * @return the result of the CRUD's retrive operation over Transaction
	 */
	ResponseEntity<List<Transaction>> getTransactionsByBranchOfficeId(Long branchOfficeId);

	/**
	 * Updates an id-specified Transaction entity
	 *
	 * @param id the id of the Transaction entity to update
	 * @param updatedTransaction the Transaction instance with the updating information
	 * @return the result of the CRUD's update operation over Transaction
	 */
	ResponseEntity<Transaction> updateTransactionById(Long id,
			Transaction updatedTransaction)
			throws TransactionNotFoundException;

	/**
	 * Deletes an id-specified Transaction entity
	 *
	 * @param id the id of the Transaction entity to delete
	 * @return the result of the CRUD's delete operation over Transaction
	 */
	ResponseEntity<?> deleteTransactionById(Long id)
			throws TransactionNotFoundException;
}

package co.edu.unbosque.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import co.edu.unbosque.entity.Transaction;
import co.edu.unbosque.exception.TransactionNotFoundException;

/**
 * @author Bryan Baron
 */
public interface TransactionService {

	/**
	 * Creates a new Transaction
	 *
	 * @param transaction the creating Transaction instance
	 * @return the result of the CRUD's create operation over Transaction
	 * @see co.edu.unbosque.repository.TransactionRepository#save(Transaction)
	 */
	ResponseEntity<Transaction> createTransaction(Transaction transaction);

	/**
	 * Retrives an id-specified Transaction entity
	 *
	 * @param id the id of the Transaction entity to retrive
	 * @return the result of the CRUD's retrive operation over Transaction
	 * @see co.edu.unbosque.repository.TransactionRepository#findById(Long)
	 */
	ResponseEntity<Transaction> getTransactionById(Long id)
			throws TransactionNotFoundException;

	/**
	 * Retrives all the Transaction entities
	 *
	 * @return the result of the CRUD's retrive operation over Transaction
	 * @see co.edu.unbosque.repository.TransactionRepository#findAll()
	 */
	ResponseEntity<List<Transaction>> getTransactions();

	/**
	 * Updates an id-specified Transaction entity
	 *
	 * @param id the id of the Transaction entity to update
	 * @param updatedTransaction the Transaction instance with the updating information
	 * @return the result of the CRUD's update operation over Transaction
	 * @see co.edu.unbosque.repository.TransactionRepository#save(Transaction)
	 */
	ResponseEntity<Transaction> updateTransactionById(Long id,
			Transaction updatedTransaction)
			throws TransactionNotFoundException;

	/**
	 * Deletes an id-specified Transaction entity
	 *
	 * @param id the id of the Transaction entity to delete
	 * @return the result of the CRUD's delete operation over Transaction
	 * @see co.edu.unbosque.repository.TransactionRepository#deleteById(Long)
	 */
	ResponseEntity<?> deleteTransactionById(Long id)
			throws TransactionNotFoundException;
}

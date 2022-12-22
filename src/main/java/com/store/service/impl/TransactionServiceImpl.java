package com.store.service.impl;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.store.exception.TransactionNotFoundException;
import com.store.model.Transaction;
import com.store.repository.TransactionRepository;
import com.store.service.TransactionService;

import lombok.AllArgsConstructor;

/**
 * @author Bryan Baron
 */
@Service
@AllArgsConstructor
public class TransactionServiceImpl implements TransactionService {

	private TransactionRepository transactionRepository;

	/**
	 * Creates a new Transaction
	 *
	 * @param transaction the creating Transaction instance
	 * @return the result of the CRUD's create operation over Transaction
	 */
	@Override
	public ResponseEntity<Transaction> createTransaction(Transaction transaction) {
		final Transaction savedTransaction =
			transactionRepository.save(transaction);
		return ResponseEntity.ok(savedTransaction);
	}

	/**
	 * Retrives an id-specified Transaction entity
	 *
	 * @param id the id of the Transaction entity to retrive
	 * @return the result of the CRUD's retrive operation over Transaction
	 */
	@Override
	public ResponseEntity<Transaction> getTransactionById(Long id)
		throws TransactionNotFoundException {
		final Transaction transaction = transactionRepository.findById(id)
			.orElseThrow(() -> new TransactionNotFoundException(
							String.format("transaction with id %d not found",
								id)
							));
		return ResponseEntity.ok(transaction);
	}

	/**
	 * Retrives all the Transaction entities
	 *
	 * @return the result of the CRUD's retrive operation over Transaction
	 */
	@Override
	public ResponseEntity<List<Transaction>> getTransactions() {
		final List<Transaction> transactions = transactionRepository.findAll();
		return ResponseEntity.ok(transactions);
	}

	/**
	 * Updates an id-specified Transaction entity
	 *
	 * @param id the id of the Transaction entity to update
	 * @param updatedTransaction the Transaction instance with the updating information
	 * @return the result of the CRUD's update operation over Transaction
	 */
	@Override
	public ResponseEntity<Transaction> updateTransactionById(Long id,
			Transaction updatedTransaction)
			throws TransactionNotFoundException {
		Transaction transaction = transactionRepository.findById(id)
			.orElseThrow(() -> new TransactionNotFoundException(
							String.format("transaction with id %d not found",
								id)
							));

		transaction.setClientIdentificationNumber(
				updatedTransaction.getClientIdentificationNumber());
		transaction.setClientIdentificationType(
				updatedTransaction.getClientIdentificationType());
		transaction.setProductName(updatedTransaction.getProductName());
		transaction.setProductType(updatedTransaction.getProductType());
		transaction.setProductPrice(updatedTransaction.getProductPrice());

		transaction = transactionRepository.save(transaction);
		return ResponseEntity.ok(transaction);
	}

	/**
	 * Deletes an id-specified Transaction entity
	 *
	 * @param id the id of the Transaction entity to delete
	 * @return the result of the CRUD's delete operation over Transaction
	 */
	@Override
	public ResponseEntity<?> deleteTransactionById(Long id)
		throws TransactionNotFoundException {
		Transaction transaction = transactionRepository.findById(id)
			.orElseThrow(() -> new TransactionNotFoundException(
							String.format("transaction with id %d not found",
								id)
							));
		transactionRepository.delete(transaction);

		return ResponseEntity.noContent().build();
	}
}

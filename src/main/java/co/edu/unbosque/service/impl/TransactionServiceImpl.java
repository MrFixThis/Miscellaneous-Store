package co.edu.unbosque.service.impl;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import co.edu.unbosque.entity.Transaction;
import co.edu.unbosque.exception.TransactionNotFoundException;
import co.edu.unbosque.repository.TransactionRepository;
import co.edu.unbosque.service.TransactionService;
import lombok.AllArgsConstructor;

/**
 * @author Bryan Baron
 */
@Service
@AllArgsConstructor
public class TransactionServiceImpl implements TransactionService {

	//TODO: impement the external (client) behavior
	private TransactionRepository transactionRepository;

	/**
	 * Creates a new Transaction
	 *
	 * @param transaction the creating Transaction instance
	 * @return the result of the CRUD's create operation over Transaction
	 * @see co.edu.unbosque.repository.TransactionRepository#save(Transaction)
	 */
	@Override
	public ResponseEntity<Transaction> createTransaction(Transaction transaction) {
		Transaction savedTrasaction = transactionRepository.save(transaction);
		return ResponseEntity.ok(savedTrasaction);
	}

	/**
	 * Retrives an id-specified Transaction entity
	 *
	 * @param id the id of the Transaction entity to retrive
	 * @return the result of the CRUD's retrive operation over Transaction
	 * @see co.edu.unbosque.repository.TransactionRepository#findById(Long)
	 */
	@Override
	public ResponseEntity<Transaction> getTransactionById(Long id)
		throws TransactionNotFoundException {
		Transaction transaction = transactionRepository.findById(id)
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
	 * @see co.edu.unbosque.repository.TransactionRepository#findAll()
	 */
	@Override
	public ResponseEntity<List<Transaction>> getTransactions() {
		List<Transaction> transactions = transactionRepository.findAll();
		return ResponseEntity.ok(transactions);
	}

	/**
	 * Updates an id-specified Transaction entity
	 *
	 * @param id the id of the Transaction entity to update
	 * @param updatedTransaction the Transaction instance with the updating information
	 * @return the result of the CRUD's update operation over Transaction
	 * @see co.edu.unbosque.repository.TransactionRepository#save(Transaction)
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
	 * @see co.edu.unbosque.repository.TransactionRepository#deleteById(Long)
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

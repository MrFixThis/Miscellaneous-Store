package com.store.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.store.model.Transaction;

/**
 * @author Bryan Baron
 */
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

	/**
	 * Retrieves all the Transaction entities related to a BranchOffice entity.
	 *
	 * @param branchOfficeId must not be {@literal null}.
	 * @return a list with all the existent Transaction entities related to a
	 * BranchOffice's entity.
	 * or {@literal Optional#empty()} if none is found.
	 * @throws IllegalArgumentException if {@literal branchOfficeId} is {@literal null}.
	 */
	Optional<List<Transaction>> findTransactionsByBranchOfficeId(Long branchOfficeId);
}

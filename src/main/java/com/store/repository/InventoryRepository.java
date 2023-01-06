package com.store.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.store.model.Inventory;

/**
 * @author Bryan Baron
 */
public interface InventoryRepository extends JpaRepository<Inventory, Long> {

	/**
	 * Retrieves the Inventory entity related to a given BranchOffice entity.
	 *
	 * @param branchOfficeId must not be {@literal null}.
	 * @return the BranchOffice's related Inventory entity or
	 * {@literal Optional#empty()} if none is found.
	 * @throws IllegalArgumentException if {@literal clientId} is {@literal null}.
	 */
	Optional<Inventory> findInventoryByBranchOfficeId(Long branchOfficeId);
}

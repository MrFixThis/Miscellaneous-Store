package com.store.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.store.model.BranchOffice;

/**
 * @author Bryan Baron
 */
public interface BranchOfficeRepository extends JpaRepository<BranchOffice, Long> {

	/**
	 * Retrieves all the BranchOffice entities by a Client's entity id.
	 *
	 * @param clientId must not be {@literal null}.
	 * @return a list with the BranchOffice's related entities to a Client's entity
	 * or {@literal Optional#empty()} if none is found.
	 * @throws IllegalArgumentException if {@literal clientId} is {@literal null}.
	 */
	Optional<List<BranchOffice>> findBranchOfficesByClientsId(Long clientId);
}

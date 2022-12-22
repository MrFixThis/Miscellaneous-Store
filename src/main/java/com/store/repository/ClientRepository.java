package com.store.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.store.model.Client;

/**
 * @author Bryan Baron
 */
public interface ClientRepository extends JpaRepository<Client, Long> {

	/**
	 * Retrieves all the Client entities by a BranchOffice's entity id.
	 *
	 * @param branchOfficeId must not be {@literal null}.
	 * @return a list with the Client's related entities to a BranchOffice's entity
	 * or {@literal Optional#empty()} if none is found.
	 * @throws IllegalArgumentException if {@literal branchOfficeId} is {@literal null}.
	 */
	Optional<List<Client>> findClientsByBranchOfficesId(Long branchOfficeId);
}

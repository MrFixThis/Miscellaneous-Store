package com.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.store.model.Worker;

/**
 * @author Bryan Baron
 */
public interface WorkerRepository extends JpaRepository<Worker, Long> {
	
}

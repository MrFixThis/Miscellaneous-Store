package com.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.store.model.Transaction;

/**
 * @author Bryan Baron
 */
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

}

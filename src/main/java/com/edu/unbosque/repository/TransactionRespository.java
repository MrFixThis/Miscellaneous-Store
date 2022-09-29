package com.edu.unbosque.repository;

import com.edu.unbosque.entity.Transaction;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Bryan Baron
 */
public interface TransactionRespository extends JpaRepository<Transaction, Long> {

}

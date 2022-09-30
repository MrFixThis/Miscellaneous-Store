package co.edu.unbosque.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.unbosque.entity.Transaction;

/**
 * @author Bryan Baron
 */
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

}

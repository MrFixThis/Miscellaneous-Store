package co.edu.unbosque.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.unbosque.entity.Client;

/**
 * @author Bryan Baron
 */
public interface ClientRepository extends JpaRepository<Client, Long> {

}

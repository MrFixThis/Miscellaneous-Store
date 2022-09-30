package co.edu.unbosque.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.unbosque.entity.Administrator;

/**
 * @author Bryan Baron
 */
public interface AdministratorRepository extends JpaRepository<Administrator, Long> {

}

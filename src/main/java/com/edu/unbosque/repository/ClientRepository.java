package com.edu.unbosque.repository;

import com.edu.unbosque.entity.Client;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Bryan Baron
 */
public interface ClientRepository extends JpaRepository<Client, Long> {

}

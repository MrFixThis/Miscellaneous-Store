package com.edu.unbosque.repository;

import com.edu.unbosque.entity.Administrator;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Bryan Baron
 */
public interface AdministratorRepository extends JpaRepository<Administrator, Long> {

}

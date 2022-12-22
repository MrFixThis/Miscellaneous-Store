package com.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.store.model.Administrator;

/**
 * @author Bryan Baron
 */
public interface AdministratorRepository extends JpaRepository<Administrator, Long> {

}

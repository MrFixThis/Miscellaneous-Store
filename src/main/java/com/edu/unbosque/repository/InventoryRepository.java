package com.edu.unbosque.repository;

import com.edu.unbosque.entity.Inventory;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Bryan Baron
 */
public interface InventoryRepository extends JpaRepository<Inventory, Long> {

}

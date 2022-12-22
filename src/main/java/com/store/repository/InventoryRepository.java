package com.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.store.model.Inventory;

/**
 * @author Bryan Baron
 */
public interface InventoryRepository extends JpaRepository<Inventory, Long> {

}

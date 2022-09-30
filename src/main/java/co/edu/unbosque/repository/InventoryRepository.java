package co.edu.unbosque.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.unbosque.entity.Inventory;

/**
 * @author Bryan Baron
 */
public interface InventoryRepository extends JpaRepository<Inventory, Long> {

}

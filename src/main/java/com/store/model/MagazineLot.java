package com.store.model;

import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PreRemove;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.store.util.TransactionManager;

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author Bryan Baron
 */
@Entity
@Table(name = "magazine_lot")
@Data
@NoArgsConstructor @AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class MagazineLot implements TransactionManager {
	@Id
	@Column(name = "isbn")
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(
		name = "UUID",
		strategy = "org.hibernate.id.UUIDGenerator"
	)
	private UUID isbn;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "publisher_name", nullable = false)
	private String publisherName;

	@Column(name = "price_per_unit", nullable = false)
	private Long pricePerUnit;

	@Column(name = "available_units", nullable = false)
	private Long availableUnits;

	@JsonIgnore
	@ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH})
	@JoinColumn(name = "inventory_id", nullable = false)
	private Inventory magazineLotInventory;

	/**
	 * Truncates a possible post-delete re-persistence of the current entity.
	 * @see com.store.util.TransactionManager#preRemove()
	 */
	@PreRemove
	public void preRemove() {
		this.magazineLotInventory.getInventoryMagazineLots().remove(this);
		this.magazineLotInventory = null;
	}
}

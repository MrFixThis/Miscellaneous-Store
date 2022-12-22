package com.store.model;

import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PreRemove;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.store.util.TransactionManager;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author Bryan Baron
 */
@Entity
@Table(name = "disc_lot")
@Data
@NoArgsConstructor @AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class DiscLot implements TransactionManager {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "publication_date", nullable = false)
	private Date publicationDate;

	@Column(name = "disc_format", nullable = false)
	@Enumerated(EnumType.STRING)
	private DiscFormat discFormat;

	@Column(name = "execution_time_in_minutes", nullable = false)
	private Integer executionTimeInMinutes;

	@Column(name = "content_language", length = 120, nullable = false)
	private String contentLanguage;

	@Column(name = "content_classification", length = 120, nullable = false)
	private String contentClassification;

	@Column(name = "price_per_unit", nullable = false)
	private Long pricePerUnit;

	@Column(name = "available_units", nullable = false)
	private Long availableUnits;

	@JsonIgnore
	@ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH})
	@JoinColumn(name = "inventory_id")
	private Inventory discLotInventory;

	/**
	 * Enum for DiscLot's entities format
	 */
	public static enum DiscFormat {
		DVD,
		BLUE_RAY
	}

	/**
	 * Truncates a possible post-delete re-persistence of the current entity.
	 * @see com.store.util.TransactionManager#preRemove()
	 */
	@PreRemove
	public void preRemove() {
		this.discLotInventory.getInventoryDiscLots().remove(this);
		this.discLotInventory = null;
	}
}

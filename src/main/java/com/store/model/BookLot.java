package com.store.model;

import java.sql.Date;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author Bryan Baron
 */
@Entity
@Table(name = "book_lot")
@Data
@NoArgsConstructor @AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Builder
public class BookLot {
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

	@Column(name = "author_name", nullable = false)
	private String authorName;

	@Column(name = "publisher_name", nullable = false)
	private String publisherName;

	@Column(name = "pages_number", nullable = false)
	private String pagesNumber;

	@Column(name = "publication_date", nullable = false)
	private Date publicationDate;

	@Column(name = "price_per_unit", nullable = false)
	private Long pricePerUnit;

	@Column(name = "available_units", nullable = false)
	private Long availableUnits;

	@JsonIgnore
	@ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH},
		fetch = FetchType.LAZY)
	@JoinColumn(name = "inventory_id")
	private Inventory bookLotInventory;

	/**
	 * Truncates a possible post-delete re-persistence of the current entity.
	 * @see com.store.util.TransactionManager#preRemove()
	 */
	// @PreRemove
	// public void preRemove() {
	// 	this.bookLotInventory.getInventoryBookLots().remove(this);
	// 	this.bookLotInventory = null;
	// }
}

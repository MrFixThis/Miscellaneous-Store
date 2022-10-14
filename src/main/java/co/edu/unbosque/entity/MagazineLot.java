package co.edu.unbosque.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
public class MagazineLot {
	@Id
	@Column(name = "ibsn", length = 13)
	private String isbn;

	@Column(name = "name", unique = true, nullable = false)
	private String name;

	@Column(name = "publisher_name", nullable = false)
	private String publisherName;

	@Column(name = "price_per_unit", nullable = false)
	private Long pricePerUnit;

	@Column(name = "available_units", nullable = false)
	private Integer availableUnits;

	@JsonIgnore
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "inventory_id", nullable = false, insertable = false,
		updatable = false)
	private Inventory magazineInventory;
}

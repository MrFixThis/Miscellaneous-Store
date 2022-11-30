package co.edu.unbosque.entity;

import java.sql.Date;
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

import org.hibernate.annotations.GenericGenerator;

import co.edu.unbosque.util.TransactionManager;
import lombok.AllArgsConstructor;
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
public class BookLot implements TransactionManager {
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
	@ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH})
	@JoinColumn(name = "inventory_id")
	private Inventory bookLotInventory;

	/**
	 * Truncates a possible post-delete re-persistence of the current entity.
	 * @see co.edu.unbosque.util.TransactionManager#preRemove()
	 */
	@PreRemove
	public void preRemove() {
		this.bookLotInventory.getInventoryBookLots().remove(this);
		this.bookLotInventory = null;
	}
}

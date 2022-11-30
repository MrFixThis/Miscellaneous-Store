package co.edu.unbosque.entity;

import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PreRemove;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import co.edu.unbosque.util.TransactionManager;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author Bryan Baron
 */
@Entity
@Table(name = "vinyl_record_lot")
@Data
@NoArgsConstructor @AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class VinylRecordLot implements TransactionManager {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "record_production_name", nullable = false)
	private String recordProductionName;
	
	@Column(name = "artist_group_name", length = 180, nullable = false)
	private String artistGroupName;

	@Column(name = "publication_date", nullable = false)
	private Date publicationDate;

	@Column(name = "musical_genre", length = 180, nullable = false)
	private String musicalGenre;

	@Column(name = "price_per_unit", nullable = false)
	private Long pricePerUnit;

	@Column(name = "available_units", nullable = false)
	private Long availableUnits;

	@JsonIgnore
	@ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH})
	@JoinColumn(name = "inventory_id")
	private Inventory vinylRecordLotInventory;

	/**
	 * Truncates a possible post-delete re-persistence of the current entity.
	 * @see co.edu.unbosque.util.TransactionManager#preRemove()
	 */
	@PreRemove
	public void preRemove() {
		this.vinylRecordLotInventory.getInventoryVinylRecordLots().remove(this);
		this.vinylRecordLotInventory = null;
	}
}

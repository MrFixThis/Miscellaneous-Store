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
@Table(name = "vinyl_record_lot")
@Data
@NoArgsConstructor @AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class VinylRecordLot {
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

	@Column(name = "misical_genre", length = 180, nullable = false)
	private String musicalGenre;

	@Column(name = "price_per_unit", nullable = false)
	private Long pricePerUnit;

	@Column(name = "available_units", nullable = false)
	private Integer availableUnits;

	@JsonIgnore
	@ManyToOne(cascade = {CascadeType.REFRESH, CascadeType.MERGE})
	@JoinColumn(name = "inventory_id")
	private Inventory vinylRecordInventory;
}

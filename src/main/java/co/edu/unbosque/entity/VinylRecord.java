package co.edu.unbosque.entity;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
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
@Table(name = "vinyl_record")
@Data
@NoArgsConstructor @AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class VinylRecord {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "record_production_name", unique = true, nullable = false)
	private String recordProductionName;
	
	@Column(name = "artist_group_name", length = 180, nullable = false)
	private String artistGroupName;

	@Column(name = "publication_date", nullable = false)
	private Date publicationDate;

	@Column(name = "misical_genre", length = 180, nullable = false)
	private String musicalGenre;

	@Column(name = "price", nullable = false)
	private Long price;

	@Column(name = "available_units", nullable = false)
	private Integer availableUnits;

	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "inventory_id", nullable = false,
		insertable = false, updatable = false)
	private Set<Inventory> vinylRecordInventories;

	{
		vinylRecordInventories = new HashSet<>();
	}
}

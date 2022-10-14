package co.edu.unbosque.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "inventory")
@Data
@NoArgsConstructor @AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Inventory {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "description", nullable = false)
	private String description;

	@JsonIgnore
	@OneToMany(cascade = {CascadeType.REFRESH, CascadeType.REMOVE},
		fetch = FetchType.EAGER, mappedBy = "bookInventory")
	private Set<BookLot> inventoryBookLots;

	@JsonIgnore
	@OneToMany(cascade = {CascadeType.REFRESH, CascadeType.REMOVE},
		fetch = FetchType.EAGER, mappedBy = "magazineInventory")
	private Set<MagazineLot> inventoryMagazineLots;

	@JsonIgnore
	@OneToMany(cascade = {CascadeType.REFRESH, CascadeType.REMOVE},
		fetch = FetchType.EAGER, mappedBy = "discInventory")
	private Set<DiscLot> inventoryDiscLots;

	@JsonIgnore
	@OneToMany(cascade = {CascadeType.REFRESH, CascadeType.REMOVE},
		fetch = FetchType.EAGER, mappedBy = "vinylRecordInventory")
	private Set<VinylRecordLot> inventoryVinylRecordLots;
}

package co.edu.unbosque.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

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
	@EqualsAndHashCode.Exclude @ToString.Exclude
	@OneToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH},
		mappedBy = "inventory", fetch = FetchType.LAZY)
	private BranchOffice branchOffice;

	@JsonIgnore
	@EqualsAndHashCode.Exclude @ToString.Exclude
	@OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REFRESH,
		CascadeType.REMOVE}, orphanRemoval = true, fetch = FetchType.EAGER,
		mappedBy = "magazineLotInventory")
	private Set<MagazineLot> inventoryMagazineLots;

	@JsonIgnore
	@EqualsAndHashCode.Exclude @ToString.Exclude
	@OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REFRESH,
		CascadeType.REMOVE}, orphanRemoval = true, fetch = FetchType.EAGER,
		mappedBy = "bookLotInventory")
	private Set<BookLot> inventoryBookLots;

	@JsonIgnore
	@EqualsAndHashCode.Exclude @ToString.Exclude
	@OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REFRESH,
		CascadeType.REMOVE}, orphanRemoval = true, fetch = FetchType.EAGER,
		mappedBy = "discLotInventory")
	private Set<DiscLot> inventoryDiscLots;

	@JsonIgnore
	@EqualsAndHashCode.Exclude @ToString.Exclude
	@OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REFRESH,
		CascadeType.REMOVE}, orphanRemoval = true, fetch = FetchType.EAGER,
		mappedBy = "vinylRecordLotInventory")
	private Set<VinylRecordLot> inventoryVinylRecordLots;

	{
		inventoryMagazineLots = new HashSet<>();
		inventoryBookLots = new HashSet<>();
		inventoryDiscLots = new HashSet<>();
		inventoryVinylRecordLots = new HashSet<>();
	}
}

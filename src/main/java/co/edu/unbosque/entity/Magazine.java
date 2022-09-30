package co.edu.unbosque.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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
 * @athor Bryan Baron
 */
@Entity
@Table(name = "book")
@Data
@NoArgsConstructor @AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Magazine {
	@Id
	@Column(name = "ibsn", length = 13)
	private String isbn;

	@Column(name = "name", unique = true, nullable = false)
	private String name;

	@Column(name = "publisher_name", nullable = false)
	private String publisherName;

	@Column(name = "price", nullable = false)
	private Long price;

	@Column(name = "available_units", nullable = false)
	private Integer availableUnits;

	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "inventory_id", referencedColumnName = "id",
		nullable = false
	)
	private Set<Inventory> inventories;

	{
		inventories = new HashSet<>();
	}
}

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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
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
@Table(name = "branch_office")
@Data
@NoArgsConstructor @AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class BranchOffice {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "administrator_id")
	private Administrator administrator;

	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "inventory_id")
	private Inventory inventory;

	@JsonIgnore
	@ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST},
		fetch = FetchType.EAGER)
	@JoinTable(
		name = "branch_office_clients",
		joinColumns = @JoinColumn(
					name = "branch_office_id", nullable = false
				),
		inverseJoinColumns = @JoinColumn(
					name = "client_id", nullable = false
				)
	)
	private Set<Client> clients;

	{
		clients = new HashSet<>();
	}
}

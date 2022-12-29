package com.store.model;

import java.util.HashSet;
import java.util.LinkedHashSet;
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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

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
@Table(name = "branch_office")
@Data
@NoArgsConstructor @AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Builder
public class BranchOffice {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "administrator_id")
	private Administrator administrator;

	@OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH,
		CascadeType.REMOVE})
	@JoinColumn(name = "inventory_id")
	private Inventory inventory;

	@JsonIgnore
	@ManyToMany(cascade = {CascadeType.MERGE, CascadeType.REFRESH})
	@JoinTable(
		name = "branch_office_clients",
		joinColumns = @JoinColumn(name = "branch_office_id", nullable = false),
		inverseJoinColumns = @JoinColumn(name = "client_id", nullable = false)
	)
	private Set<Client> clients;

	@JsonIgnore
	@OneToMany(cascade = {CascadeType.REFRESH, CascadeType.REMOVE},
		mappedBy = "branchOffice")
	private Set<Worker> workers;

	@JsonIgnore
	@OneToMany(cascade = {CascadeType.REFRESH, CascadeType.REMOVE},
		fetch = FetchType.EAGER, mappedBy = "branchOffice")
	private Set<Transaction> transactions;

	{
		clients = new HashSet<>();
		workers = new HashSet<>();
		transactions = new LinkedHashSet<>();
	}
}

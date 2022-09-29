package com.edu.unbosque.entity;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
@Table(name = "disc")
@Data
@NoArgsConstructor @AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Disc {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name", unique = true, nullable = false)
	private String name;

	@Column(name = "publication_date", nullable = false)
	private Date publicationDate;

	@Column(name = "disc_format", nullable = false)
	@Enumerated(EnumType.STRING)
	private DiscFormat discFormat;

	@Column(name = "execution_time_in_minutes", nullable = false)
	private Integer executionTimeInMinutes;

	@Column(name = "content_language", length = 120, nullable = false)
	private String contentLanguage;

	@Column(name = "content_classification", length = 120, nullable = false)
	private String contentClassification;

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

	public enum DiscFormat {
		DVD,
		BLUE_RAY
	}
}

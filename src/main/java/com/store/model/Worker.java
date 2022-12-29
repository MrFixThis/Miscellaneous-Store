package com.store.model;

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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author Bryan Baron
 */
@Entity
@Table(name = "worker")
@Data
@NoArgsConstructor @AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Builder
public class Worker {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "first_name", length = 80, nullable = false)
	private String firstName;

	@Column(name = "middle_name", length = 80)
	private String middleName;

	@Column(name = "paternal_last_name", length = 80, nullable = false)
	private String paternalLastName;

	@Column(name = "maternal_last_name", length = 80)
	private String maternalLastName;

	@Column(name = "identification_number", length = 25, nullable = false)
	private String identificationNumber;

	@Column(name = "identification_type", length = 80, nullable = false)
	private String identificationType;

	@Column(name = "date_of_birth", nullable = false)
	private Date dateOfBirth;

	@Column(name = "date_of_hire", nullable = false)
	private Date dateOfHire;

	@Column(name = "phone_number", length = 15, nullable = false)
	private String phoneNumber;

	@Column(name = "email_address", nullable = false)
	private String emailAddress;

	@Column(name = "residence_address", nullable = false)
	private String residenceAddress;

	@Column(name = "role", length = 80, nullable = false)
	private String role;

	@Column(name = "basic_salary", nullable = false)
	private Long basicSalary;

	@JsonIgnoreProperties({"administrator", "inventory"})
	@ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH})
	@JoinColumn(name = "branch_office_id")
	private BranchOffice branchOffice;
}

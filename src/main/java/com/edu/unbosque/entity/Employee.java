package com.edu.unbosque.entity;

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
 * @athor Bryan Baron
 */
@Entity
@Table(name = "employee")
@Data
@NoArgsConstructor @AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Employee {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "first_name", length = 80, nullable = false)
	private String firstName;

	@Column(name = "middle_name", length = 80, nullable = true)
	private String middleName;

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

	@Column(name = "role", length = 80, nullable = false)
	private String role;

	@Column(name = "basic_salary", nullable = false)
	private Long basicSalary;

	@JsonIgnore
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "branch_office_id", referencedColumnName = "id",
		nullable = false
	)
	private BranchOffice branchOffice;
}

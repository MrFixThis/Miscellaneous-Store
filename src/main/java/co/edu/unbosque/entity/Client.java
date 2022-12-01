package co.edu.unbosque.entity;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
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
@Table(name = "client")
@Data
@NoArgsConstructor @AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Client {
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

	@Column(name = "phone_number", length = 15, nullable = false)
	private String phoneNumber;

	@Column(name = "email_address", nullable = false)
	private String emailAddress;

	@Column(name = "purchases_number", nullable = false)
	private Integer purchasesNumber;

	@JsonIgnore
	@EqualsAndHashCode.Exclude @ToString.Exclude
	@ManyToMany(fetch = FetchType.EAGER, mappedBy = "clients")
	private Set<BranchOffice> branchOffices;

	{
		purchasesNumber = 0;
		branchOffices = new HashSet<>();
	}
}

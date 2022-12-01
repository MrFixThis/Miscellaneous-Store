package co.edu.unbosque.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
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
 * @author Bryan Baron
 */
@Entity
@Table(name = "transaction")
@Data
@NoArgsConstructor @AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Transaction {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "client_name", nullable = false)
	private String clientName;

	@Column(name = "client_identification_number", length = 25, nullable = false)
	private String clientIdentificationNumber;

	@Column(name = "client_identification_type", length = 80, nullable = false)
	private String clientIdentificationType;

	@Column(name = "product_name", nullable = false)
	private String productName;

	@Column(name = "product_type", nullable = false)
	@Enumerated(EnumType.STRING)
	private ProductType productType;

	@Column(name = "product_price", nullable = false)
	private Long productPrice;

	@Column(name = "product_quantity", nullable = false)
	private Long productQuantity;

	@Column(name = "transaction_cost", nullable = false)
	private Long transactionCost;

	@JsonIgnore
	@ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH},
		fetch = FetchType.LAZY)
	@JoinColumn(name = "branch_office_id", nullable = false)
	private BranchOffice branchOffice;

	/**
	 * Enum for Transaction entities' products types
	 */
	public static enum ProductType {
		MAGAZINE,
		BOOK,
		DISC,
		VINYL_RECORD
	}
}

package team5.EPIC_ENERGY_SERVICES.customers;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import team5.EPIC_ENERGY_SERVICES.address.Address;

@Entity
@Table(name = "customers")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {

	@Id
	@GeneratedValue
	private UUID id;
	private String businessName;
	private String email;
	private String contactName;
	private String contactLastname;
	private String contactEmail;
	private String VATNumber;
	private LocalDate added;
	private LocalDate lastContact;
	private BigDecimal annualTurnover;
	private String pec;
	private String phoneNo;
	private String contactPhone;
	@Enumerated(EnumType.STRING)
	private BusinessType customerType;
	@OneToOne
	@JoinColumn(name = "legal_address_id")
	private Address legalAddress;
	@OneToOne
	@JoinColumn(name = "operational_address_id")
	private Address operationalAddress;

}

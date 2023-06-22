package team5.EPIC_ENERGY_SERVICES.address;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import team5.EPIC_ENERGY_SERVICES.customers.Customer;
import team5.EPIC_ENERGY_SERVICES.municipality.Municipality;

@Entity
@Table(name = "addresses")
@Data
@NoArgsConstructor
public class Address {
	@Id
	@GeneratedValue
	private UUID id;
	private String street;
	private String buildingNumber;
	private String city;
	private String zipCode;

	@ManyToOne
	@JoinColumn(name = "municipality_id")
	@JsonManagedReference
	Municipality municipality;

	@OneToOne(mappedBy = "legalAddress")
	private Customer legalCustomer;

	@OneToOne(mappedBy = "operationalAddress")
	private Customer operationalCustomer;

	public Address(String street, String buildingNumber, String city,
			String zipCode, Municipality municipality) {
		super();
		this.street = street;
		this.buildingNumber = buildingNumber;
		this.city = city;
		this.zipCode = zipCode;
		this.municipality = municipality;
	}

}

package team5.EPIC_ENERGY._ERVICES.address;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "addresses")
@Data
public class Address {
	@Id
	@GeneratedValue
	UUID id;
	String street;
	String buildingNumber;
	String city;
	String zipCode;
	String municipality;

	public Address(String street, String buildingNumber, String city,
			String zipCode, String municipality) {
		this.street = street;
		this.buildingNumber = buildingNumber;
		this.city = city;
		this.zipCode = zipCode;
		this.municipality = municipality;
	}

}

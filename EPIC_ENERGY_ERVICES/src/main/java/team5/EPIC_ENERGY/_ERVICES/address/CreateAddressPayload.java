package team5.EPIC_ENERGY._ERVICES.address;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class CreateAddressPayload {
	@NotNull(message = "Street is required")
	@Size(min = 3, max = 30, message = "Street must be between 3 and 30 characters")
	String street;

	@NotNull(message = "Building number is required")
	String buildingNumber;

	@NotNull(message = "City is required")
	String city;

	@NotNull(message = "ZIP Code is required")
	@Size(min = 5, max = 5, message = "ZIP code must have 5 digits")
	String zipCode;
}

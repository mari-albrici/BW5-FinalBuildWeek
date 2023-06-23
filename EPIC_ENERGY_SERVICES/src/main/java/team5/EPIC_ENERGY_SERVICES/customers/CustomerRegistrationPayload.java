package team5.EPIC_ENERGY_SERVICES.customers;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CustomerRegistrationPayload {
	@NotNull
	@Size(min = 1, max = 30, message = "Username must be between 8 and 30 characters")
	String businessName;
	@NotNull
	@Email(message = "Email address is invalid")
	String email;

	@Size(min = 3, max = 30, message = "Min3 characters, max 30")
	String contactName;

	String contactLastname;
	@Email(message = "Email address is invalid")
	String contactEmail;

	String VATNumber;

	@JsonFormat(shape= JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
	LocalDate added;

	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
	LocalDate lastContact;

	BigDecimal annualTurnover;

	String pec;

	String phoneNo;

	String contactPhone;

	BusinessType customerType;

	UUID legalAddress;

	UUID operationalAddress;
}

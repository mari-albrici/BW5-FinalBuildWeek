package team5.EPIC_ENERGY_SERVICES.invoice.payload;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import team5.EPIC_ENERGY_SERVICES.invoice.InvoiceType;

@Getter
@Setter
public class InvoicePayload {

	@NotNull(message = "enter a valid year")
//	@Size(min = 0, max = 3000)
	int year;

// --------------------------------------
	@NotNull(message = "enter a valid date")
	LocalDate date;

// --------------------------------------	
	@NotNull(message = "enter a valid amount")
//	@Size(min = 3)
	double amount;

// ---------------------------------------	
	@NotNull(message = "enter a valid invoice Number")
//	@Size(min = 10, max = 18)
	String invoiceNumber;

// ----------------------------------------
	@NotNull(message = "enter a valid invoice type")
	InvoiceType type;

// ----------------------------------------

}

package team5.EPIC_ENERGY_SERVICES.invoice.payload;

import java.time.LocalDate;
import java.util.UUID;

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
	@NotNull
	UUID customerId;

	public InvoicePayload(@NotNull(message = "enter a valid year") int year,
			@NotNull(message = "enter a valid date") LocalDate date,
			@NotNull(message = "enter a valid amount") double amount,
			@NotNull(message = "enter a valid invoice Number") String invoiceNumber,
			@NotNull(message = "enter a valid invoice type") InvoiceType type,
			@NotNull(message = "enter a valid customerId") UUID customerId) {
		super();
		this.year = year;
		this.date = date;
		this.amount = amount;
		this.invoiceNumber = invoiceNumber;
		this.type = type;
		this.customerId = customerId;
	}
}

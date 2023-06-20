package team5.EPIC_ENERGY_SERVICES.invoice.payload;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import team5.EPIC_ENERGY_SERVICES.customers.Customer;
import team5.EPIC_ENERGY_SERVICES.invoice.Invoice;
import team5.EPIC_ENERGY_SERVICES.invoice.InvoiceType;

@Getter
@Setter
public class InvoicePayload extends Invoice{



	@NotNull(message = "enter a valid year")
	@Size(min = 4 , max = 4)
	int year;
	
	
// --------------------------------------
	@NotNull(message = "enter a valid date")
	LocalDate date;
	
	
// --------------------------------------	
	@NotNull(message = "enter a valid amount")
	@Size(min = 3)
	double amount;
	
	
// ---------------------------------------	
	@NotNull(message = "enter a valid invoice Number")
	@Size(min = 10, max = 18)
	String invoiceNumber;
	
	
// ----------------------------------------
	@NotNull(message = "enter a valid invoice type")
	InvoiceType type;
	
	
// ----------------------------------------
	public InvoicePayload(
			@NotNull(message = "enter a valid year") @Size(min = 4, max = 4) int year,
			@NotNull(message = "enter a valid date") LocalDate date,
			@NotNull(message = "enter a valid amount") @Size(min = 3) double amount,
			@NotNull(message = "enter a valid invoice Number") @Size(min = 10, max = 18) String invoiceNumber,
			@NotNull(message = "enter a valid invoice type") InvoiceType type) {
		super();
		this.year = year;
		this.date = date;
		this.amount = amount;
		this.invoiceNumber = invoiceNumber;
		this.type = type;
	}
}
	

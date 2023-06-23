package team5.EPIC_ENERGY_SERVICES.invoice;

import java.time.LocalDate;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import team5.EPIC_ENERGY_SERVICES.customers.Customer;

@Entity
@Table(name = "invoice")
@Data
@NoArgsConstructor
public class Invoice {

	@Id
	@GeneratedValue
	private UUID id;
	private int year;
	private LocalDate date;
	private double amount;
	private String invoiceNumber;
	@Enumerated(EnumType.STRING)
	private InvoiceType type;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "customer_id")
	private Customer customerId;

	public Invoice(int year, LocalDate date, double amount, String invoiceNumber, InvoiceType type, Customer customerId) {
		super();
		this.year = year;
		this.date = date;
		this.amount = amount;
		this.invoiceNumber = invoiceNumber;
		this.type = type;
		this.customerId = customerId;
	}

	public Invoice(int year, LocalDate date, double amount, String invoiceNumber, InvoiceType invoiceType) {
		super();
		this.year = year;
		this.date = date;
		this.amount = amount;
		this.invoiceNumber = invoiceNumber;
		this.type = invoiceType;
	}
}

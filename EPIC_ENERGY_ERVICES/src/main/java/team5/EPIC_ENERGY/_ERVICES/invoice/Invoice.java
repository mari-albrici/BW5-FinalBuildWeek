package team5.EPIC_ENERGY._ERVICES.invoice;

import java.time.LocalDate;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

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
	
	
	public Invoice(int year, LocalDate date, double amount, String invoiceNumber, InvoiceType type) {
		super();
		this.year = year;
		this.date = date;
		this.amount = amount;
		this.invoiceNumber = invoiceNumber;
		this.type = type;
	}
}

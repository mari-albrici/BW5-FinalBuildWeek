package team5.EPIC_ENERGY_SERVICES.customers;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name= "customers")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {

    @Id
    @GeneratedValue
    private UUID customerId;

    private String businessName;
    private String VATNumber;
    private String email;
    private LocalDate added;
    private LocalDate lastContact;
    private BigDecimal annualTurnover;
    private String pec;
    private long phoneNo;
    private String contactEmail;
    private String contactName;
    private String contactLastname;
    private long contactPhone;
    private String legalAddress;
    private String operationalAddress;
    private BusinessType customerType;
    private List<Users> usersList;
    private List<Invoice> invoicesList;

}

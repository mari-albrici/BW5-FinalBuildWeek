package team5.EPIC_ENERGY_SERVICES.customers;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import team5.EPIC_ENERGY_SERVICES.invoice.Invoice;
import team5.EPIC_ENERGY_SERVICES.users.User;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
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
    private String phoneNo;
    private String contactEmail;
    private String contactName;
    private String contactLastname;
    private String contactPhone;
    private String legalAddress;
    private String operationalAddress;

    @Enumerated(EnumType.STRING)
    private BusinessType customerType;

}

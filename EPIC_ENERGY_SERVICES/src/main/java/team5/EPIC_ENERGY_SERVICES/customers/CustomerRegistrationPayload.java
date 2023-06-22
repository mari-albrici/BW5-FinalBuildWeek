package team5.EPIC_ENERGY_SERVICES.customers;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class CustomerRegistrationPayload {
    @NotNull
    @Size(min = 8, max = 30, message = "Username must be between 8 and 30 characters")
    String businessName;
    @NotNull
    @Email(message = "Email address is invalid")
    String email;

    @Size(min = 3, max = 30, message = "Min3 characters, max 30")
    String contactName;

    String contactLastname;
    @Email(message = "Email address is invalid")
    String contactEmail;
    @NotNull(message = "Password is requested")
    String password;

    String VATNumber;

    LocalDate added;

    LocalDate lastContact;

    BigDecimal annualTurnover;

    String pec;

    String phoneNo;

    String contactPhone;

    BusinessType customerType;
}

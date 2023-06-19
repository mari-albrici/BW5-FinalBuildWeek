package team5.EPIC_ENERGY_SERVICES.customers;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CustomerRegistrationPayload extends Customer {
    @NotNull
    @Size(min = 8, max = 30, message = "Username must be between 8 and 30 characters")
    String businessName;
    @Email(message = "Email address is invalid")
    String email;
    @NotNull(message = "Name is requested")
    @Size(min = 3, max = 30, message = "Min3 characters, max 30")
    String contactName;
    @NotNull(message = "Lastname is requested")
    String contactLastname;
    @Email(message = "Email address is invalid")
    String contactEmail;
    @NotNull(message = "Password is requested")
    String password;
}

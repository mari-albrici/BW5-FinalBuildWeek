package team5.EPIC_ENERGY_SERVICES.users.payload;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserRegistrationPayload {
	@NotNull(message = "Name is required")
	@Size(min = 3, max = 30, message = "Name must be between 3 and 30 characters")
	String name;
	@NotNull(message = "Username is required")
	@Size(min = 3, max = 30, message = "Username must be between 3 and 30 characters")
	String username;
	@NotNull(message = "Surname is required")
	String surname;
	@Email(message = "Invalid email address")
	String email;
	@NotNull(message = "Password is required")
	String password;
//	@NotNull(message = "Il customer è obbligatorio")
//	UUID customerId;

	public UserRegistrationPayload(
			@NotNull(message = "Username is required") @Size(min = 3, max = 30, message = "Username must be between 3 and 30 characters") String username,
			@NotNull(message = "Name is required") @Size(min = 3, max = 30, message = "Name must be between 3 and 30 characters") String name,
			@NotNull(message = "Surname is required") String surname,
			@Email(message = "Invalid email address") String email,
			@NotNull(message = "Password is required") String password) {
		super();
		this.name = name;
		this.username = username;
		this.surname = surname;
		this.email = email;
		this.password = password;
//		this.customerId = customerId;
	}

}
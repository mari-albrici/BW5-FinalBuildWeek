package team5.EPIC_ENERGY_SERVICES.users.payload;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserRegistrationPayload {
	@NotNull(message = "Il nome è obbligatorio")
	@Size(min = 3, max = 30, message = "Nome min 3 caratteri, massimo 30")
	String name;
	@NotNull(message = "Lo username è obbligatorio")
	@Size(min = 3, max = 30, message = "Username min 3 caratteri, massimo 30")
	String username;
	@NotNull(message = "Il cognome è obbligatorio")
	String surname;
	@Email(message = "Non hai inserito un indirizzo email valido")
	String email;
	@NotNull(message = "La password è obbligatoria")
	String password;

	public UserRegistrationPayload(
			@NotNull(message = "Lo username è obbligatorio") @Size(min = 3, max = 30, message = "Username min 3 caratteri, massimo 30") String username,
			@NotNull(message = "Il nome è obbligatorio") @Size(min = 3, max = 30, message = "Nome min 3 caratteri, massimo 30") String name,
			@NotNull(message = "Il cognome è obbligatorio") String surname,
			@Email(message = "Non hai inserito un indirizzo email valido") String email,
			@NotNull(message = "La password è obbligatoria") String password) {
		super();
		this.name = name;
		this.username = username;
		this.surname = surname;
		this.email = email;
		this.password = password;
	}

}
package team5.EPIC_ENERGY_SERVICES.users.payload;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class UserLoginPayload {
	@NotNull
	String email;
	@NotNull
	String password;
}
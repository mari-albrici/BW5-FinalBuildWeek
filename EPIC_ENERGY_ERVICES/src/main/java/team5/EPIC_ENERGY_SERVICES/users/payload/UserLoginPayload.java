package team5.EPIC_ENERGY_SERVICES.users.payload;

import lombok.Getter;

@Getter
public class UserLoginPayload {
	String email;
	String password;
}
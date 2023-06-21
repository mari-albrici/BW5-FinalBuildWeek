package team5.EPIC_ENERGY_SERVICES.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import team5.EPIC_ENERGY_SERVICES.auth.payload.AuthenticationSuccessfullPayload;
import team5.EPIC_ENERGY_SERVICES.exceptions.NotFoundException;
import team5.EPIC_ENERGY_SERVICES.exceptions.UnauthorizedException;
import team5.EPIC_ENERGY_SERVICES.users.User;
import team5.EPIC_ENERGY_SERVICES.users.UserService;
import team5.EPIC_ENERGY_SERVICES.users.payload.UserLoginPayload;
import team5.EPIC_ENERGY_SERVICES.users.payload.UserRegistrationPayload;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	UserService usersService;

	@PostMapping("/register")
	public ResponseEntity<User> register(@RequestBody @Validated UserRegistrationPayload body) {
		User createdUser = usersService.create(body);
		return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
	}

	@PostMapping("/login")
	public ResponseEntity<AuthenticationSuccessfullPayload> login(@RequestBody UserLoginPayload body)
			throws NotFoundException {
		User user = usersService.findByEmail(body.getEmail());

		if (!body.getPassword().matches(user.getPassword())) {
			throw new UnauthorizedException("Credenziali non valide");
		}

		String token = JWTTools.createToken(user);
		return new ResponseEntity<>(new AuthenticationSuccessfullPayload(token), HttpStatus.OK);
	}

}
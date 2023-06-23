package team5.EPIC_ENERGY_SERVICES.users;

import java.util.Locale;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.github.javafaker.Faker;

import team5.EPIC_ENERGY_SERVICES.users.payload.UserRegistrationPayload;

@Component
public class UsersRunner implements CommandLineRunner {
	@Autowired
	UserService usersService;

	@Autowired
	PasswordEncoder bcrypt;

	@Override
	public void run(String... args) throws Exception {
		Faker faker = new Faker(new Locale("en"));
		for (int i = 0; i < 5; i++) {
			try {
				String username = faker.funnyName().name();
				String name = faker.name().firstName();
				String surname = faker.name().lastName();
				String email = faker.internet().emailAddress();
				String password = bcrypt.encode("1234");
				UUID customerId = UUID
						.fromString("d67ab67f-694e-4412-a2ef-7784126832fa");

				UserRegistrationPayload user = new UserRegistrationPayload(
						username, name, surname, email, password, customerId);
//				usersService.create(user);
			} catch (Exception e) {
				System.out.println(e);
			}
		}

	}

}
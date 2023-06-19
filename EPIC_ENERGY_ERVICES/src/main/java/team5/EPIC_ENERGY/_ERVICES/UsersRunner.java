package team5.EPIC_ENERGY._ERVICES;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.github.javafaker.Faker;

import team5.EPIC_ENERGY._ERVICES.users.UserService;
import team5.EPIC_ENERGY._ERVICES.users.payload.UserRegistrationPayload;

@Component
public class UsersRunner implements CommandLineRunner {
	@Autowired
	UserService usersService;

	@Override
	public void run(String... args) throws Exception {
		Faker faker = new Faker(new Locale("en"));
		for (int i = 0; i < 5; i++) {
			try {
				String username = faker.funnyName().name();
				String name = faker.name().firstName();
				String surname = faker.name().lastName();
				String email = faker.internet().emailAddress();
				String password = "1234";

				UserRegistrationPayload user = new UserRegistrationPayload(username, name, surname, email, password);
				// usersService.create(user);
			} catch (Exception e) {
				System.out.println(e);
			}
		}

	}

}
package team5.EPIC_ENERGY_SERVICES.customers;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Locale;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.github.javafaker.Faker;

import team5.EPIC_ENERGY_SERVICES.address.AddressRepository;
import team5.EPIC_ENERGY_SERVICES.address.AddressService;
import team5.EPIC_ENERGY_SERVICES.municipality.MunicipalityService;

@Component
public class CustomerRunner implements CommandLineRunner {

	@Autowired
	private CustomerService customerService;

	@Autowired
	AddressService addressService;

	@Autowired
	MunicipalityService municipalityService;

	@Autowired
	AddressRepository addressRepo;

	@Override
	public void run(String... args) throws Exception {

		Faker faker = new Faker(new Locale("it"));

		for (int i = 0; i < 1; i++)
			try {
				CustomerRegistrationPayload customer = new CustomerRegistrationPayload();
				customer.setBusinessName(faker.funnyName().name());
				customer.setAdded(LocalDate.of(2023, 1, 1));
				customer.setLastContact(customer.getAdded().plusDays(42));
				customer.setCustomerType(
						faker.options().option(BusinessType.class));
				customer.setEmail(faker.internet().emailAddress());
				customer.setAnnualTurnover(
						BigDecimal.valueOf(faker.number().randomNumber()));
				customer.setContactEmail(faker.internet().emailAddress());
				customer.setContactLastname(faker.name().lastName());
				customer.setContactName(faker.name().firstName());
				customer.setContactPhone(faker.phoneNumber().phoneNumber());
				customer.setPec(faker.internet().emailAddress());
				customer.setPhoneNo(faker.phoneNumber().phoneNumber());
				customer.setLegalAddress(UUID
						.fromString("06423e34-d97c-465d-ba92-c6475bd50cde"));
				customer.setVatnumber(String.valueOf(
						faker.number().numberBetween(111111111, 999999999)));
//				customerService.create(customer);
			} catch (Exception e) {
				System.out.println(e);
			}

	}
}

package team5.EPIC_ENERGY_SERVICES.customers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Locale;
import com.github.javafaker.Faker;

@Component
public class CustomerRunner implements CommandLineRunner {

    @Autowired
    private CustomerService customerService;

    @Override
    public void run(String... args) throws Exception {

        Faker faker = new Faker(new Locale("it"));

        for (int i = 0; i < 10; i++)
            try {
                Customer customer = new Customer();
                customer.setBusinessName(faker.funnyName().name());
                customer.setAdded(LocalDate.of(2023, 01, 01));
                customer.setLastContact(customer.getAdded().plusDays(42));
                customer.setCustomerType(BusinessType.SAS);
                customer.setEmail(faker.internet().emailAddress());
                customer.setAnnualTurnover(BigDecimal.valueOf(faker.number().randomNumber()));
                customer.setContactEmail(faker.internet().emailAddress());
                customer.setContactLastname(faker.name().lastName());
                customer.setContactName(faker.name().firstName());
                customer.setContactPhone(faker.phoneNumber().phoneNumber());
                customer.setPec(faker.internet().emailAddress());
                customer.setPhoneNo(faker.phoneNumber().phoneNumber());
                customer.setLegalAddress(faker.address().fullAddress());
                customer.setVATNumber(String.valueOf(faker.number().numberBetween(111111111, 999999999)));
                customerService.create(customer);
            } catch (Exception e) {
                System.out.println(e);
            }

    }
}

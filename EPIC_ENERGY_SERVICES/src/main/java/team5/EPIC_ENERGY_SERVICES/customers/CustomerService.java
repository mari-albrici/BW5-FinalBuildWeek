package team5.EPIC_ENERGY_SERVICES.customers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import team5.EPIC_ENERGY_SERVICES.exceptions.NotFoundException;

@Service
public class CustomerService {

	@Autowired
	private CustomerRepository customerRepo;

	public Customer create(CustomerRegistrationPayload body) {
		Customer c = new Customer();
		c.setBusinessName(body.getBusinessName());
		c.setEmail(body.getEmail());
		c.setContactName(body.getContactName());
		c.setContactLastname(body.getContactLastname());
		c.setContactEmail(body.getContactEmail());
		// VATNumber
		c.setAdded(body.getAdded());
		// lastContact
		c.setAnnualTurnover(body.getAnnualTurnover());
		c.setPec(body.getPec());
		c.setPhoneNo(body.getPhoneNo());
		c.setContactPhone(body.getContactPhone());
		c.setCustomerType(body.getCustomerType());
		// UUID legalAddress
		// UUID operationalAddress
		return customerRepo.save(c);
	}

	public Page<Customer> find(int page, int size, String sortBy) {
		if (size < 0)
			size = 10;
		if (size > 100)
			size = 100;
		Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));

		return customerRepo.findAll(pageable);
	}

	public Customer findById(UUID id) throws NotFoundException {
		return customerRepo.findById(id).orElseThrow(() -> new NotFoundException("User not found"));
	}

	public Customer findByIdAndUpdate(UUID id, CustomerRegistrationPayload c) throws NotFoundException {
		Customer found = this.findById(id);

		found.setBusinessName(c.getBusinessName());
		found.setEmail(c.getEmail());
		found.setContactName(c.getContactName());
		found.setContactLastname(c.getContactLastname());
		found.setContactEmail(c.getContactEmail());
		// VATNumber
		found.setAdded(c.getAdded());
		// lastContact
		found.setAnnualTurnover(c.getAnnualTurnover());
		found.setPec(c.getPec());
		found.setPhoneNo(c.getPhoneNo());
		found.setContactPhone(c.getContactPhone());
		found.setCustomerType(c.getCustomerType());
		// UUID legalAddress
		// UUID operationalAddress

		return customerRepo.save(found);
	}

	public void findByIdAndDelete(UUID id) throws NotFoundException {
		Customer found = this.findById(id);
		customerRepo.delete(found);
	}

}

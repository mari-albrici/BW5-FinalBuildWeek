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
		c.setBusinessName(c.getBusinessName());
		c.setEmail(c.getEmail());
		c.setContactName(c.getContactName());
		c.setContactLastname(c.getContactLastname());
		c.setContactEmail(c.getContactEmail());
		// VATNumber
		c.setAdded(c.getAdded());
		// lastContact
		c.setAnnualTurnover(c.getAnnualTurnover());
		c.setPec(c.getPec());
		c.setPhoneNo(c.getPhoneNo());
		c.setContactPhone(c.getContactPhone());
		c.setCustomerType(c.getCustomerType());
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

// -------------- EXTRA FILTERS -----------------
    public Page<Customer> findCustomerByAnnualTurnover(BigDecimal annualTurnover, int page, int size, String sortBy) {
        if (!Objects.equals(annualTurnover, BigDecimal.ZERO)) {
            Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
            return customerRepo.findCustomerByAnnualTurnover(annualTurnover, pageable);
        } else {
            return Page.empty();
        }
    }

    public Page<Customer> findCustomerByAdded(LocalDate added, int page, int size, String sortBy) {
        if (added != null) {
            Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
            return customerRepo.findCustomerByAdded(added, pageable);
        } else {
            return Page.empty();
        }
    }

    public Page<Customer> findCustomerByLastContact(LocalDate lastContact, int page, int size, String sortBy) {
        if (lastContact != null) {
            Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
            return customerRepo.findCustomerByLastContact(lastContact, pageable);
        } else {
            return Page.empty();
        }
    }

    public Page<Customer> findCustomerByBusinessName(String businessName, int page, int size, String sortBy) {
        if (businessName != null) {
            Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
            return customerRepo.findCustomerByBusinessName(businessName, pageable);
        } else {
            return Page.empty();
        }
    }

	public void findByIdAndDelete(UUID id) throws NotFoundException {
		Customer found = this.findById(id);
		customerRepo.delete(found);
	}

}

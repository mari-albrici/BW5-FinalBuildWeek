package team5.EPIC_ENERGY_SERVICES.customers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import team5.EPIC_ENERGY_SERVICES.exceptions.NotFoundException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepo;

    public Customer create(Customer c){
        return customerRepo.save(c);
    }

    public Customer createPayload(CustomerRegistrationPayload body) {
        Customer c = new Customer();
        c.setBusinessName(body.getBusinessName());
        c.setAdded(body.getAdded());
        c.setCustomerType(body.getCustomerType());
        c.setEmail(body.getEmail());
        c.setAnnualTurnover(body.getAnnualTurnover());
        c.setContactEmail(body.getContactEmail());
        c.setContactLastname(body.getContactLastname());
        c.setContactName(body.getContactName());
        c.setContactPhone(body.getContactPhone());
        c.setPec(body.getPec());
        c.setPhoneNo(body.getPhoneNo());
        return customerRepo.save(c);
    }

    public Page<Customer> find(int page, int size, String sortBy){
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
        found.setAdded(c.getAdded());
        found.setCustomerType(c.getCustomerType());
        found.setEmail(c.getEmail());
        found.setAnnualTurnover(c.getAnnualTurnover());
        found.setContactEmail(c.getContactEmail());
        found.setContactLastname(c.getContactLastname());
        found.setContactName(c.getContactName());
        found.setContactPhone(c.getContactPhone());
        found.setPec(c.getPec());
        found.setPhoneNo(c.getPhoneNo());

        return customerRepo.save(found);
    }

    public void findByIdAndDelete(UUID id) throws NotFoundException {
        Customer found = this.findById(id);
        customerRepo.delete(found);
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


}

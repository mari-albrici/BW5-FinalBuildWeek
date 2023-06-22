package team5.EPIC_ENERGY_SERVICES.customers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import team5.EPIC_ENERGY_SERVICES.exceptions.NotFoundException;

import java.util.List;
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


}

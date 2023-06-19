package team5.EPIC_ENERGY_SERVICES.customers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepo;

    public Customer create(Customer c) {
        return customerRepo.save(c);
    }

    public List<Customer> find(){
        return customerRepo.findAll();
    }

    public Customer findById(UUID id) throws NotFoundException {
        return customerRepo.findById(id).orElseThrow(() -> new NotFoundException("User not found"));
    }

    public Customer findByIdAndUpdate(UUID id, Customer c) throws NotFoundException {
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
        found.setLegalAddress(c.getLegalAddress());

        return customerRepo.save(found);
    }

    public void findByIdAndDelete(UUID id) throws NotFoundException {
        Customer found = this.findById(id);
        customerRepo.delete(found);
    }
}

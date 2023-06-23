package team5.EPIC_ENERGY_SERVICES.customers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customers")
@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	// ********** GET ALL CUSTOMERS **********
	@GetMapping("")
	public Page<Customer> getCustomer(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "id") String sortBy) {
		return customerService.find(page, size, sortBy);
	}

	// ********** POST NEW CUSTOMER **********
	@PostMapping("")
	@PostAuthorize("hasAuthority('ADMIN')")
	@ResponseStatus(HttpStatus.CREATED)
	public Customer saveCustomer(@RequestBody @Validated CustomerRegistrationPayload body) {
		return customerService.create(body);
	}

	// ********** GET SINGLE CUSTOMER **********
	@GetMapping("/{id}")
	public Customer getCustomer(@PathVariable UUID id) throws Exception {
		return customerService.findById(id);
	}

	// ********** PUT SINGLE CUSTOMER **********
	@PutMapping("/{id}")
	@PostAuthorize("hasAuthority('ADMIN')")
	public Customer updateCustomer(@PathVariable UUID id, @RequestBody CustomerRegistrationPayload body)
			throws Exception {
		return customerService.findByIdAndUpdate(id, body);
	}

	// ********** DELETE SINGLE CUSTOMER **********
	@DeleteMapping("/{id}")
	@PostAuthorize("hasAuthority('ADMIN')")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteUser(@PathVariable UUID id) throws Exception {
		customerService.findByIdAndDelete(id);
	}


    //********** EXTRA FILTER ENDPOINTS **********

    @GetMapping("/findby/{annualTurnover}")
    public ResponseEntity<Page<Customer>> getCustomerByTurnover(@PathVariable BigDecimal annualTurnover) {
        Page<Customer> customers = customerService.findCustomerByAnnualTurnover(annualTurnover, 0, 20, "customerId");
        return ResponseEntity.ok(customers);
    }

    @GetMapping("/findby/{added}")
    public ResponseEntity<Page<Customer>> getCustomersByAdded(@PathVariable String added)  {

        LocalDate dateAdded = LocalDate.parse(added);

        Page<Customer> customers = customerService.findCustomerByAdded(dateAdded, 0, 20, "customerId");
        return ResponseEntity.ok(customers);
    }

    @GetMapping("/findby/{lastContact}")
    public ResponseEntity<Page<Customer>> getCustomersByLastContact(@PathVariable String lastContact) {

        LocalDate lastContacted = LocalDate.parse(lastContact);

        Page<Customer> customers = customerService.findCustomerByLastContact(lastContacted, 0, 20, "customerId");
        return ResponseEntity.ok(customers);
    }

    @GetMapping("/findby/{businessName}")
    public ResponseEntity<Page<Customer>> findCustomersByBusinessName(@PathVariable String businessName) {
        Page<Customer> customers = customerService.findCustomerByBusinessName(businessName, 0, 20, "customerId");
        return ResponseEntity.ok(customers);
    }

}

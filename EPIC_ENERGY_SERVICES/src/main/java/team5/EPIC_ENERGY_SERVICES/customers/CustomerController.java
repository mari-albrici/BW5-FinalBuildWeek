package team5.EPIC_ENERGY_SERVICES.customers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import team5.EPIC_ENERGY_SERVICES.users.User;

import java.util.UUID;

@RestController
@RequestMapping("/customers")
@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    //********** GET ALL CUSTOMERS **********
    @GetMapping("")
    public Page<Customer> getCustomer(@RequestParam(defaultValue = "0") int page,
                               @RequestParam(defaultValue = "10") int size,
                               @RequestParam(defaultValue = "id") String sortBy) {
        return customerService.find(page, size, sortBy);
    }

    //********** POST NEW CUSTOMER **********
    @PostMapping("")
    @PostAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    public Customer saveCustomer(@RequestBody @Validated CustomerRegistrationPayload body) {
        return customerService.create(body);
    }

    //********** GET SINGLE CUSTOMER **********
    @GetMapping("/{id}")
    public Customer getCustomer(@PathVariable UUID id) throws Exception {
        return customerService.findById(id);
    }

    //********** PUT SINGLE CUSTOMER **********
    @PutMapping("/{id}")
    @PostAuthorize("hasAuthority('ADMIN')")
    public Customer updateCustomer(@PathVariable UUID id, @RequestBody Customer body) throws Exception {
        return customerService.findByIdAndUpdate(id, body);
    }

    //********** DELETE SINGLE CUSTOMER **********
    @DeleteMapping("/{id}")
    @PostAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable UUID id) throws Exception {
        customerService.findByIdAndDelete(id);
    }

}

package team5.EPIC_ENERGY_SERVICES.customers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    //********** GET ALL CUSTOMERS **********
    @GetMapping("")
    public Page<Customer> getUsers(@RequestParam(defaultValue = "0") int page){
        return (Page<Customer>) customerService.find(10);
    }

    //********** POST NEW CUSTOMER **********
    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public Customer saveCustomer(@RequestBody CustomerRegistrationPayload body) {
        return customerService.create(body);
    }

    //********** GET SINGLE CUSTOMER **********
    @GetMapping("/{username}")
    public Customer getCustomer(@PathVariable UUID id) throws Exception {
        return customerService.findById(id);
    }

    //********** PUT SINGLE CUSTOMER **********
    @PutMapping("/{username}")
    public Customer updateCustomer(@PathVariable UUID id, @RequestBody Customer body) throws Exception {
        return customerService.findByIdAndUpdate(id, body);
    }

    //********** DELETE SINGLE CUSTOMER **********
    @DeleteMapping("/{username}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable UUID id) throws Exception {
        customerService.findByIdAndDelete(id);
    }

}

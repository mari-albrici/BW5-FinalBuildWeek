package team5.EPIC_ENERGY._ERVICES.address;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/address")
public class AddressController {

	@Autowired
	AddressService addressService;

	// CRUD:
	// 1. CREATE (POST METHOD) - http://localhost:3001/address
	@PostMapping("")
	public Address saveAddress(
			@RequestBody @Validated CreateAddressPayload body) {
		return addressService.save(body);
	}

	// 2. READ (GET METHOD) - http://localhost:3001/address
	@GetMapping("")
	public List<Address> getAllAddresses() {
		return addressService.getAll();
	}
}

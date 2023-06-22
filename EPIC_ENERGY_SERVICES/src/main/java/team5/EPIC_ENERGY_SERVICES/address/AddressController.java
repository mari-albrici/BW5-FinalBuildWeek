package team5.EPIC_ENERGY_SERVICES.address;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
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
@RequestMapping("/address")
@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
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
	public Page<Address> getAllAddresses(
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size,
			@RequestParam(defaultValue = "id") String sortValue) {
		return addressService.findWithPagination(page, size, sortValue);
	}

	// 3. READ (GET METHOD) - http://localhost:3001/address/:addressId
	@GetMapping("/{addressId}")
	public Address getAddressById(@PathVariable String addressId) {
		return addressService.getAddressById(UUID.fromString(addressId));

	}

	// 4. UPDATE (PUT METHOD) - http://localhost:3001/address/:addressId + req.
	// body
	@PutMapping("/{addressId}")
	@PostAuthorize("hasAuthority('ADMIN')")
	public Address updateAddress(@PathVariable UUID addressId,
			@RequestBody Address body) throws Exception {
		return addressService.findByIdAndUpdate(addressId, body);
	}

	// 5. DELETE (DELETE METHOD) - http://localhost:3001/address/:addressId
	@DeleteMapping("/{addressId}")
	@PostAuthorize("hasAuthority('ADMIN')")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteUser(@PathVariable UUID addressId) {
		addressService.findByIdAndDelete(addressId);
	}

}

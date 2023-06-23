package team5.EPIC_ENERGY_SERVICES.users;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import team5.EPIC_ENERGY_SERVICES.exceptions.NotFoundException;
import team5.EPIC_ENERGY_SERVICES.users.payload.UserRegistrationPayload;

@RestController
@RequestMapping("/users")
@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
public class UserController {
	@Autowired
	private UserService usersService;

	@GetMapping("")
	public Page<User> getUsers(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size,
			@RequestParam(defaultValue = "id") String sortBy) {
		return usersService.find(page, size, sortBy);
	}

	@GetMapping("/{userId}")
	public User getUser(@PathVariable UUID userId) throws Exception {

		return usersService.findById(userId);
	}

	@PutMapping("/{userId}")
	@PostAuthorize("hasAuthority('ADMIN')")
	public User updateUser(@PathVariable UUID userId,
			@RequestBody UserRegistrationPayload body) throws Exception {
		return usersService.findByIdAndUpdate(userId, body);
	}

	@DeleteMapping("/{userId}")
	@PostAuthorize("hasAuthority('ADMIN')")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteUser(@PathVariable UUID userId) throws NotFoundException {
		usersService.findByIdAndDelete(userId);
	}

	@PatchMapping("/{userId}")
	@PostAuthorize("hasAuthority('ADMIN')")
	public void setAdmin(@PathVariable UUID userId) throws NotFoundException {
		usersService.findByIdAndSetAdmin(userId);
	}

}
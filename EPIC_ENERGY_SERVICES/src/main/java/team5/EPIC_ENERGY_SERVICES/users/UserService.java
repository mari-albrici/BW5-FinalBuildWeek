package team5.EPIC_ENERGY_SERVICES.users;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import team5.EPIC_ENERGY_SERVICES.customers.CustomerRepository;
import team5.EPIC_ENERGY_SERVICES.exceptions.BadRequestException;
import team5.EPIC_ENERGY_SERVICES.exceptions.NotFoundException;
import team5.EPIC_ENERGY_SERVICES.users.payload.UserRegistrationPayload;

@Service
public class UserService {
	@Autowired
	private UserRepository usersRepo;

	@Autowired
	private CustomerRepository customerRepo;

	public User create(UserRegistrationPayload u) {
		usersRepo.findByEmail(u.getEmail()).ifPresent(user -> {
			throw new BadRequestException(
					"Email " + user.getEmail() + " already in use!");
		});
		User newUser = new User(u.getUsername(), u.getName(), u.getSurname(),
				u.getEmail(), u.getPassword(),
				customerRepo.findById(u.getCustomerId()).orElseThrow(
						() -> new NotFoundException("Customer not found")));
		return usersRepo.save(newUser);
	}

	public Page<User> find(int page, int size, String sortBy) {
		if (size < 0)
			size = 10;
		if (size > 100)
			size = 100;
		Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));

		return usersRepo.findAll(pageable);
	}

	public User findById(UUID id) throws NotFoundException {
		return usersRepo.findById(id)
				.orElseThrow(() -> new NotFoundException("User not found"));
	}

	public User findByEmail(String email) throws NotFoundException {
		return usersRepo.findByEmail(email)
				.orElseThrow(() -> new NotFoundException("User not found"));
	}

	public User findByIdAndUpdate(UUID id, UserRegistrationPayload u)
			throws NotFoundException {
		User found = this.findById(id);

		found.setId(id);
		found.setUsername(u.getUsername());
		found.setName(u.getName());
		found.setSurname(u.getSurname());
		found.setEmail(u.getEmail());

		return usersRepo.save(found);
	}

	public void findByIdAndDelete(UUID id) throws NotFoundException {
		User found = this.findById(id);
		usersRepo.delete(found);
	}

	public void findByIdAndSetAdmin(UUID id) throws NotFoundException {
		User found = this.findById(id);
		found.setRole(Role.ADMIN);
		usersRepo.save(found);
	}

}
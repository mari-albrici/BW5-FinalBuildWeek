package team5.EPIC_ENERGY_SERVICES.address;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import team5.EPIC_ENERGY_SERVICES.exceptions.NotFoundException;
import team5.EPIC_ENERGY_SERVICES.municipality.Municipality;
import team5.EPIC_ENERGY_SERVICES.municipality.MunicipalityRepository;

@Service
public class AddressService {

	@Autowired
	AddressRepository addressRepo;

	@Autowired
	MunicipalityRepository municipalityRepo;

	public Address save(CreateAddressPayload payload) {

		Optional<Municipality> municipalityFound = municipalityRepo
				.findById(UUID.fromString(payload.getMunicipalityId()));

		if (municipalityFound.isPresent()) {
			Municipality municipality = municipalityFound.get();

			Address newAddress = new Address(payload.getStreet(),
					payload.getBuildingNumber(), payload.getCity(),
					payload.getZipCode(), municipality);

			return addressRepo.save(newAddress);
		} else {
			throw new IllegalArgumentException(
					"Municipality not found for number: "
							+ payload.getMunicipalityId());
		}

	}

	public List<Address> getAll() {
		return addressRepo.findAll();
	}

	public Address getAddressById(UUID id) {

		return addressRepo.findById(id).orElseThrow(
				() -> new NotFoundException("User not found for id: " + id));

	}

	public Address findByIdAndUpdate(UUID userId, CreateAddressPayload address)
			throws NotFoundException {
		Address found = this.getAddressById(userId);
		Optional<Municipality> municipalityFound = municipalityRepo
				.findById(UUID.fromString(address.getMunicipalityId()));

		found.setId(userId);
		found.setBuildingNumber(address.getBuildingNumber());
		found.setCity(address.getCity());
		found.setMunicipality(municipalityFound.orElse(null));
		found.setStreet(address.getStreet());
		found.setZipCode(address.getZipCode());

		return addressRepo.save(found);
	}

	public void findByIdAndDelete(UUID id) throws NotFoundException {
		Address found = this.getAddressById(id);
		addressRepo.delete(found);
	}

	public Page<Address> findWithPagination(int page, int size, String sortBy) {
		if (size < 0)
			size = 10;
		if (size > 100)
			size = 100;
		Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));

		return addressRepo.findAll(pageable);
	}

}

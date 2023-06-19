package team5.EPIC_ENERGY_SERVICES.address;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

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
				.findByMunicipalityNumber(payload.getMunicipalityNumber());

		if (municipalityFound.isPresent()) {
			Municipality municipality = municipalityFound.get();

			Address newAddress = new Address(payload.getStreet(),
					payload.getBuildingNumber(), payload.getCity(),
					payload.getZipCode(), municipality);

			return addressRepo.save(newAddress);
		} else {
			throw new IllegalArgumentException(
					"Municipality not found for number: "
							+ payload.getMunicipalityNumber());
		}

	}

	public List<Address> getAll() {
		return addressRepo.findAll();
	}

	public Address getAddressById(UUID id) {

		return addressRepo.findById(id).orElseThrow(
				() -> new NotFoundException("User not found for id: " + id));

	}

}

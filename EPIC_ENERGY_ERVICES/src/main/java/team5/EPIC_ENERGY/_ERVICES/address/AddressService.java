package team5.EPIC_ENERGY._ERVICES.address;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import team5.EPIC_ENERGY._ERVICES.municipality.Municipality;
import team5.EPIC_ENERGY._ERVICES.municipality.MunicipalityRepository;

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

}

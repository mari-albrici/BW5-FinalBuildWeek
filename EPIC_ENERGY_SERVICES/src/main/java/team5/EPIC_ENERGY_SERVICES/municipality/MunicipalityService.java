package team5.EPIC_ENERGY_SERVICES.municipality;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import team5.EPIC_ENERGY_SERVICES.exceptions.NotFoundException;

@Service
public class MunicipalityService {

	@Autowired
	MunicipalityRepository municipalityRepo;

	public Municipality getByMunicipalityId(String id) {

		return municipalityRepo.findById(UUID.fromString(id))
				.orElseThrow(() -> new NotFoundException(
						"Municipality not found for id: " + id));

	}
}

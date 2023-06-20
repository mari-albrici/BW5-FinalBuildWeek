package team5.EPIC_ENERGY_SERVICES.municipality;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import team5.EPIC_ENERGY_SERVICES.address.NotFoundException;

@Service
public class MunicipalityService {

	@Autowired
	MunicipalityRepository municipalityRepo;

	public Municipality getByMunicipalityNumber(String n) {

		return municipalityRepo.findByMunicipalityNumber(n)
				.orElseThrow(() -> new NotFoundException(
						"Municipality not found for id: " + n));

	}
}

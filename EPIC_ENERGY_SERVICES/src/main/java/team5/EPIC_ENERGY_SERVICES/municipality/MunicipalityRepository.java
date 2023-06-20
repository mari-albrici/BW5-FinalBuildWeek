package team5.EPIC_ENERGY_SERVICES.municipality;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MunicipalityRepository
		extends JpaRepository<Municipality, Long> {

	Optional<Municipality> findByProvinceName(String province);

	Optional<Municipality> findByMunicipalityNumber(String province);

}

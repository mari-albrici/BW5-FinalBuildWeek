package team5.EPIC_ENERGY_SERVICES.municipality;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MunicipalityRepository
		extends JpaRepository<Municipality, UUID> {

	Optional<Municipality> findByProvinceName(String province);

	Optional<Municipality> findById(UUID id);

}

package team5.EPIC_ENERGY_SERVICES.municipality;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import team5.EPIC_ENERGY_SERVICES.address.Address;

@Entity
@Table(name = "municipalities")
@Data
public class Municipality {
	@Id
	String municipalityNumber;
	String name;
	Long provinceNumber;
	String provinceName;
	String region;
	String initials;

	@OneToMany(mappedBy = "municipality")
	List<Address> addresses;
}

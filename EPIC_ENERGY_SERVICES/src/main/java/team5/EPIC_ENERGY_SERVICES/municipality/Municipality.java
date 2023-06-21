package team5.EPIC_ENERGY_SERVICES.municipality;

import java.util.List;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import team5.EPIC_ENERGY_SERVICES.address.Address;
import team5.EPIC_ENERGY_SERVICES.province.Province;

@Entity
@Table(name = "municipalities")
@Data
public class Municipality {
	@Id
	@GeneratedValue
	private UUID id;
	private Long municipalityNumber;
	private String name;
	private Long provinceNumber;
	private String provinceName;

	@ManyToOne
	@JoinColumn(name = "province_initial")
	private Province province;

	@OneToMany(mappedBy = "municipality")
	List<Address> addresses;
}

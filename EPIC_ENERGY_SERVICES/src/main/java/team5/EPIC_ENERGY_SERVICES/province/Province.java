package team5.EPIC_ENERGY_SERVICES.province;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import team5.EPIC_ENERGY_SERVICES.municipality.Municipality;

@Entity
@Table(name = "Provinces")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Province {
	@Id
	private String initials;
	private String name;
	private String region;

	@OneToMany(mappedBy = "province")
	@JsonManagedReference
	private List<Municipality> municipalities;
}

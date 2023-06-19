package team5.EPIC_ENERGY._ERVICES.municipality;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "municipalities")
@Data
public class Municipality {
	@Id
	Long municipalityNumber;
	String name;
	Long provinceNumber;
}

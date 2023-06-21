package team5.EPIC_ENERGY_SERVICES.address;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.text.similarity.JaccardSimilarity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import team5.EPIC_ENERGY_SERVICES.municipality.Municipality;
import team5.EPIC_ENERGY_SERVICES.municipality.MunicipalityRepository;
import team5.EPIC_ENERGY_SERVICES.province.Province;
import team5.EPIC_ENERGY_SERVICES.province.ProvinceRepository;

@Component
public class AddressRunner implements CommandLineRunner {

	@Autowired
	MunicipalityRepository municipalityRepo;
	@Autowired
	ProvinceRepository provinceRepo;

	@Override
	public void run(String... args) throws Exception {

		// Se le tabelle su db sono vuote, vengono popolate
		if (municipalityRepo.findAll().isEmpty()
				&& provinceRepo.findAll().isEmpty()) {
			// Il primo record contiene il nome delle colonne
			// Quindi la prima riga deve essere saltata
			boolean firstMunicipalityRecord = true;
			boolean firstProvinceRecord = true;

			// Richiamo il metodo 'csvToArray che ho creato per leggere il
			// contenuto dei csv e salvarli su array
			List<List<String>> municipalities = csvToArray(
					"src/main/java/team5/EPIC_ENERGY_SERVICES/municipality/csv/comuni-italiani.csv",
					';');

			List<List<String>> provinces = csvToArray(
					"src/main/java/team5/EPIC_ENERGY_SERVICES/municipality/csv/province-italiane.csv",
					';');

			int i = 1;
			for (List<String> m : municipalities) {

				// Se è il primo record viene omesso il salvataggio su db
				if (firstMunicipalityRecord) {
					firstMunicipalityRecord = false;
					continue;
				}

				Municipality municipality = new Municipality();

				municipality.setProvinceNumber(Long.parseLong(m.get(0)));

				// Fix del valore #RIF!
				if (m.get(3).equals("Sassari")) {
					municipality.setMunicipalityNumber((long) i);
					i++;

				} else {

					municipality
							.setMunicipalityNumber(Long.parseLong(m.get(1)));
				}
				municipality.setName(m.get(2));
				municipality.setProvinceName(m.get(3));

				// Itero sulle provincie e le salvo su db
				for (List<String> p : provinces) {

					// Se è il primo record viene omesso il salvataggio su db
					if (firstProvinceRecord) {
						firstProvinceRecord = false;
						continue;
					}

					Province province = new Province();
					province.setInitials(p.get(0));
					province.setName(p.get(1));
					province.setRegion(p.get(2));

					provinceRepo.save(province);

					// Cerco la corrispondenza tra comune e provincia,
					// in molti casi il nome delle provincie nei due file non e
					// scritto uguale

					String provinceCSV = m.get(3);
					String provinceDB = p.get(1);

					JaccardSimilarity jaccardSimilarity = new JaccardSimilarity();
					double similarity = jaccardSimilarity.apply(provinceCSV,
							provinceDB);

					if (similarity >= 0.8) {
						municipality.setProvince(province);
					}

//					if (m.get(3).equals(p.get(1)) || m.get(3).split(" ")[0]
//							.equals(p.get(1).split(" ")[0])) {
//
//						municipality.setProvince(province);
//
//					}

				}

				municipalityRepo.save(municipality);
			}

		}

	}

	// Creo un metodo per riutlizzare il codice
	private static List<List<String>> csvToArray(String filePath,
			char separator) throws IOException {
		// Carica e legge primo file csv (comuni)
		// Crea un nuovo Array che contiene liste di String
		List<List<String>> records = new ArrayList<List<String>>();

		// I file csv dati da Epicode usano ';' come separatore invece di ','
		CSVParser parser = new CSVParserBuilder().withSeparator(separator)
				.build();
		CSVReader csvReader = new CSVReaderBuilder(new FileReader(filePath))
				.withCSVParser(parser).build();

		// Usa CSVReader dalla libreria OpenCSV per salvare i dati sull'array
		try (csvReader) {
			String[] values = null;
			while ((values = csvReader.readNext()) != null) {
				records.add(Arrays.asList(values));

			}
		}
		return records;
	}
}

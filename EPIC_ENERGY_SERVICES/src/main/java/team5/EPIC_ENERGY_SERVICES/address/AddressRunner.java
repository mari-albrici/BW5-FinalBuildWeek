package team5.EPIC_ENERGY_SERVICES.address;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import team5.EPIC_ENERGY_SERVICES.municipality.Municipality;
import team5.EPIC_ENERGY_SERVICES.municipality.MunicipalityRepository;

@Component
public class AddressRunner implements CommandLineRunner {

	@Autowired
	MunicipalityRepository municipalityRepo;

	@Override
	public void run(String... args) throws Exception {

		// Se la tabella su db e vuota, viene popolata
		if (municipalityRepo.findAll().isEmpty()) {
			// Il primo record contiene il nome delle colonne:
			// ("﻿Codice Provincia", "Progressivo del Comune", "Denominazione in
			// italiano")
			// Quindi la prima riga deve essere saltata
			boolean firstMunicipalityRecord = true;

			// Richiame il metodo 'csvToArray che ho creato io per leggere il
			// csv
			List<List<String>> municipalities = csvToArray(
					"src/main/java/team5/EPIC_ENERGY_SERVICES/municipality/csv/comuni-italiani.csv",
					';');

			// Anche sul secondo file
			List<List<String>> provinces = csvToArray(
					"src/main/java/team5/EPIC_ENERGY_SERVICES/municipality/csv/province-italiane.csv",
					';');

			Municipality municipality = new Municipality();

			for (List<String> m : municipalities) {

				// Se è il primo record viene omesso il salvataggio su db
				if (firstMunicipalityRecord) {
					firstMunicipalityRecord = false;
					continue;
				}

				municipality.setProvinceNumber(Long.parseLong(m.get(0)));
				municipality.setMunicipalityNumber(m.get(1));
				municipality.setName(m.get(2));
				municipality.setProvinceName(m.get(3));

				municipalityRepo.save(municipality);

				boolean firstProvinceRecord = true;

				for (List<String> p : provinces) {

					// Se è il primo record viene omesso il salvataggio su db
					if (firstProvinceRecord) {
						firstProvinceRecord = false;
						continue;
					}

					String initials = p.get(0);
					String province = p.get(1);
					String region = p.get(2);

					// Cerco la corrispondenza
					if (m.get(3).equals(p.get(1))) {

						// Aggiorno il valore degli attributi
						municipality.setRegion(region);
						municipality.setInitials(initials);

					}

				}

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

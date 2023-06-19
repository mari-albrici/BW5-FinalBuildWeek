package team5.EPIC_ENERGY._ERVICES.address;

import java.io.FileReader;
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

import team5.EPIC_ENERGY._ERVICES.municipality.Municipality;
import team5.EPIC_ENERGY._ERVICES.municipality.MunicipalityRepository;

@Component
public class AddressRunner implements CommandLineRunner {

	@Autowired
	MunicipalityRepository municipalityRepo;

	@Override
	public void run(String... args) throws Exception {

		// Carica e legge primo file csv (comuni)
		// Crea un nuovo Array che contiene liste di String
		List<List<String>> records = new ArrayList<List<String>>();

		// I file csv dati da Epicode usano ';' come separatore invece di ','
		CSVParser parser = new CSVParserBuilder().withSeparator(';').build();
		CSVReader csvReader = new CSVReaderBuilder(new FileReader(
				"src/main/java/team5/EPIC_ENERGY/_ERVICES/municipality/csv/comuni-italiani.csv"))
				.withCSVParser(parser).build();

		// Usa CSVReader dalla libreria OpenCSV per salvare i dati sull'array
		try (csvReader) {
			String[] values = null;
			while ((values = csvReader.readNext()) != null) {
				records.add(Arrays.asList(values));

			}
		}

		// Se la tabella su db e vuota, viene popolata
		if (municipalityRepo.findAll().isEmpty()) {
			// Il primo record contiene il nome delle colonne:
			// ("﻿Codice Provincia", "Progressivo del Comune", "Denominazione in
			// italiano")
			// Quindi la prima riga deve essere saltata
			boolean firstRecord = true;

			for (List<String> record : records) {

				// Se è il primo record viene omesso il salvataggio su db
				if (firstRecord) {
					firstRecord = false;
					continue;
				}

				Municipality municipality = new Municipality();
				municipality.setProvinceNumber(Long.parseLong(record.get(0)));
				municipality
						.setMunicipalityNumber(Long.parseLong(record.get(1)));
				municipality.setName(record.get(2));
				municipality.setProvinceName(record.get(3));

				municipalityRepo.save(municipality);

			}
		}

	}

}

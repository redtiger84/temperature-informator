package com.example.largefilereadingchallenge.repository;

import com.example.largefilereadingchallenge.exception.DataFileException;
import java.util.Set;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
class CSVFileReaderTest {

    @Autowired
    private CSVFileReader csvFileReader;


    @Test
    void shouldReadAllCitiesWithoutDuplicatesAndDoesNotThrowExceptions() throws DataFileException {
        Set<String> cities = csvFileReader.extractCities();
        Assertions.assertThat(cities).isNotEmpty();
        Assertions.assertThat(cities)
            .hasSize(3)
            .containsOnly("Warszawa", "Kraków", "Gdańsk");
    }

    @Test
    void shouldThrowDataFileException() throws DataFileException {
        CSVFileReader failedCsvFileReader = new CSVFileReader(null);
        Assertions.assertThatThrownBy(() -> failedCsvFileReader.extractCities())
            .isInstanceOf(DataFileException.class)
            .hasMessageContaining("invalid_input_data");
    }

//    @Test
//    void readCSVFile() {
//    }
}
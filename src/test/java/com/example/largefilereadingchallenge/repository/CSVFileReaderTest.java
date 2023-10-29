package com.example.largefilereadingchallenge.repository;

import com.example.largefilereadingchallenge.exception.DataFileException;
import java.util.Map;
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
    void shouldReadAllCitiesWithoutDuplicatesAndDoesNotThrowExceptions() {
        Assertions.assertThatCode(() -> {
                Set<String> cities = csvFileReader.extractCities();
                Assertions.assertThat(cities).isNotEmpty();
                Assertions.assertThat(cities)
                    .hasSize(3)
                    .containsOnly("Warszawa", "Kraków", "Gdańsk");
            })
            .doesNotThrowAnyException();
    }

    @Test
    void shouldThrowDataFileExceptionForCityExtractMethod() {
        CSVFileReader failedCsvFileReader = new CSVFileReader(null);
        Assertions.assertThatThrownBy(failedCsvFileReader::extractCities)
            .isInstanceOf(DataFileException.class)
            .hasMessageContaining("invalid_input_data");
    }

    @Test
    void shouldReadAverageTemperatureForAllCities() {
        Map<Integer, Double> warszawaResults = Map.of(
            2018, 24.5,
            2019, 7.59,
            2020, 23.28,
            2021, 19.36,
            2022, 17.22,
            2023, 11.3
        );
        Map<Integer, Double> gdanskResults = Map.of(
            2018, 25.84,
            2019, 20.85,
            2020, 4.05,
            2021, -8.66,
            2022, 15.6,
            2023, 11.24
        );
        Map<Integer, Double> krakowResults = Map.of(
            2018, 13.49,
            2019, -9.07,
            2020, 14.26,
            2021, 34.88,
            2022, 37.06,
            2023, -3.4
        );

        Assertions.assertThatCode(() -> {
            checkResults(warszawaResults, "Warszawa");
            checkResults(gdanskResults, "Gdańsk");
            checkResults(krakowResults, "Kraków");
        }).doesNotThrowAnyException();


    }

    private void checkResults(Map<Integer, Double> cityResults, String cityName) throws DataFileException {
        Map<Integer, Double> result = csvFileReader.readCSVFile(cityName);
        Assertions.assertThat(result).isNotEmpty().hasSize(cityResults.size())
            .containsExactlyInAnyOrderEntriesOf(cityResults);
    }

    @Test
    void shouldThrowDataFileExceptionForReadCsvFileMethod() {
        CSVFileReader failedCsvFileReader = new CSVFileReader(null);
        Assertions.assertThatThrownBy(() -> failedCsvFileReader.readCSVFile(null))
            .isInstanceOf(DataFileException.class)
            .hasMessageContaining("invalid_input_data");
    }

    @Test
    void shouldReturnEmptyListForNonExistingCity() {
        Assertions.assertThatCode(() -> {
            Map<Integer, Double> result = csvFileReader.readCSVFile(null);
            Assertions.assertThat(result).isEmpty();
        }).doesNotThrowAnyException();

    }
}
package com.example.largefilereadingchallenge.repository;

import com.example.largefilereadingchallenge.exception.DataFileException;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CSVFileReader implements TemperatureReader {

    private static final String DATA_SEPARATOR = ";";

    private final Path pathToInputFile;

    @Autowired
    public CSVFileReader(Path csvFilePath) {
        this.pathToInputFile = csvFilePath;
    }

    @Override
    public Set<String> extractCities() throws DataFileException {
        if (pathToInputFile == null) {
            throw new DataFileException();
        }

        try (Stream<String> lines = java.nio.file.Files.lines(pathToInputFile)) {
            return lines
                .filter(Objects::nonNull)
                .skip(1)
                .map(line -> line.split(DATA_SEPARATOR)[0])
                .filter(city -> !city.isEmpty())
                .collect(Collectors.toSet());
        } catch (IOException ex) {
            throw new DataFileException();
        }
    }

    @Override
    public Map<Integer, Double> readCSVFile(String city) throws DataFileException {
        Map<Integer, List<Double>> temperatureMap = new HashMap<>();

        try (Stream<String> lines = java.nio.file.Files.lines(pathToInputFile)) {
            lines
                .skip(1) // Skip the header line
                .filter(line -> line.startsWith(city + DATA_SEPARATOR))
                .forEach(line -> {
                    String[] parts = line.split(DATA_SEPARATOR);
                    String[] dateParts = parts[1].split(" ")[0].split("-");
                    int year = Integer.parseInt(dateParts[0]);
                    double temperature = Double.parseDouble(parts[2]);

                    temperatureMap.computeIfAbsent(year, k -> new ArrayList<>()).add(temperature);
                });
        } catch (IOException ex) {
            throw new DataFileException();
        }

        return temperatureMap.entrySet().stream()
            .collect(Collectors.toMap(
                Map.Entry::getKey,
                entry -> entry.getValue().stream().mapToDouble(Double::doubleValue).average().orElse(0.0)
            ));
    }
}

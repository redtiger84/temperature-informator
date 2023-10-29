# Temperature Information

## Description

This solution provides an endpoint `/temperatures` with two REST methods:
- The first method retrieves all cities from a file. 
  - It is possible to filter and limit the number of cities using the following command
`
    curl --location 'http://localhost:8080/temperatures/city?term=w&limit=2'

- The second method retrieves the years and average temperatures for a specified city
  - The average temperature is rounded to 2 decimal places using the following command:
`
    curl --location 'http://localhost:8080/temperatures/Warszawa' 
`
## Webpage

- The homepage allows you to enter a city name and retrieve the average temperature
  - Access it at: http://localhost:8080/

## Assumptions

- Caching is enabled for the method that retrieves all cities from a file
  - Time-to-Live (TTL) is set to 5 minutes

## Logger

- Memory usage is logged when data is read from the CSV file. The log level is set to debug.

## Errors

- A 500 error is thrown when there is a problem with the CSV file.
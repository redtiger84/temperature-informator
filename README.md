# Temperature Information

## Description

This solution provides an endpoint `/temperatures` with two REST methods:

- GET http://localhost:8080/temperatures/{city}
    - Returns temperature information for the selected city.
- GET http://localhost:8080/temperatures/city
    - Returns a list of all available cities.

## Interface

- Homepage (Authentication required by login and password).
- API endpoint (API key required).

## Technologies Used

- Security (To-Do)
- Cache (To-Do)
- Swagger (To-Do)

## Security

This is an example of security, where login, password, and API key are static.

- Login
- Password
- API key

### Assumptions

- Cache
- Time-to-Live (TTL): 5 minutes

### Logger

- logging of used memory and numbers of read data

### Errors
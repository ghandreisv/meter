# Meter Readings
The solution is build as a Spring Boot application:
* server address: `http://localhost:8080/api`
* persistence: H2 db in memory
* db initialization: 
  * flyway scripts at `src/main/resources/db/migration`
  * initial data include:
    * 2 clients, respective 2 addresses and meters;
    * 12 meter readings for 2019 and 2020;
* security: none

### Run using Maven Spring Boot plugin
* Start application by running in project root: `mvn spring-boot:run`

### Build using Maven & run using Java 
* Build the project jar file by running in project root: `mvn clean package`
* Run by executing: `java -jar target/meter-0.0.1-SNAPSHOT.jar`

### Usage
### Reports generation
* in order to calculate readings report send a GET request to 
  `http://localhost:8080/api/report`;
* in order to provide report attributes use `json` parameter;
* the parameter should be the JSON URL encoded representation of the bellow structure:
```json
{
  "year": int, // mandatory
  "month": int, // optional
  "detailed": boolean // optional, default false
}
```
* example, request a detailed report for 2020-01: 
  * non URL encoded `{ "year": 2020, "detailed":true, "month":1}`
  * actual request: `http://localhost:8080/api/report?json=%7B%20%22year%22%3A%202020%2C%20%22detailed%22%3Atrue%2C%20%22month%22%3A1%7D`

### Readings reporting
* send a PUT request to `http://localhost:8080/api/report/readings`;
* controller expect a DTO of structure:

```json
{
  // at least one id should be set
  "meterId": String,
  "addressId": String,
  "clientId": String, 
  "date": YearMonth // not null, ex. '2020-02'
  "value": Long // not null
}
```

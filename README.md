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
#### Yearly Reports
* endpoint URL `http://localhost:8080/api/reports/yearly`
* query parameters:
  * `year`: mandatory, number;
  * `detailed`: optional, boolean, default `false`
* sample request: `http://localhost:8080/api/reports/yearly?year=2020&detailed=false`
* sample response:
```json5
{
  "year": 2020,
  "total": 159
}
```
#### Monthly Reports
* endpoint URL `http://localhost:8080/api/reports/monthly`
* query parameters:
  * `year`: mandatory, number;
  * `month`: mandatory, month number or name e.g. `1` or `january`
* sample request: `http://localhost:8080/api/reports/monthly?year=2020&month=january`
* sample response:
```json5
{
  "year": 2020,
  "month": "JANUARY",
  "value": 23
}
```

### Adding a new meter readings
* send a PUT request to `http://localhost:8080/api/meter-readings`;
* the request payload is expected to be a JSON structure specifying:
  * meter identification specified by one of the bellow identifiers in the order of listing (at least one should be present): 
    * meter id;
    * address id;
    * client id.
  * reading month as year month value e.g. "2021-01"
  * reading value, expected an integer value
* the response contains the id of newly created meter reading record; 
* sample request payload:
```json5
{
  "addressId":"0ad46436-532d-4bfb-92e6-654e036b6cda",
  "yearMonth":"2021-02",
  "value": 11
}
```
* sample response: `4626c103-058f-4fbf-bf46-ef2e1dd613e7`

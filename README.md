# American Express - Giftcard REST API

### Description

The purpose of this API is to provide endpoints for Amex members to manage Giftcards

### Software pre-reqiuistes

The following software will be required to run this application:

* Gradle 8.8
* Java 17
* GIT
* Postgres DB (with default postgres user)
* pgAdmin 4 (optional)
* Intellij IDE

### Build & Run REST Application

To run this software please perform the following steps:

* Git clone the repo with following command: git clone git@github.com:DavidKnight46/amexm3test.git
* Navigate to root directory of repo (where buildScript.sh is located)
* Run ./buildScript.sh
* Enter password setup for postgres user when prompted
* Optional test endpoints using AmexRESTEndpoints.http in Intellij

### Limitations

There are a number of limitations to this API, including:

- Currently can only add/create one GiftCard ID per HTTP request
- Currently can only find one with given ID per HTTP request

### Possible improvements

There are a number of improvements that could be made to this API, including:

- Swagger/OpenAPI implementation
- Assign GiftCards to specific members
- Consider using Cloud solutions for Database/Deployment
- CI/CD build pipeline

### License

Property of American Express

### Author

David Knight

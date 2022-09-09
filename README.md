# personio-challenge
REST API for managing a company’s hierarchy

 # Getting started

 ## Build and start the application using docker
 ``` shell
 $ make up
 $ make docker-run
 ```

 Now you can go to http://localhost:8030/health-check to see is everything is OK

 You can run the tests in docker to ensure everything is working fine.

 ``` shell
 $ make docker-test
 ```

## Steps for building the application from scratch (Not necessary)

``` shell
 $ make build
 $ make run
 $ make test
 ```

 # Testing with postman

 You can find the postman collection in PersonioAPI.postman_collection.json

 # What can be done more

- Add more tests. Some unit tests are missing.
- Error handling can be improved. Custom error classes as part of the Domain.
- Status codes are not descriptive enought.As well as the error messages from the server.
- Authorization credentials are hardcoded.
- The application do not check if there is another entry of employee in the database with the same name. Data duplicated.
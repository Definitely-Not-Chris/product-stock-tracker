# Product Stock Tracker – Junior Developer Coding Challenge 


### How to build: 
    ./gradlew build
### How to run: 
    ./gradlew run

### How to test: 
    ./gradlew test

### Configurations: 
    ./src/main/resources/application.properties
    host: localhost
    port: 8081

### Technologies: 
    Java 11
    Spring Boot 2.4.1
    Spring Data JPA 2.4.1
    Spring Web 5.3.1
    H2 in-memory database


## API Documentation:

### How to Upload File in Application:
#### POST http://localhost:8081/api/v1/products/upload
    - This api accepts a multipart file in form data.
    - This api should only accepts a csv file and it throws if empty.
    - This api should return the saved list of products.

    Request Form Data:
    {
        "file": 
    }

    Sample Response:
    {
        "message": "File uploaded successfully",
        "products": [
            {},
            {}
        ]
    }

### How to Upload File in Application:

#### POST http://localhost:8081/api/v1/products/upload-from-device
    - This api accepts a path to a csv file in the request body.
    - This api should throw when file is not found.
    - This api should only accepts a csv file and it throws if empty.
    - This api should return the saved list of products.

    Request Form Data:
    {
        "location": 
    }

    Sample Response:
    {
        "message": "File uploaded successfully",
        "products": [
            {},
            {}
        ]
    }


### GET http://localhost:8081/api/v1/products
    Sample Response:
    [
        {
            "id": 1,
            "name": "Product 1",
            "quantity": 100,
            "createdAt: "",
            "updatedAt": ""
        },
        {
            "id": 2,
            "name": "Product 2",
            "createdAt: "",
            "updatedAt": ""
        }
    ]

### GET http://localhost:8081/api/v1/products/1
    Sample Response:
    {
        "id": 1,
        "name": "Product 1",
        "quantity": 100,
    }


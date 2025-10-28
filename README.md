# Product Stock Tracker – Junior Developer Coding Challenge 

## Take note: 
- This application is for challenge only, I wouldn't commit stocks.csv and application.properties files in real project.
## If you want to test the import API:
- place your csv file under src/resources/static/temp/products folder
##### Sample CSV file: (Take note: system skips the first line for headers)
    sku,name,stockQuantity
    ABC123,Widget Pro,10
    XYZ456,Gadget Plus,0
    LMN789,Tool Set,25


## How to run the application: 
    ./gradlew bootRun

## Test the application using these curl commands:
### For detailed documentation, please refer to the API Documentation section below.
    //GET products
    curl http://localhost:8081/api/v1/products
    
    //Upload products from file
    curl --location 'http://localhost:8081/api/v1/products/upload' --form 'file=@"ABSOLUTE_PATH_TO_FILE_HERE"'
    
    //Import products from local file via path
    curl --location 'http://localhost:8081/api/v1/products/import' --header 'Content-Type: text/plain' --data 'CHANGE_PATH_TO_FILE_HERE'

***

## Used Technologies: 
    Java 11
    Spring Boot 2.4.1
    Spring Data JPA 2.4.1
    Spring Web 5.3.1
    H2 in-memory database

## Requirements:
    - Reads product stock data from a CSV file
    - Saves the product data to a database
    - Provides a REST API endpoint to view the products 
    - Create atleast 2 unit test for service
    - Logs a message when a product has zero stock

## Any assumptions or design decisions I made:
    - I have created an api to upload actual csv file
    - I have used java standard library to read csv file
    - I have returned the saved list of products after uploading and importing csv file
    - I have handle duplicated products and update if its already exists
    - I have added timestamp to product entity for better logging

## What I would improve with more time:
    - I would have separate the product and stock data into different tables for database normalization
    - I would have create product mapper class for it to be not tightly coupled with product entity and service
    - I would have create a DTO class for request and response
    - I would have create a test for duplicated products

## 
***

## API Documentation:

### How to Upload File in Application:
#### POST http://localhost:8081/api/v1/products/upload
    - This api accepts a multipart file in form data.
    - This api should only accepts a csv file and it throws if empty.
    - This api should return the saved list of products.
    - If product sku is already in db, it should update the existing product and It should not create a new product.

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

### How to Import Local File in Application Via Path:

#### POST http://localhost:8081/api/v1/products/import
    - This api accepts a path to a csv file in your device.
    - This api should throw when file is not found.
    - This api should only accepts a csv file and it throws if empty.
    - This api should return the saved list of products.
    - If product sku is already in db, it should update the existing product and It should not create a new product.
    - If you want to test this api, place your csv file under src/resources/static/temp/products folder.

    Content-Type: text/plain
    Request Body: /path/to/file/products.csv

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

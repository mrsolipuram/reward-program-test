### Rewards Calculation
---
**Description:** A retailer offers a rewards program to its customers, awarding points based on each recorded purchase.
- A customer receives 2 points for every dollar spent over $100 in each transaction, plus 1 point for every
dollar spent over $50 in each transaction
- (e.g. a $120 purchase = 2x$20 + 1x$50 = 90 points).

```xml
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>

    <dependency>
        <groupId>com.h2database</groupId>
        <artifactId>h2</artifactId>
        <scope>runtime</scope>
    </dependency>
    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
        <version>1.18.24</version>
        <optional>true</optional>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>
```
### Steps to run the application
- Step1: To build the application
```agsl
mvn clean install
```
- Step2: To run the applicaion
```agsl
java -jar rewards-0.0.1-SNAPSHOT.jar
```
### API to test
**1.CREATE TRANSACTION:**

- URL: http://localhost:8080/createTransaction
- Method: POST
- Sample Request Body
```agsl
{
  "customerId":"1234",
  "amount":102.00,
  "transactionDate": "2022-01-17T00:53:30"
}
```
**2.POINTS SUMMERY:**

- URL: http://localhost:8080/getCustomerSummery/{customerId}
- Method: GET
- Sample Response
```agsl
{
    "customerId": "1234",
    "totalPoints": 216,
    "pointsSummery":{
        "2022-3": 54,
        "2022-1": 108,
        "2022-2": 54
    }
}
```
**3.POINTS HISTORY:**

- URL: http://localhost:8080/history/{customerId}
- Method: GET
- Sample Response
```agsl
[
    {
        "id": 1,
        "customerId": "1234",
        "amount": 102.00,
        "createDate": "2023-01-17T10:17:16.000514",
        "transactionDate": "2022-01-17T00:53:30",
        "points": 54
    },
    {
        "id": 2,
        "customerId": "1234",
        "amount": 102.00,
        "createDate": "2023-01-17T10:17:21.780305",
        "transactionDate": "2022-02-17T00:53:30",
        "points": 54
    }
]
```
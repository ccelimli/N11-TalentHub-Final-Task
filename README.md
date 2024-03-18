# N11 TalentHub

#### ``` You can find the UNIT AND INTEGRATION TESTS of the relevant services under the test folder of the service. ```


This repository contains three separate Spring projects: User Review Service, User Service, and Restaurant Service. Each is designed to manage user reviews, process user information, and provide restaurant details, respectively.

## About the Projects

### User Review Service
This service allows users to make comments and rate restaurants. It is accessible via REST API and integrated with a database to store reviews. It also offers restaurant recommendations within a user-defined distance.

### User Service
Designed to manage user information, it supports operations such as user registration, updates, and deletion. It is equipped with security and verification mechanisms.

### Restaurant Service
Designed to manage user information, it supports operations such as user registration, updates, and deletion. It is equipped with security and verification mechanisms.
## BGetting Started

To run the projects in your local environment, follow these steps:


### 1. Clone the repository:
```
git clone https://github.com/ccelimli/N11-TalentHub-Final-Task.git
```
### 2. Navigate to each project’s directory and install dependencies:
```
cd user-review-service mvn install
```
```
cd user-service mvn install
```
```
cd restaurant-service mvn install
```
### 3. Start each project separately:
```
mvn spring-boot:run
```

For more information and assistance, refer to each project’s own README.md file.

## User Service Dockerized
First, navigate to the root directory of the project. Then, execute the following command to Dockerize the API project using the Dockerfile.
```shell
    docker build -t user-service:0.0.1 .
```

## User Review Service Dockerized
First, navigate to the root directory of the project. Then, execute the following command to Dockerize the API project using the Dockerfile.
```shell
    docker build -t user-review-service:0.0.1 .
```

## Restaurant Service Dockerized
First, navigate to the root directory of the project. Then, execute the following command to Dockerize the API project using the Dockerfile.
```shell
    docker build -t restaurant-service:0.0.1 .
```

 After Dockerizing all services, bring up all services using the compose file in the root directory of the main project.
```shell
    docker-compose up 
```
To stop the services:
```shell
    docker-compose down 
```


## License

These projects are licensed under the MIT license.

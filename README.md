# Reference Documentation

#  Frameworks and language Used

* SpringBoot
* Spring MVC
* Spring Data JPA
* H2 In-Memory Database
* Maven Build Tool
* Core Java 17
* Postman

#  Data Flow

* Controller -

  it Will receive request from client and send request to service layer.

* Service -

  it Will receive request from controller and this will manipulate the operation and send send request to DAO layer

* Repository -

  it Will receive request from service and it will communicate with the Database.

* DataBase Design -

  For Database i am using H2(In-Memory DB) and created a pogo for Job management where i performing CRUD
  operation with user basic information.

#  Data Structure used in project

* DataBase Design -

  In this project mostly used ArrayList & along with java 8 feature like optional class and
  lambda expression.


#  Project Summary


* In Job management i created a Rest APIs with the help of spring boot where we can track record of
  of user and status related to the   organizing.

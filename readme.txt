
To execute JAR file :-

1. Executable jar file can be found under /rest/target/ with name rest-0.0.1.jar .

2. First go to jar path and run this jar using command  java -jar rest-0.0.1.jar .

For Database setup :- 

3. A sql script named schema.sql is also placed in rest folder for database and required table creation.

4. This rest api project uses postgresql database for storing data locally.

Steps for running schema.sql file:-

a. exceute command psql -U postgres
b. run following sql command :-
	\i /rest/schema.sql


5. An application.properties file is placed under folder rest/config/application.properties , which contains the database related configuration. following are the details of application.properties file...


spring.datasource.driverClassName=org.postgresql.Driver
spring.datasource.url=jdbc:postgresql://127.0.0.1/contact
spring.datasource.username=postgres
spring.datasource.password=postgres

a. spring.datasource.driverClassName contains driver class name for postgres db.
b. spring.datasource.url contains url of database. here contact is the name of database.
c. spring.datasource.username contains username and spring.datasource.password contains of db. if you have another username or password, please modify the values.

API:-

This project contains 3 APIs for inserting, updating and retrieving contact information.

http://127.0.0.1:8080/rest/contacts with Get method to retrieve all contacts.

http://127.0.0.1:8080/rest/contacts with Post method to insert the contact.

http://127.0.0.1:8080/rest/contacts/1 with Put method to update the information of the contact for a particular id, that is given in path variable.







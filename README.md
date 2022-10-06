# SmartHome

a java web project based on JavaScript and Servlet, deployed on Tomcat

- The project was programmed with Eclipse as IDE and Maven as packet manager. Before compling, one should import the `smarthome.mwb` into MySQL Workbench and change the `druid.properties` in `java/main/resources` to correctly link to the SQL database.
- One should use Tomcat 9.0 or older since the code imported `javax` instead of `jakarta`.

### Front-end Structure

<img width="416" alt="image" src="https://user-images.githubusercontent.com/69375071/194193386-18825c61-89d8-4e07-96ba-a81a4f2af68d.png">

### Back-end Structure

<img width="416" alt="image" src="https://user-images.githubusercontent.com/69375071/194193461-a0b9edcd-e9c8-49d7-8ca4-acb0ed9add9e.png">

### Dependencies

- javax.servlet-api:4.0.1
- junit-jupiter-api:5.7.2
- junit-jupiter-engine:5.7.2
- mysql-connector-java:8.0.26
- druid:1.2.6
- commons-logging:1.2
- spring-core:5.3.9
- spring-jdbc:5.3.9
- spring-tx:5.3.9
- spring-beans:5.3.9
- fastjson:1.2.78
- urlrewritefilter:4.0.4

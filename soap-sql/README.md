
# Movies Service based on SOAP API and SQL database

## Abstract
SOAP API service which share endpoint with data from IMDB

## Technologies
- Java
- Spring
- MySQL
- Docker

## Installation
For installation process execute commands below

### Download the data from IMDB website
* Download this project
* [Download IMDB database](https://datasets.imdbws.com/title.basics.tsv.gz)
* Unzip and put data.tsv to folder /src/main/resources/

### Fill the database with data
* mysql> create database mydatabase; -- Creates the new mySQL database
* mysql> create user 'newuser'@'%' identified by 'password'; -- Creates the user
* mysql> grant all on db_example.* to 'newuser'@'%'; -- Gives all privileges to the new user on the newly created database

### Build the project
* $ mvn install; -- Install project
* $ java -jar target/cinema-soap-web-service-producing-0.1.jar -Ddatabase.create=true -Ddatabase.records=500; -- run application, create table "Movie" and load 500(defoult 100) record 
* if you want to save your changes, next time run app without flag

## Tutorial
Post methods by XML are available under http://localhost:8080/ws. You can see some examples be;pw 

### Get all movies 
Send POST request to http://localhost:8080/ws 
```$xml
<Envelope xmlns="http://schemas.xmlsoap.org/soap/envelope/">
    <Body>
        <getAllMovieRequest xmlns="http://CinemaSevice.com/SoapWebServicesProducing">
        </getAllMovieRequest>
    </Body>
</Envelope>
```
### Add new movie
Send POST request to http://localhost:8080/ws with data below
```xml
<Envelope xmlns="http://schemas.xmlsoap.org/soap/envelope/">
    <Body>
        <addMovieRequest xmlns="http://CinemaSevice.com/SoapWebServicesProducing">
          <titleType>short</titleType>
          <primaryTitle>Carmencita</primaryTitle>
          <originalTitle>Carmencita</originalTitle>
          <isAdult>false</isAdult>
          <startYear>1894</startYear>
          <endYear>0</endYear>
          <runtimeMinutes>1</runtimeMinutes>
          <genres>Documentary,Short</genres>
        </addMovieRequest>
    </Body>
</Envelope>
```
### Modify movies
Send POST request with command below http://localhost:8080/ws
```xml
<Envelope xmlns="http://schemas.xmlsoap.org/soap/envelope/">
     <Body>
         <editMovieRequest xmlns="http://CinemaSevice.com/SoapWebServicesProducing">
           <id>1</id>
           <titleType>short</titleType>
           <primaryTitle>New title</primaryTitle> <!-- New title  -->
           <originalTitle>Carmencita</originalTitle>
           <isAdult>false</isAdult>
           <startYear>1894</startYear>
           <endYear>0</endYear>
           <runtimeMinutes>1</runtimeMinutes>
           <genres>Documentary,Short</genres>
         </editMovieRequest>
     </Body>
 </Envelope>
```

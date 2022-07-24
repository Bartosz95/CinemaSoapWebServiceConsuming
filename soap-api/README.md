# Simple SOAP Service

## If you want to test this service you need to:
### 1 Download
* Download this project
* [Download IMDB database](https://datasets.imdbws.com/title.basics.tsv.gz)
* Unzip and put data.tsv to folder /src/main/resources/

### 2 - Created 
* mysql> create database mydatabase; -- Creates the new mySQL database
* mysql> create user 'newuser'@'%' identified by 'password'; -- Creates the user
* mysql> grant all on db_example.* to 'newuser'@'%'; -- Gives all privileges to the new user on the newly created database

### 3 - Run
* $ mvn install; -- Install project
* $ java -jar target/cinema-soap-web-service-producing-0.1.jar -Ddatabase.create=true -Ddatabase.records=500; -- run application, create table "Movie" and load 500(defoult 100) record 
* if you want to save your changes, next time run app without flag

### 4 - Test
Post method by XML in URL http://localhost:8080/ws. Example: 
#### Get all record 
* Method: POST
* URL: http://localhost:8080/ws
```$xml
<Envelope xmlns="http://schemas.xmlsoap.org/soap/envelope/">
    <Body>
        <getAllMovieRequest xmlns="http://CinemaSevice.com/SoapWebServicesProducing">
        </getAllMovieRequest>
    </Body>
</Envelope>
```
#### Add record
* Method: POST
* URL: http://localhost:8080/ws
##### XML CODE:
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
#### Change record ####
* Method: POST
* URL: http://localhost:8080/ws
##### XML CODE:
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
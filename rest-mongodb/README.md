# Movies Service
## Necessary tools:
* Maven
* docker-compose
# Installation process:
## For installation run command below
 [Download](https://github.com/Bartosz95/movies-rest-spring-mongodb/archive/master.zip) and unzip or clone repository from github:
```shell script
git clone https://github.com/Bartosz95/movies-rest-spring-mongodb.git
```
[Download IMDB database](https://datasets.imdbws.com/title.basics.tsv.gz) and unzip and put `data.tsv` to folder `movies-rest-spring-mysql/src/main/resources/static/`

Change directory to movies-rest-spring-mongodb/ :
```shell script
movies-rest-spring-mysql/src/main/resources/static/
```
Change directory to movies-rest-spring-mongodb/ :
```shell script
cd movies-rest-spring-mongodb/
```
Build jar file in three module:
```shell script
mvn clean install
```
Build docker images and run containers: 
```shell script
docker-compose up
```
#### If throw error:
Tip 1 : stop container, clean all containers and remove image 
```shell script
docker stop movies-producing-mongodb mongo-db
docker docker container prune
docker image rm movies-producing-mongodb
```
Tip2:
Make sure if you have free ports 8080 and 27017 if not change ports in docker-compose.yml
## Use application
You can communicate with this web service for example by [POSTMAN](https://www.getpostman.com/) program. Use Get, Post, Delete method by JSON in URL http://localhost:8080/api/v1/movies. Example: 
#### Create all record
Method:
```html
POST
```
URL address:
```html
http://localhost:8080/api/v1/movies/reset
```
Headers parameter:
```html
Content-Type: application/json
```
#### Get all record
For get all credits go to [page](http://http://localhost:8080/api/v1/movies) or send message looks like:
Method:
```html
GET
```
URL address:
```html
http://localhost:8080/api/v1/movies
```
#### Add record
Method:
```html
POST
```
URL address:
```html
http://localhost:8080/api/v1/movies
```
Headers parameter:
```html
Content-Type: application/json
```
Body message:
```json
{
"titleType": "short",
"primaryTitle": "New title",
"originalTitle": "New title",
"startYear": 2019,
"endYear": 0,
"runtimeMinutes": 10,
"genres": "Animation,Short",
"adult": false
}
```
#### Change record ####
Method:
```html
POST
```
URL address:
```html
http://localhost:8080/api/v1/movies/2
```
Headers parameter:
```html
Content-Type: application/json
```
Body message:
```json
{
"id": 2,
"titleType": "short",
"primaryTitle": "New title",
"originalTitle": "New title",
"startYear": 1892,
"endYear": 1893,
"runtimeMinutes": 5,
"genres": "Animation,Short",
"adult": false
}
```
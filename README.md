# Bank Application 
Desktop bank app created with Angular, Spring Boot and MySql. It's main purpose is to simulate bank operations. 

## Technologies
* Bootstrap 5
* Angular 
* Java
* Spring Boot
* MySql

## How to start docker image 
1. docker pull mysql
2. docker run -p 3306:3306 --name mysqlcontainerbank -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=bank -d mysql
3. docker network create networkmysql
4. network connect networkmysql mysqlcontainerbank
5. docker run -p 8080:8080 --name bankcontainer --net networkmysql -e PROD_DB_HOST=mysqlcontainerbank -e PROD_DB_PORT=3306 -e PROD_DB_NAME=bank -e PROD_DB_USERNAME=root -e PROD_DB_PASSWORD=root bankapi

## Example of use
# The entire file is located in main folder : Bank-api.postman_collection.json
* Getting token for sending requests :
* By Registration
![image](https://github.com/GebratenesOmelett/Bank-Project/assets/78979897/5af07cb0-6d4f-4591-902f-230fdb04b5ef)
![image](https://github.com/GebratenesOmelett/Bank-Project/assets/78979897/16f979eb-8471-4b4b-9be4-f79b69dc6c09)
* By Login
![image](https://github.com/GebratenesOmelett/Bank-Project/assets/78979897/3de907de-6890-4948-9940-37d5254a96a4)
![image](https://github.com/GebratenesOmelett/Bank-Project/assets/78979897/6312b111-af1b-4a03-9a9b-6dc7298942d9)





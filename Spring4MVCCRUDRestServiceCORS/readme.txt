1)To build ->
mvn clean package

2)Deploy war to container of your choice. We have used tomcat 7.

3)Get all remainders
http://localhost:8082/RemainderRestService/remainder/

you can change server and port as it suits you.

4) Get specific remainder
http://localhost:8082/RemainderRestService/remainder/2

5)Create remainder-HTTP POST in SOAP UI

http://localhost:8082/RemainderRestService/remainder/

Request type text/json
{
   "id": 103,
   "name": "call pc 1001",
   "description": "call piku chau in morning1",
   "dueDate": "2090-07-03",
   "status": "Done"
}

6)Update Remainder - HTTP PUT in SOAP UI

http://localhost:8082/RemainderRestService/remainder/
{
   "id": 1,
   "name": "call pc 1001",
   "description": "call piku chau in morning1",
   "dueDate": "2090-07-03",
   "status": "Done"
}

7)Delete Ramainder -HTTP DELETE in SOAP UI
http://localhost:8082/RemainderRestService/remainder/1

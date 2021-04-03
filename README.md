# market
Market project.  


  
# Run:  
docker-compose -d up  
kafka ui:  
http://localhost:8085/  


## Postman Collection:  
[![Run in Postman](https://run.pstmn.io/button.svg)](https://app.getpostman.com/run-collection/32228080008f1f0134f7#?env%5Barticle-env%5D=W3sia2V5IjoiYXJ0aWNsZS1wYXRoIiwidmFsdWUiOiJodHRwOi8vbG9jYWxob3N0Ojg5ODkvYXJ0aWNsZSIsImVuYWJsZWQiOnRydWV9LHsia2V5Ijoib3JkZXItcGF0aCIsInZhbHVlIjoiaHR0cDovL2xvY2FsaG9zdDo4OTg5L29yZGVyIiwiZW5hYmxlZCI6dHJ1ZX0seyJrZXkiOiJwYXltZW50LXBhdGgiLCJ2YWx1ZSI6Imh0dHA6Ly9sb2NhbGhvc3Q6ODk4OS9wYXltZW50IiwiZW5hYmxlZCI6dHJ1ZX1d)
  
  
## TODO:  
Unit tests(Junit5), it(wiremock), Authorization(Token), Add unsuccessfull states to order, Spring state machine(for order states, not sure), Jaeger
  
  
## Путь Order:  
1 order-service сохраняет Order с OrderCreatedState
2 orded-service пушит ивен в кафку  
3 article-inventory берет ивент и резервирует товар  
4 в случае успеха пушит OrderConfirmedEvent  
5 payment-service уменьшает баланс  
6 в случае успеха пушит OrderCompletedEvent  
7 order-service слушает все изменения состояния заказа и обновляет state в бд(нужен отдельный мс наверное, а логику создания заказа вынести в orchestrator, но хотелось реализовать паттерн хореографии)  
  
Список квизов:  
![alt text](images/image1.jpg)  
  
  
## Unit Tests:  
Junit5, Mockito - in Order Service
  
## Integrational Tests:  
Wiremock - in Order Service

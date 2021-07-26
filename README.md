# SpringBoot

### - Задача 2 - Продвинутый сервис авторизации.


* #### Запрос на добавление нового пользователя в БД:

```
    POST http://localhost:24001/reg  
    Content-Type: application/json  
    {
        "name" : "andrey",    
        "password" : "123456"
    }
```    

* #### Ответ на запрос добавления нового пользователя в БД:

```
    POST http://localhost:24001/reg
    
    HTTP/1.1 200 
    Content-Type: application/json
    Transfer-Encoding: chunked
    Date: Sun, 25 Jul 2021 16:45:54 GMT
    Keep-Alive: timeout=60
    Connection: keep-alive
    {
      "name": "andrey",
      "password": "123456",
      "userAuthorities": [
        "READ",
        "WRITE"
      ]
    }
    Response code: 200; Time: 162ms; Content length: 72 bytes
```

* #### Запрос на получение возможностей пользователя:

```
    GET http://localhost:24001/authorize?user=andrey&password=123456
```

* #### Ответ на запрос на получение возможностей пользователя:

```
    GET http://localhost:24001/authorize?user=andrey&password=123456
    HTTP/1.1 200
    Content-Type: application/json
    Transfer-Encoding: chunked
    Date: Sun, 25 Jul 2021 16:48:22 GMT
    Keep-Alive: timeout=60
    Connection: keep-alive
    [
        "READ",
        "WRITE"
    ]
    Response code: 200; Time: 40ms; Content length: 16 bytes

```

* #### Запрос на получение исключения InvalidCredentials:
  
```
    GET http://localhost:24001/authorize?user=andrey&password=
```

* #### Ответ на запрос на получение исключения InvalidCredentials:

```
    GET http://localhost:24001/authorize?user=andrey&password=
    HTTP/1.1 400
    Content-Type: application/json
    Content-Length: 30
    Date: Sun, 25 Jul 2021 16:51:23 GMT
    Connection: close
      User name or password is empty
    Response code: 400; Time: 36ms; Content length: 30 bytes
```

* #### Запрос на получение исключения UnauthorizedUser:

```
    GET http://localhost:24001/authorize?user=vadim&password=123456
```

* #### Ответ на запрос на получение исключения UnauthorizedUser:

```
    GET http://localhost:24001/authorize?user=vadim&password=123456
    HTTP/1.1 401
    Content-Type: application/json
    Content-Length: 18
    Date: Sun, 25 Jul 2021 16:53:34 GMT
    Keep-Alive: timeout=60
    Connection: keep-alive
      Unknown user vadim
    Response code: 401; Time: 29ms; Content length: 18 bytes
```

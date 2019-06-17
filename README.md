# Simple shop REST API v1.0.0

## Requirements
* Java 1.8
* Docker, can be downloaded from [Docker Hub](https://hub.docker.com)

## How to start the application

### Docker

1. Make sure that you have Docker installed.
2. if you are on *Linux / Mac OS* navigate to the projects folder <br>
    and type `sudo chmod 777 mvnw`
3. Type then `sudo chmod 777 run-with-docker.sh`
4. Execute`run-with-docker.sh`

### Java only

1. Navigate to the project folder.
2. if you are on *Linux / Mac OS* navigate to the projects folder <br>
    and type `sudo chmod 777 mvnw`
3. Type `./mvnw clean install`
4. Navigate to `/application/target`
5. Execute command `java -jar *.jar`

# Project modular structure

The Simple shop REST API is a multi module monolithic application.

## Main Modules

| Module | Description |
|------|--------|
| `application` | Contains the Main class as well as environment configurations. |
| `core` | Core features of the application. It has several submodules: *api, service, security, data* |
| `infrastructure` | Contains the shared infrastructure modules that are included in every feature module including core features |

## Core modules

| Module | Description |
|------|--------|
| `api` | Contains REST Controllers and DTOs. |
| `service` | Contains classes and interfaces for business logic. |
| `security` | Contains logic for authorization and password encryption. |
| `data` | Contains entity and repository classes. |

## Infrastructure modules

| Module | Description |
|------|--------|
| `infrastructure-common` | Contains the common logic and utility classes as well as abstractions for implementing the reusable components. |
| `infrastructure-security` | Contains base classes for authenticated user principal and JWT Tokens. |
| `infrastructure-data` | Contains dependencies for ORM and Database connectors. |

The following components diagram illustrates the modular structure of the <br> application.

![Components](./.gitdoc/img/components.png)

# How to interact with the API

To see all the possible
    endpoints go to <br> `http://localhost:28003/api/swagger-ui.html`


1. Create account by sending <br> 
    **HTTP POST REQUEST** to `/api/auth/user/register` endpoint.
    
    Request body should look like this:

    ```json
    {
        "firstName": "Marko",
        "lastName": "Vuckovic",
        "imageUrl": null,
        "password": "123",
        "userName": "marvuchko"
    }
    ```

    **HTTP GET RESPONSE** will look like this.

    ```json
    {
        "id": 2,
        "firstName": "Marko",
        "lastName": "Vuckovic",
        "userName": "marko",
        "password": "$2a$10$PN8Tnu2LEn/yPrd3Bd9wuuUib49EqzamGbtRvz2kLFk5L34IwewG.",
        "createdAt": "2019-06-17T13:27:25.817+0000",
        "updatedAt": "2019-06-17T13:27:25.817+0000"
    }
    ```

2. Log in to the system. To authenticate
   to the system send a <br>
   
   **HTTP GET REQUEST** to `/api/auth/login?username=<your_username>&password=<ypur_password>`

   There is a default admin user in the database with credentials<br>
   
   username: admin<br>
   password: admin<br>

   **HTTP GET RESPONSE** will look like this.

   ```json
    {
        "accessToken": "eyJhbGciOiJIUzM4NCJ9.eyJ1c2VyTmFtZSI6ImFkbWluIiwidXNlcklkIjoxLCJ1c2VyUm9sZXMiOlsiQURNSU4iXSwiZXhwIjoxNTYwNzgxMTc5fQ.TyaQH3yxs8cqW2_jZ2mV-b_RtUArRIOklf_02nekNco2a-s5apBV41w1M68troAk",
        "refreshToken": "eyJhbGciOiJIUzM4NCJ9.eyJ1c2VySWQiOjEsInVzZXJSb2xlcyI6WyJBRE1JTiJdLCJleHAiOjE1NjA3OTE1Nzl9.9L9Djsns0qdCBnMWusKkOCRp42fbs6b8nu82pQSDGII3kf6g4LbZCNHjLWr_Lp_C"
    }
   ```
    Both tokens will contain user credentials.

3. From now on you can interact with most endpoints. <br> Certain endpoints require you to have specific role, but you can see that <br> in the documentation.

    Let's for example fetch all the shop items from the database.
    Send a 

    **HTTP GET REQUEST** to `/api/shop/item?page=0&size=20`

    Add access token header as `Authorization: Bearer <your_access_token>`

    **HTTP GET RESPONSE** will look like this.

    ```json
        {
            "content": [
             {
                "id": 1,
                "name": "Coca Cola",
                "type": "GROCERIES",
                "description": "A drink.",
                "price": 9.5,
                "imageUrl": "https://target.scene7.com/is/image/Target/GUEST_1d1acfbf-d627-4cee-a1ba-0853508cc4ff?wid=488&hei=488&fmt=pjpeg",
                "createdAt": "2019-06-17T13:01:35.536+0000",
                "updatedAt": "2019-06-17T13:01:35.536+0000"
            },
             {
                "id": 2,
                "name": "Iphone X",
                "type": "OTHER",
                "description": "Newest model of Apple mobile phone.",
                "price": 1045,
                "imageUrl": "https://cdn.dxomark.com/wp-content/uploads/2018/07/apple_iphonex-1024x768.jpg",
                "createdAt": "2019-06-17T13:01:35.536+0000",
                "updatedAt": "2019-06-17T13:01:35.536+0000"
            }
            ]
        }
    ```

    If you go to the database console `http://localhost:28003/api/h2` and <br>
    check the prices they will have a different values. <br>
    That's because when you send JWT token, system recognises you <br>
    and calculates new prices for items you see.

## How the discount logic works?

The *PriceManager* is a service implemented in a form of a <br>
Visitor design pattern. 

![Visitor](./.gitdoc/img/visitor.png)

*PriceManager* has a list of Visitors that are applied to shoping items when they are fetched from the database and correct the price.

The disount by user role is stored in the database because we might
need to frequently change it.

## Roles and privileges

The following use case diagram show the roles and their privilegies in the system.
<br>

![usecase](./.gitdoc/img/usecase.png)

# Code analysys

To analyse code do the following

1. If you are using *linux / Mac OS* execute `sudo chmod 777 sonarqube-analyze.sh`
2. Execute script `sonarcube-analyze.sh`
3. Check `http://localhost:28004` and see the results

## SonarCube credentials:

**USERNAME:** admin<br>
**PASSWORD:** admin
